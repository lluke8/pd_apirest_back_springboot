package com.blog.apirest.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "noticias")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="TB_NOTICIA")
public class Noticia implements Serializable{
	private static final long serialVersionUIDD = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@XmlElement
	private long id;
	@XmlElement
	private String autor;
	@XmlElement
	private String titulo;
	@XmlElement
	private String conteudo;
	@XmlElement
	Date data = new Date();
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
}
