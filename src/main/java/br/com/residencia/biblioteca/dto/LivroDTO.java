package br.com.residencia.biblioteca.dto;

import java.time.Instant;

public class LivroDTO {
	  private int codigoLivro;	  
	  private String nomeLivro;	  
	  private String nomeAutor;	  
	  private Instant dataLancamento;
	  private int codigoIsbn;
	  
	public LivroDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LivroDTO(int codigoLivro, String nomeLivro, String nomeAutor, Instant dataLancamento, int codigoIsbn) {
		super();
		this.codigoLivro = codigoLivro;
		this.nomeLivro = nomeLivro;
		this.nomeAutor = nomeAutor;
		this.dataLancamento = dataLancamento;
		this.codigoIsbn = codigoIsbn;
	}

	public int getCodigoLivro() {
		return codigoLivro;
	}

	public void setCodigoLivro(int codigoLivro) {
		this.codigoLivro = codigoLivro;
	}

	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public Instant getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Instant dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public int getCodigoIsbn() {
		return codigoIsbn;
	}

	public void setCodigoIsbn(int codigoIsbn) {
		this.codigoIsbn = codigoIsbn;
	}
	  
}
