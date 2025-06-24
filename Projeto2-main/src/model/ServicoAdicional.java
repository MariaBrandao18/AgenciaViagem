
package classes;

import java.util.ArrayList;

public class ServicoAdicional {
	// variaveis
	String nome;
	double preco;
	String descricao;
	Long servico_id;
	
	// construtor
	public ServicoAdicional(String nome, double preco, String descricao) {
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
	}
	
	
	//funcoes
	
	 public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getServicoId() {
		return servico_id;
	}
	public void setServicoId(Long servico_id) {
		this.servico_id = servico_id;
	}
}

	


// Servicos adicionais
class Translado extends ServicoAdicional{
	public Translado(String nome, double preco, String descricao) {
		super(nome,preco, descricao);
	}
}

class Passeios extends ServicoAdicional{
	public Passeios(String nome, double preco, String descricao) {
		super(nome,preco, descricao);
	}
}

class MotoristaParticular extends ServicoAdicional{
	public MotoristaParticular(String nome, double preco, String descricao) {
		super(nome,preco, descricao);
	}
}

class AluguelCarro extends ServicoAdicional{
	public AluguelCarro(String nome, double preco, String descricao) {
		super(nome,preco, descricao);
	}
}
