package com.nsis.bo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@Column(name = "NAME")
	private String name; 
	
	@Column(name = "DEV")
	private String dev; 
	
	@Column(name = "NOTE")
	private int note; 
	
	@Column(name = "IMAGE")
	private String image; 
	
	// EAGER limite en volumétrie 
	// Regarder Pagination JPA
	// FetchType.LAZY
	@ManyToMany(fetch = FetchType.EAGER, cascade = {
		    CascadeType.PERSIST,
		    CascadeType.MERGE
		})
	@JoinTable(
	  name = "jeux_console")
	private Set<ConsoleBO> console; 
	
	@Column(name = "FINISHED")
	private boolean finished;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<ConsoleBO> getConsole() {
		return console;
	}

	public void setConsole(Set<ConsoleBO> console) {
		this.console = console;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	} 
	
	@Override
	public String toString() {
		return name; 
	}
	
	// Constructeur 
	public JeuxBO() {
		this.console = new HashSet<ConsoleBO>(); 
		
	}
	
	

}
