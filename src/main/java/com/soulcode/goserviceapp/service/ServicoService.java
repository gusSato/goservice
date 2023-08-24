package com.soulcode.goserviceapp.service;

import com.soulcode.goserviceapp.domain.Servico;
import com.soulcode.goserviceapp.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> findAll(){
        return servicoRepository.findAll();
    }

    public Servico createServico(Servico servico){
        servico.setId(null);
        return servicoRepository.save(servico);
    }

    public void removeServiceById(Long id){
        servicoRepository.deleteById(id);
    }

    public Servico findById(Long id){
        Optional<Servico> servico = servicoRepository.findById(id);
        if (servico.isPresent()){
            return servico.get();
        }else{
            throw new RuntimeException("Deu erro");
        }
    }

    public Servico update(Servico servico){
        Servico updatedServico = this.findById(servico.getId());
        updatedServico.setNome(servico.getNome());
        updatedServico.setDescricao(servico.getDescricao());
        updatedServico.setDescricao(servico.getCategoria());
        return servicoRepository.save(servico);
    }


}
