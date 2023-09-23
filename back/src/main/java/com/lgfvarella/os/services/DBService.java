package com.lgfvarella.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgfvarella.os.domain.Cliente;
import com.lgfvarella.os.domain.OrdemDeServico;
import com.lgfvarella.os.domain.Tecnico;
import com.lgfvarella.os.enuns.Prioridade;
import com.lgfvarella.os.enuns.Status;
import com.lgfvarella.os.repositories.ClienteRepository;
import com.lgfvarella.os.repositories.OrdemDeServicoRepository;
import com.lgfvarella.os.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OrdemDeServicoRepository ordemDeServicoRepository;

	
	public void instanciaDB() {

		Tecnico t1 = new Tecnico(null, "Luis Varella", "568.392.390-55", "(62) 99611-3999");
		Tecnico t2 = new Tecnico(null, "Linus Tovards", "985.441.980-07", "(62) 99611-3888");
		Tecnico t3 = new Tecnico(null, "Vladmir Putim", "096.801.520-49", "(62) 99611-3777");
		Cliente c1 = new Cliente(null, "Aricelia Alves", "362.839.010-93", "(62) 99611-2222");
		OrdemDeServico os1 = new OrdemDeServico(null, null, Prioridade.ALTA, "Teste Create OS"
				, Status.ABERTA, t1, c1);
		OrdemDeServico os2 = new OrdemDeServico(null, null, Prioridade.BAIXA, "Teste Create OS2"
				, Status.ABERTA, t2, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);
		t2.getList().add(os2);
		c1.getList().add(os2);
		

		tecnicoRepository.saveAll(Arrays.asList(t1, t2, t3));
		clienteRepository.saveAll(Arrays.asList(c1));
		ordemDeServicoRepository.saveAll(Arrays.asList(os1,os2));

	}

}
