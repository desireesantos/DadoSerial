package modelo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;




@Entity
@Table(name = "TB_DADOS")

public class Dados {

	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ_ISSUE")
	@SequenceGenerator(name="SEQ_ISSUE", sequenceName="SEQ_ISSUE", allocationSize=1)
	private int id;
	
	@Column(name = "dados", nullable = false, length = 20)
	private String dados;
	
	@Transient
	private  FlagStatus flag ;
	

	public Dados(FlagStatus flag) {
		super();
		this.flag = flag;
	}
	

	public FlagStatus getFlag() {
		return flag;
	}

	public void setFlag(FlagStatus flag) {
		this.flag = flag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

}
