package com.nsis.bo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
public class ConsoleBO implements Serializable {
	private static final long seriaVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PROD")
	private String dev;

//	@ManyToMany(mappedBy = "console")
//	private Set<JeuxBO> jeux;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDev() {
		return dev;
	}

	public void setDev(String dev) {
		this.dev = dev;
	}

//	public Set<JeuxBO> getJeux() {
//		return jeux;
//	}
//
//	public void setJeux(Set<JeuxBO> jeux) {
//		this.jeux = jeux;
//	} 
	
	public ConsoleBO() {
		
	}
	
	
}
