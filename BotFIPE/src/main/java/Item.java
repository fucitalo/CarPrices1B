
public class Item {
	private String nome;
	private int codigo;		
	//private String veiculo;
	@Override
	
	
	
	public String toString() {
		return nome + " - " + codigo + " - " + "\n";
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}
