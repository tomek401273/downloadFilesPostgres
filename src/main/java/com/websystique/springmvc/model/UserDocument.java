package com.websystique.springmvc.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(schema = "sandbox_tgrajkowski", name = "user_document")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDocument {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "user_document_seq", sequenceName = "user_document_sequence")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "user_document_seq")
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
