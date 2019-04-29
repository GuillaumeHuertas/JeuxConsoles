package com.nsis.bo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
public class JeuxBO implements Serializable {
	private static final long seriaVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	
	@Column(name = "JEUX")
	private String name; 
	
	@Column(name = "DEV")
	private String dev; 
	
	@Column(name = "NOTE")
	private int note; 
	
	@Column(name = "IMAGE")
	private Long image; 
	
	@ManyToMany
	@JoinTable(
	  name = "jeux_console")
	private Set<ConsoleBO> console; 
	
	@Column(name = "FINISHED")
	private String finished;

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

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public Long getImage() {
		return image;
	}

	public void setImage(Long image) {
		this.image = image;
	}

	public Set<ConsoleBO> getConsole() {
		return console;
	}

	public void setConsole(Set<ConsoleBO> console) {
		this.console = console;
	}

	public String isFinished() {
		return finished;
	}

	public void setFinished(String finished) {
		this.finished = finished;
	} 
	
	@Override
	public String toString() {
		return name; 
	}
	
	// Constructeur 
	public JeuxBO() {
		
	}
	
	

}
