package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.EmprestimoDTO;
import br.com.residencia.biblioteca.dto.LivroDTO;
import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;
import br.com.residencia.biblioteca.repository.LivroRepository;

@Service
public class LivroService {
	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	EmprestimoRepository emprestimoRepository;
	
	@Autowired
	EmprestimoService emprestimoService;
	
	public List<Livro> getAllLivros(){
		return livroRepository.findAll();
	}
	
	public List<LivroDTO> getAllLivrosDTO(){
		List<LivroDTO> livrosDTO = new ArrayList<>();
		for(Livro livro : livroRepository.findAll()) {
			livrosDTO.add(this.toDTO(livro));
		}
		return livrosDTO;
	}
	
	public Livro getLivroById(Integer id) {
		return livroRepository.findById(id).get();
		//return livroRepository.findById(id).orElse(null);
	}
	
	public Livro saveLivro(Livro livro) {
		return livroRepository.save(livro);
	}
	
	public LivroDTO saveLivroDTO(LivroDTO livroDTO) {
		Livro livro = toEntidade(livroDTO);
		Livro novoLivro = livroRepository.save(livro);
		
		LivroDTO livroAtualizadoDTO = toDTO(novoLivro);		
		return livroAtualizadoDTO;
	}
	
	public LivroDTO updateLivroDTO(LivroDTO livroDTO, Integer id) {
		Livro livroExistenteNoBanco = getLivroById(id);
		LivroDTO livroAtualizadoDTO = new LivroDTO();

		if(livroExistenteNoBanco != null) {
			
			livroExistenteNoBanco = toEntidade(livroDTO);
			
			Livro livroAtualizado = livroRepository.save(livroExistenteNoBanco);
			
			livroAtualizadoDTO = toDTO(livroAtualizado);
		}
		return livroAtualizadoDTO;
	}
	
	public Livro toEntidade (LivroDTO livroDTO) {
		Livro livro = new Livro();
		
		livro.setNomeLivro(livroDTO.getNomeLivro());
		livro.setNomeAutor(livroDTO.getNomeAutor());
		livro.setDataLancamento(livroDTO.getDataLancamento());
		livro.setCodigoIsbn(livroDTO.getCodigoIsbn());		
		
		return livro;
	}
	
	public LivroDTO toDTO(Livro livro) {
		LivroDTO livroDTO = new LivroDTO();
			
		livroDTO.setCodigoLivro(livro.getCodigoLivro());
		livroDTO.setNomeLivro(livro.getNomeLivro());
		livroDTO.setNomeAutor(livro.getNomeAutor());
		livroDTO.setDataLancamento(livro.getDataLancamento());
		livroDTO.setCodigoIsbn(livro.getCodigoIsbn());
		
		return livroDTO;
	}
	
	public Livro updateLivro(Livro livro, Integer id) {
		
		Livro livroExistenteNoBanco = getLivroById(id);
		
		livroExistenteNoBanco.setNomeLivro(livro.getNomeLivro());
		livroExistenteNoBanco.setNomeAutor(livro.getNomeAutor());
		livroExistenteNoBanco.setDataLancamento(livro.getDataLancamento());
		livroExistenteNoBanco.setCodigoIsbn(livro.getCodigoIsbn());
		
		return livroRepository.save(livroExistenteNoBanco);
	}
	
	public Livro deleteLivro(Integer id) {
		livroRepository.deleteById(id);
		return getLivroById(id);
	}
	
	public List<LivroDTO> getAllLivrosEmprestimosDTO(){
		List<Livro> listaLivro = livroRepository.findAll();
		List<LivroDTO> listaLivroDTO = new ArrayList<>();
		
		for(Livro livro : listaLivro) {
			LivroDTO livroDTO = toDTO(livro);
			List<Emprestimo> listaEmprestimos = new ArrayList<>();
			List<EmprestimoDTO> listaEmprestimosDTO = new ArrayList<>();
			
			listaEmprestimos = emprestimoRepository.findByLivro(livro);
			
			for(Emprestimo emprestimo : listaEmprestimos) {
				EmprestimoDTO emprestimoDTO = emprestimoService.toDTO(emprestimo);
				listaEmprestimosDTO.add(emprestimoDTO);
			}
			livroDTO.setListaEmprestimosDTO(listaEmprestimosDTO);
			listaLivroDTO.add(livroDTO);
		}
		
		return listaLivroDTO;
	}
}
