package br.com.residencia.biblioteca.dto;

import java.time.Instant;

import br.com.residencia.biblioteca.entity.Aluno;


public class AlunoDTO {
	private int numeroMatriculaAluno;
	private String nome;
	private Instant dataNascimento;
	private String cpf;
	private String numeroLogradouro;
	private String complemento;
	private String bairro;
	private String cidade;
	
	public AlunoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlunoDTO(Aluno aluno) {
		super();
		this.numeroMatriculaAluno = aluno.getNumeroMatriculaAluno();
		this.nome = aluno.getNome();
		this.dataNascimento = aluno.getDataNascimento();
		this.cpf = aluno.getCpf();
		this.numeroLogradouro = aluno.getNumeroLogradouro();
		this.complemento = aluno.getComplemento();
		this.bairro = aluno.getBairro();
		this.cidade = aluno.getCidade();
	}

	public int getNumeroMatriculaAluno() {
		return numeroMatriculaAluno;
	}

	public void setNumeroMatriculaAluno(int numeroMatriculaAluno) {
		this.numeroMatriculaAluno = numeroMatriculaAluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Instant getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Instant dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumeroLogradouro() {
		return numeroLogradouro;
	}

	public void setNumeroLogradouro(String numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
}
