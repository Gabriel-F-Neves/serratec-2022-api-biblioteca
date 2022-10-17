/*
package br.com.residencia.biblioteca.mapper;

import org.springframework.stereotype.Component;

import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.entity.Editora;

@Component
public class EditoraMapper {

	public EditoraDTO paraDto(Editora editora) {
		EditoraDTO editoraDTO = new EditoraDTO();
		if(editora != null) {
			editoraDTO.setNome(editora.getNome());
			editoraDTO.setCodigoEditora(editora.getCodigoEditora());
		}
		
		return editoraDTO;
	}
	
	public Editora paraEntidade(EditoraDTO editoraDTO) {
		Editora editora = new Editora();
		editora.setNome(editoraDTO.getNome());
		
		return editora;
	}
}
*/