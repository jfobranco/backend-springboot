package com.jb.restjb.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	private String userName;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USER_COMPANY", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "companyId", referencedColumnName = "companyId"))
	@JsonIgnore
	private List<Company> following;
}
