package br.jus.trema.portaleduc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import br.jus.trema.portaleduc.enums.SituacaoAluno;
import br.jus.trema.vraptorbridge.models.AbstractEntity;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@SequenceGenerator(name="seq", sequenceName = "sq_aluno")
public class Aluno extends AbstractEntity {

	public Aluno(Turma turma) {
		// TODO Auto-generated constructor stub
		this.turma = turma;
	}
	
	
	private static final long serialVersionUID = 1L;

	@NotEmpty
    private String matricula;
    
	@NotEmpty
    private String nome;

	@NotEmpty
	private String lotacao;
    
	@NotNull
	@Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", length = 1)  
    private SituacaoAluno situacao;

	@Transient
    private String situacaoT;
	
    private String justificativa;
	
	@NotNull
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dataCadastro;  
	
	@ManyToOne
	private Turma turma;
	 
	@OneToOne(mappedBy="aluno")
	private Certificado certificado;  
	
}
