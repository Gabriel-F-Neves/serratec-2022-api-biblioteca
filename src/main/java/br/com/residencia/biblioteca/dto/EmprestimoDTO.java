package br.com.residencia.biblioteca.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class EmprestimoDTO {
	private int codigoEmprestimo;
	private Instant dataEmprestimo;
	private Instant dataEntrega;
	
	public EmprestimoDTO() {
		super();
	}

	public EmprestimoDTO(int codigoEmprestimo, Instant dataEmprestimo, Instant dataEntrega,
			BigDecimal valorEmprestimo) {
		super();
		this.codigoEmprestimo = codigoEmprestimo;
		this.dataEmprestimo = dataEmprestimo;
		this.dataEntrega = dataEntrega;
	}

	public int getCodigoEmprestimo() {
		return codigoEmprestimo;
	}

	public void setCodigoEmprestimo(int codigoEmprestimo) {
		this.codigoEmprestimo = codigoEmprestimo;
	}

	public Instant getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Instant dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Instant getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Instant dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
}
