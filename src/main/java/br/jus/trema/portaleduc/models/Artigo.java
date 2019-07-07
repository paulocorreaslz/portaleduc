package br.jus.trema.portaleduc.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDateTime;

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
@SequenceGenerator(name="seq", sequenceName = "sq_artigo")
public class Artigo extends AbstractEntity {

	private static final long serialVersionUID = -5859934194951515870L;

	@NotEmpty
	private String titulo;
	
	@NotEmpty
	private String conteudo;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime dataCadastro;
		
	@OneToMany(mappedBy="artigo")
	private List<Comentario> comentarios;

	public String getPreview() {
		return StringUtils.abbreviate(conteudo, 50);
	}
	
}
