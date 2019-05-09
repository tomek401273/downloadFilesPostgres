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
	@Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
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
//	create table USER_DOCUMENT (id SERIAL, content BYTEA not null, name varchar(255), type varchar(255), primary key (id))
