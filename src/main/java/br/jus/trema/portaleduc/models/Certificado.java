package br.jus.trema.portaleduc.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import br.jus.trema.vraptorbridge.models.AbstractEntity;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@SequenceGenerator(name="seq", sequenceName = "sq_certificado")
public class Certificado extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private String codigo;
	
    @NotNull
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime dataCadastro; 
	     
    @OneToOne	
	private Turma turma;
    
    @OneToOne	
	private Aluno aluno;

    @OneToOne	
	private Professor professor;
    
    private String textoLegal;
    
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime dataImpressao; 

}
