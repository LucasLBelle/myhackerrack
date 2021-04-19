package mytest.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

@Entity(name="pagamento")
public class Pagamento {

	@Id
	private String identificador;
	private String cnpj_contratada;
	private String codigo_contrato;
	private String cpfContratada;
	
	@Type(type="date")
	private Date data_assinatura;
	
	@Type(type="date")
	private Date data_inicio_vigencia;
	
	@Type(type="date")
	private Date data_termino_vigencia;
	
	private String fundamento_legal;
	private String licitacao_associada;
	private Integer modalidade_licitacao;
	private Integer numero;
	private Integer numero_aditivo;
	private Integer numero_aviso_licitacao;
	private String numero_processo;
	private String objeto;
	private String origem_licitacao;
	private Integer uasg;
	private Double valor_inicial;
	
	public Pagamento() {
		
	}

}
