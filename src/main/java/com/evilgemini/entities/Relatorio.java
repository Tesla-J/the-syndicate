package com.evilgemini.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "relatorio")
public class Relatorio {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn (name = "falecido")
	private Falecido falecido;
	private String descricao;
	
	@ManyToOne
	@JoinColumn (name = "funcionario")
	private Funcionario autor;
	private LocalDate dataEmissao;
	
	public Relatorio () {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Falecido getFalecido() {
		return falecido;
	}
	public void setFalecido(Falecido falecido) {
		this.falecido = falecido;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Funcionario getAutor() {
		return autor;
	}
	public void setAutor(Funcionario autor) {
		this.autor = autor;
	}
	public LocalDate getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
}
