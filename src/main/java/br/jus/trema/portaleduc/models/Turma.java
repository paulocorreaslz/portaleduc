package br.jus.trema.portaleduc.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;
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
import org.joda.time.format.DateTimeFormat;

import br.jus.trema.vraptorbridge.models.AbstractEntity;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@SequenceGenerator(name="seq", sequenceName = "sq_turma")
public class Turma extends AbstractEntity {

	
	public Turma(Curso cursox) {
		// TODO Auto-generated constructor stub
		this.curso = cursox;
	}

	private static final long serialVersionUID = 1L;

	@NotEmpty
    private String nome;

	@NotEmpty
    private String cargaHoraria;
	
	@NotEmpty	
	@Column(name="CONTEUDO", length = 2000)
    private String conteudo;
	
	@NotEmpty	
    private String publico;
	
	@NotEmpty	
    private String tempoConclusao;
		
	@NotEmpty	
	@Column(name="ESTRATEGIAS", length = 1000)
	private String estrategias;
	
    @NotEmpty	
    private String vagasCartorio;
		
	@NotEmpty	
    private String vagasSecretaria;	
    
    @NotEmpty	
    private String horaTutoria;	
    
 //   @NotEmpty	
//	@Column(name="SITUACAOTURMA", length = 100)
//    private String situacaoTurma;	
    
    @NotEmpty	
    @Column(name="TUTORIA", length = 1000)
    private String tutoria;		
   
    @NotEmpty	
    @Column(name="METODOAVALIACAO", length = 1000)
    private String metodoAvaliacao;
    
    @NotEmpty	
    @Column(name="METODOINTERACAO", length = 1000)
    private String metodoInteracao;
    
    @Column(name="OBSERVACOES", length = 1000)
    private String observacoes;
        
	@NotNull
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dataInicial;
 
	@NotNull
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dataFinal;
	     
	@Transient 
	private Date dataInicialT;
	
	@Transient 
	private Date dataFinalT;
		
	public void setDataFinalT(Date dataFinalT){
		this.dataFinalT = dataFinalT;
	}
	
	public void setDataInicialT(Date dataInicialT){
		this.dataInicialT = dataInicialT;
	}
	
	public Date getDataFinalT(){
		return dataFinalT;
	}
	
	public Date getDataInicialT(){
		return dataInicialT;
	}
	
	public String getDataInicialFormatada(){
		return DateTimeFormat.forPattern("dd/MM/yyyy").print(dataInicial);
	}
	
	public String getDataFinalFormatada(){
		return DateTimeFormat.forPattern("dd/MM/yyyy").print(dataFinal);
	}
	
	public String getTotalInscritos(){			
		if (alunos != null && professores != null)
			return ""+(alunos.size() + professores.size());
		return "0";
	}
	
	public String getTotalAlunos(){			
		if (alunos != null)	
			return "" + alunos.size();
		return "0";
	}
	
	public String getTotalProfessores(){		
		if (professores != null)
			return "" + professores.size();
		return "0";
	}

	public String getAlunosAprovados(){
		int total = 0;
		if (alunos != null ){
			for (Aluno aluno : alunos) {
				if (aluno.getSituacao().equals("A"))
					total++;
			}
			return "" + total;
		} else {
			return "0";
		}
	}
	
	public String getAlunosReprovados(){
		int total = 0;
		if (alunos != null ){
			for (Aluno aluno : alunos) {
				if (aluno.getSituacao().equals("R"))
					total++;
			}
		return "" + total;
		} else {
			return "0";
		}
	}
	
	public String getAlunosDesistentes(){
		int total = 0;
		if (alunos != null) {
			for (Aluno aluno : alunos) {
				if (aluno.getSituacao().equals("D"))
					total++;
			}
			return "" + total;
		} else {
			return "0";
		}
	}
	
	public String getAlunosEvadidos(){
		int total = 0;
		if (alunos != null) {
			for (Aluno aluno : alunos) {
				if (aluno.getSituacao().equals("E"))
					total++;
			}
			return "" + total;
		} else {
			return "0";
		}		
	}
	
	public String getCertificadosLiberados(){
		int total = 0;
		if (certificados != null) {
			for (Certificado certificado : certificados) {
				if (certificado.getAluno() != null)
					total++;
			}
			return "" + total;
		} else {
			return "0";
		}
	}

	public String getDeclaracoesLiberadas(){
		int total = 0;
		if (certificados != null) {
			for (Certificado certificado : certificados) {
				if (certificado.getProfessor() != null)
					total++;
			}
			return "" + total;
		} else {
			return "0";
		}
	}
	
	public String getRelacaoTutorAluno(){		

		if (alunos != null && professores != null) { 					
			
			if (alunos.size() > 0 && professores.size() >0) {
				return "" + Math.round(alunos.size() / professores.size());
			} else {
				return "0";
			}
			
		} else {			
			return "000";		
		}		
	}
	
    @ManyToOne
	private Curso curso;

    @OneToMany(mappedBy="turma")
	private List<Aluno> alunos;
    	
    @OneToMany(mappedBy="turma")
	private List<Professor> professores;
    
    @OneToMany(mappedBy="turma")
	private List<Certificado> certificados;
        
}
