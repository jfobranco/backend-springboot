package com.jb.restjb.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long postId;

	private String title;
	
	private String content;

	private LocalDateTime stamp;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="companyId")
	@JsonIgnore
	private Company company;
}
