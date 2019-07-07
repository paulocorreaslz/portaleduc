package br.jus.trema.portaleduc.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.validator.constraints.NotEmpty;

import br.jus.trema.vraptorbridge.models.AbstractEntity;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@SequenceGenerator(name="seq", sequenceName = "sq_comentario")
public class Comentario extends AbstractEntity {

	private static final long serialVersionUID = -5859934194951515870L;
	
	@NotEmpty
	private String conteudo;
	
	@NotEmpty
	private String nomeUsuario;
	
	@ManyToOne
	private Artigo artigo;

}
