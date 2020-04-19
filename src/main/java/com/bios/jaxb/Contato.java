package com.bios.jaxb;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Contato")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "contato", 
		propOrder = { "id", "nome", "email", "endereco", "telefones" }, 
		namespace = "http://mballem.com")
public class Contato {

	@XmlElement(name="id", required=true)
	private int id;
	
	@XmlElement(name="nome", required=true)
	private String nome;
	
	@XmlElement(name="email", required=true)
	private String email;
	
	//Wrapper usado para coleções
	@XmlElementWrapper(name="telefones")
	@XmlElement(name="Telefone")
	private Collection<Telefone> telefones;
	
	@XmlElement(name="Endereco", required=true)
	private Endereco endereco;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Contato###Id:"+id+"; Nome:"+nome+"; Email:"+email+"; "+telefones.toString()+"; "+endereco.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Collection<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
