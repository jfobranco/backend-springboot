package com.jb.restjb.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Service {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long serviceId;
	
	private String name;
	
	private String code;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="companyId")
	private Company company;
}
