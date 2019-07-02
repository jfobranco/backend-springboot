package com.jb.restjb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long companyId;

	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	@JsonIgnore
	private List<Post> posts;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	@JsonIgnore
	private List<Service> services;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "following")
	private List<User> followers;
}
