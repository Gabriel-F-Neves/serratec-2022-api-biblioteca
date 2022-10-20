package br.com.residencia.biblioteca.dto;

import java.time.Instant;
import java.util.List;

public class AlunoDTO {
	private int numeroMatriculaAluno;
	private String nome;
	private String cpf;
	private List<EmprestimoDTO> listaEmprestimosDTO;
	
	public AlunoDTO() {
		super();
	}

	public AlunoDTO(int numeroMatriculaAluno, String nome, Instant dataNascimento, String cpf, String logradouro,
			String numeroLogradouro, String complemento, String bairro, String cidade) {
		super();
		this.numeroMatriculaAluno = numeroMatriculaAluno;
		this.nome = nome;
		this.cpf = cpf;
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
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public List<EmprestimoDTO> getListaEmprestimosDTO() {
		return listaEmprestimosDTO;
	}

	public void setListaEmprestimosDTO(List<EmprestimoDTO> listaEmprestimosDTO) {
		this.listaEmprestimosDTO = listaEmprestimosDTO;
	}
}
