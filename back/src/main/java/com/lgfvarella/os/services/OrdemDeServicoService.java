package com.lgfvarella.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgfvarella.os.domain.Cliente;
import com.lgfvarella.os.domain.OrdemDeServico;
import com.lgfvarella.os.domain.Tecnico;
import com.lgfvarella.os.dtos.OrdemDeServicoDTO;
import com.lgfvarella.os.enuns.Prioridade;
import com.lgfvarella.os.enuns.Status;
import com.lgfvarella.os.repositories.OrdemDeServicoRepository;
import com.lgfvarella.os.services.exceptions.DataIntegratyViolationException;
import com.lgfvarella.os.services.exceptions.ObjectNotFoundException;

@Service
public class OrdemDeServicoService {

	@Autowired
	private OrdemDeServicoRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;

	public OrdemDeServico findById(Integer id) {
		Optional<OrdemDeServico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + OrdemDeServico.class.getName()));

	}

	public List<OrdemDeServico> findAll() {
		return repository.findAll();
	}

	public OrdemDeServico create(@Valid OrdemDeServicoDTO obj) {
		return fromDTO(obj);
	}
	
	public OrdemDeServico update(@Valid OrdemDeServicoDTO objDTO) {
		findById(objDTO.getId());
		return fromDTO(objDTO);
	}
	
	public void delete(Integer id) {
		
		OrdemDeServico obj = findById(id);
		
		if(obj != null && obj.getStatus().getCod().equals(2)) {
			throw new DataIntegratyViolationException("Esta Ordem de serviço já foi encerrada e não pode ser deletada");			
		}
		
		repository.deleteById(id);
		
	}
	
	private OrdemDeServico fromDTO (OrdemDeServicoDTO obj) {
		OrdemDeServico newObj = new OrdemDeServico();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente());
		
		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		return repository.save(newObj);
		
		
	}

	

	

}
