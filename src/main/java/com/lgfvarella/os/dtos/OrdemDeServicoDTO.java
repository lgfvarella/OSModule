package com.lgfvarella.os.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lgfvarella.os.domain.OrdemDeServico;

public class OrdemDeServicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataFechamento;
	
	private Integer prioridade;
	
	@NotEmpty(message = "O campo OBSERVAÇÕES é Obrigatório!")
	private String observacoes;	
	private Integer status;
	
	private Integer tecnico;	
	private Integer cliente;
	
	public OrdemDeServicoDTO() {
		super();
	}
	public OrdemDeServicoDTO(OrdemDeServico obj) {
		super();
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataFechamento = obj.getDataFechamento();
		this.prioridade = obj.getPrioridade().getCod();
		this.observacoes = obj.getObservacoes();
		this.status = obj.getStatus().getCod();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the dataAbertura
	 */
	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}
	/**
	 * @param dataAbertura the dataAbertura to set
	 */
	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	/**
	 * @return the dataFechamento
	 */
	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}
	/**
	 * @param dataFechamento the dataFechamento to set
	 */
	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	/**
	 * @return the prioridade
	 */
	public Integer getPrioridade() {
		return prioridade;
	}
	/**
	 * @param prioridade the prioridade to set
	 */
	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}
	/**
	 * @return the observacoes
	 */
	public String getObservacoes() {
		return observacoes;
	}
	/**
	 * @param observacoes the observacoes to set
	 */
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return the tecnico
	 */
	public Integer getTecnico() {
		return tecnico;
	}
	/**
	 * @param tecnico the tecnico to set
	 */
	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}
	/**
	 * @return the cliente
	 */
	public Integer getCliente() {
		return cliente;
	}
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemDeServicoDTO other = (OrdemDeServicoDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
	

}
