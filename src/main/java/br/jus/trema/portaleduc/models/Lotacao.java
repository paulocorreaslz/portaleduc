package br.jus.trema.portaleduc.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */

@Data
@Entity
@Table(name = "vw_lotacao", schema = "portaleduc")
public class Lotacao implements Serializable {

	private static final long serialVersionUID = -5859934194951515870L;
	
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "mat_servidor")
	private String matricula;

	@Column(name = "cod_unid_tse")
	private String codUnidadeTse;
	
	@Column(name = "sigla_unid_tse")
	private String siglaUnidadeTse;
	
	@Column(name = "ds")
	private String descricao;
	
	@Column(name = "dt_ini_lotacao")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime dataInicioLotacao;		
	
	@Column(name = "dt_fim_lotacao")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private DateTime dataFimLotacao;
}
