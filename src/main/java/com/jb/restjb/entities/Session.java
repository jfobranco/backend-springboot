package com.jb.restjb.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sessionId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="companyId")
	private Company company;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "SESSION_SERVICE", joinColumns = @JoinColumn(name = "sessionId", referencedColumnName = "sessionId"), inverseJoinColumns = @JoinColumn(name = "serviceId", referencedColumnName = "serviceId"))
	private List<Service> services;
	
	private BigDecimal total;
}
