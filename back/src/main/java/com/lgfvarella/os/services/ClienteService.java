package com.lgfvarella.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgfvarella.os.domain.Cliente;
import com.lgfvarella.os.domain.Pessoa;
import com.lgfvarella.os.dtos.ClienteDTO;
import com.lgfvarella.os.repositories.ClienteRepository;
import com.lgfvarella.os.repositories.PessoaRepository;
import com.lgfvarella.os.services.exceptions.DataIntegratyViolationException;
import com.lgfvarella.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Cliente.class.getName()));

	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		if (this.findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado");
		}
		return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado");
		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());

		return repository.save(oldObj);
	}

	public void delete(Integer id) {

		Cliente obj = findById(id);

		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Este cliente possui ordens de serviço, não pode ser deletado");
		}

		repository.deleteById(id);
	}

	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}
