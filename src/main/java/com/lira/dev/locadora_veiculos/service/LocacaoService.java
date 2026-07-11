package com.lira.dev.locadora_veiculos.service;

import com.lira.dev.locadora_veiculos.dto.request.CriarLocacaoDTO;
import com.lira.dev.locadora_veiculos.dto.response.LocacaoResponseDTO;
import com.lira.dev.locadora_veiculos.entity.Cliente;
import com.lira.dev.locadora_veiculos.entity.Locacao;
import com.lira.dev.locadora_veiculos.entity.Veiculo;
import com.lira.dev.locadora_veiculos.enums.StatusLocacao;
import com.lira.dev.locadora_veiculos.exception.BadRequestException;
import com.lira.dev.locadora_veiculos.exception.ClienteNotFoundException;
import com.lira.dev.locadora_veiculos.exception.LocacaoNotFoundException;
import com.lira.dev.locadora_veiculos.exception.VeiculoNotFoundException;
import com.lira.dev.locadora_veiculos.mapper.LocacaoMapper;
import com.lira.dev.locadora_veiculos.repository.ClienteRepository;
import com.lira.dev.locadora_veiculos.repository.LocacaoRepository;
import com.lira.dev.locadora_veiculos.repository.VeiculoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LocacaoService {

    private final LocacaoRepository locacaoRepository;
    private final LocacaoMapper locacaoMapper;
    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;


    public LocacaoService(LocacaoRepository locacaoRepository,VeiculoRepository veiculoRepository,ClienteRepository clienteRepository,LocacaoMapper locacaoMapper){
        this.locacaoRepository = locacaoRepository;
        this.veiculoRepository = veiculoRepository;
        this.clienteRepository = clienteRepository;
        this.locacaoMapper = locacaoMapper;
    }


    @Transactional
    public LocacaoResponseDTO cadastrarLocacao(CriarLocacaoDTO request){
        Cliente cliente = buscarClienteIdOuFalhar(request.getClienteId());
        Veiculo veiculo = buscarVeiculoIdOuFalhar(request.getVeiculoId());
        if (!veiculo.isDisponivel()){
            throw new BadRequestException("Veiculo não está disponivel.");
        }

        boolean ePosterior = request.getDataPrevistaDevolucao().isAfter(request.getDataRetirada());
        if(!ePosterior){
            throw new BadRequestException("Data de devolução deve ser após a data de retirada. ");
        }

        long diferencaDeDias = ChronoUnit.DAYS.between(request.getDataRetirada(),request.getDataPrevistaDevolucao());

        if(diferencaDeDias <= 0){
            diferencaDeDias = 1;
        }

        BigDecimal valorTotal = veiculo.getValorDiaria().multiply(BigDecimal.valueOf(diferencaDeDias));

        Locacao locacao = Locacao.builder()
                .dataRetirada(request.getDataRetirada())
                .dataPrevistaDevolucao(request.getDataPrevistaDevolucao())
                .valorTotal(valorTotal)
                .status(StatusLocacao.ATIVA)
                .cliente(cliente)
                .veiculo(veiculo)
                .build();

        veiculo.setDisponivel(false);

        Locacao locacaoNova = locacaoRepository.save(locacao);


        return locacaoMapper.toResponseDTO(locacaoNova);
    }

    @Transactional
    public LocacaoResponseDTO devolverLocacao(Long id){
        Locacao locacao = buscarLocacaoIdOuFalhar(id);

        if (locacao.getStatus() != StatusLocacao.ATIVA){
            throw new BadRequestException("Essa locação já foi finalizada ou cancelada, não é possivel devolver.");
        }

        locacao.setDataDevolucao(LocalDate.now());
        locacao.setStatus(StatusLocacao.FINALIZADA);
        locacao.getVeiculo().setDisponivel(true);

        Locacao locacaoDevolvida = locacaoRepository.save(locacao);

        return locacaoMapper.toResponseDTO(locacaoDevolvida);
    }

    @Transactional
    public LocacaoResponseDTO cancelarLocacao(Long id){
        Locacao locacao = buscarLocacaoIdOuFalhar(id);

        if(locacao.getStatus() != StatusLocacao.ATIVA){
            throw new BadRequestException("Não é possivel cancelar uma Locacao já finalizada ou cancelada.");
        }

        if (LocalDate.now().isAfter(locacao.getDataPrevistaDevolucao())){
            throw new BadRequestException("Não é possível cancelar uma locação após a data prevista de devolução");
        }

        locacao.setStatus(StatusLocacao.CANCELADA);
        locacao.getVeiculo().setDisponivel(true);

        long diasUtilizados = ChronoUnit.DAYS.between(locacao.getDataRetirada(),LocalDate.now());
        if (diasUtilizados <= 0){
            diasUtilizados = 1;
        }

        locacao.setValorTotal(locacao.getVeiculo().getValorDiaria().multiply(BigDecimal.valueOf(diasUtilizados)));

        Locacao locacaoCancelada = locacaoRepository.save(locacao);

        return locacaoMapper.toResponseDTO(locacaoCancelada);

    }


    public List<LocacaoResponseDTO> listarLocacoes(){
        return locacaoRepository.findAll()
                .stream()
                .map(locacaoMapper::toResponseDTO)
                .toList();
    }

    public LocacaoResponseDTO listarLocacaoPorId(Long id){
        return locacaoMapper.toResponseDTO(buscarLocacaoIdOuFalhar(id));
    }

    public List<LocacaoResponseDTO> buscarLocacoesPorCliente(Long clienteid){
        return locacaoRepository.findByClienteId(clienteid)
                .stream()
                .map(locacaoMapper::toResponseDTO)
                .toList();
    }

    public List<LocacaoResponseDTO> buscarLocacoesPorStatus(StatusLocacao status){
        return locacaoRepository.findByStatus(status)
                .stream()
                .map(locacaoMapper::toResponseDTO)
                .toList();
    }

    public Page<LocacaoResponseDTO> buscarLocacoesPaginadas(Pageable pageable){
        return locacaoRepository.findAll(pageable).map(locacaoMapper::toResponseDTO);
    }


    private Cliente buscarClienteIdOuFalhar(Long id){
        return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException("Cliente de ID: " + id + " não encontrado."));
    }

    private Veiculo buscarVeiculoIdOuFalhar(Long id){
        return veiculoRepository.findById(id).orElseThrow(() -> new VeiculoNotFoundException("Veiculo de ID: " + id + " não encontrado."));
    }

    private Locacao buscarLocacaoIdOuFalhar(Long id){
        return locacaoRepository.findById(id).orElseThrow(() -> new LocacaoNotFoundException("Locação de ID: " + id + " não encontrado."));
    }





}
