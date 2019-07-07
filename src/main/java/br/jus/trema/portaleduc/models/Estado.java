package br.jus.trema.portaleduc.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.envers.Audited;
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
@Audited
@SequenceGenerator(name="seq", sequenceName = "sq_estado")
public class Estado extends AbstractEntity {

	private static final long serialVersionUID = 655267032257535207L;

	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String sigla;
	
	@NotNull
	@ManyToOne
	private Pais pais;
		
}
