package br.com.residencia.biblioteca.dto;

import java.util.List;

public class EditoraDTO {
	private Integer codigoEditora;
	private String nome;
	private String imagemFileName;
	private String imagemUrl;
	private String ImagemNome;
	private List<LivroDTO> listaLivrosDTO;
	
	public EditoraDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EditoraDTO(Integer codigoEditora, String nome) {
		super();
		this.codigoEditora = codigoEditora;
		this.nome = nome;
	}

	public Integer getCodigoEditora() {
		return codigoEditora;
	}

	public void setCodigoEditora(Integer codigoEditora) {
		this.codigoEditora = codigoEditora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<LivroDTO> getListaLivrosDTO() {
		return listaLivrosDTO;
	}

	public void setListaLivrosDTO(List<LivroDTO> listaLivrosDTO) {
		this.listaLivrosDTO = listaLivrosDTO;
	}
	

	public String getImagemFileName() {
		return imagemFileName;
	}

	public void setImagemFileName(String imagemFileName) {
		this.imagemFileName = imagemFileName;
	}

	public String getImagemUrl() {
		return imagemUrl;
	}

	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}

	public String getImagemNome() {
		return ImagemNome;
	}

	public void setImagemNome(String imagemNome) {
		ImagemNome = imagemNome;
	}

	@Override
	public String toString() {
		return "EditoraDTO [codigoEditora=" + codigoEditora + ", nome=" + nome + ", listaLivrosDTO=" + listaLivrosDTO
				+ "]";
	}

}
