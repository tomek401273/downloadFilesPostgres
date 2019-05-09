package com.websystique.springmvc.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="USER_DOCUMENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDocument {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@Column(name="name")
	private String name;

	@Column(name="type")
	private String type;
	
	@Lob
    @Basic(fetch = FetchType.LAZY)
	@Column(name="content", nullable=false)
	private byte[] content;
}
