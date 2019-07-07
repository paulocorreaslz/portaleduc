package br.jus.trema.portaleduc.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

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
@SequenceGenerator(name="seq", sequenceName = "sq_pais")
public class Pais extends AbstractEntity {

	private static final long serialVersionUID = -5859934194951515870L;

	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String sigla;

	@Audited
	@OneToMany(mappedBy="pais")
	private List<Estado> estados;
	
	public Pais(String nome, String sigla) {
		this.nome = nome;
		this.sigla = sigla;
	}
		
	@Override
	public String toString() {
		return nome;
	}

}
