package com.rizwancode.userservice.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "M_USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	
	@Id
	@Column(name = "ID")
	private String userId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "ABOUT")
	private String about;
	
	@Transient
	private List<Rating> ratings = new ArrayList<>();

	@PrePersist
	public void generateId() {
	    if (userId == null) {
	    	userId = UUID.randomUUID().toString();
	    }
	}
}
