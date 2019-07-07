package br.jus.trema.portaleduc.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */

@Data
@Entity
@Table(name = "vw_servidor_srh", schema = "portaleduc")
public class Servidor implements Serializable {

	private static final long serialVersionUID = -5859934194951515870L;

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "mat_servidor")
	private String matricula;
	
	@Column(name = "nom")
	private String nome;
	
	@Column(name = "sg_lotacao")
	private String lotacao;
	
	@Column(name = "situacao")
	private String situacao;
	
	//@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	//private LocalDateTime dataCadastro;		
	
}
