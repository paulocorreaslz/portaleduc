package br.jus.trema.portaleduc.models;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import br.jus.trema.portaleduc.enums.Eleitoral;
import br.jus.trema.portaleduc.enums.TipoCurso;
import br.jus.trema.portaleduc.enums.ValidoAQ;
import br.jus.trema.portaleduc.enums.VersaoMoodle;
import br.jus.trema.vraptorbridge.models.AbstractEntity;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@SequenceGenerator(name="seq", sequenceName = "sq_curso")
public class Curso extends AbstractEntity  {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPOCURSO", length = 1)
    private TipoCurso tipoCurso;
    
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ELEITORAL", length = 1)   
    private Eleitoral eleitoral;
    
    @NotEmpty
    private String justificativa;
    
    @NotEmpty    
    private String objetivo;
     
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "VALIDOAQ", length = 1)   
    private ValidoAQ validoAQ;
      
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "VERSAOMOODLE", length = 1)   
    private VersaoMoodle versaoMoodle;
       
    @NotNull
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime dataCadastro; 
            
	@Transient 
	private Date dataCadastroT;
		
	@Transient	
	private String metodologia;
	
	@Transient 
	private String validoAQT;
	
	public void setDataCadastroT(Date dataCadastroT){
		this.dataCadastroT = dataCadastroT;
	}
	
	public Date getDataCadastroT(){
		return dataCadastroT;
	}
    
	public String getDataCadastroFormatada(){
		return DateTimeFormat.forPattern("dd/MM/yyyy").print(dataCadastro);
	}
	
	public void setMetodologia(String t){	
		this.metodologia = t;
	}
	
	public String getMetodologia(){
		return this.metodologia;
	}

	public String getTotalTurmas(){		
		return "" + turmas.size();
	}
	
	public String getTotalTurmas2(){
		int total = 0;
		for (Turma turma : turmas) {
			if (turma.getCurso().equals(this))
				total++;
		}
		
		return "" + turmas.size();
	}
	
    @OneToMany(mappedBy="curso")
	private List<Turma> turmas;
    	
}
