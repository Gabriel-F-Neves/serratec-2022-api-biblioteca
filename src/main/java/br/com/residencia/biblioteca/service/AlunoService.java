package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.AlunoDTO;
import br.com.residencia.biblioteca.dto.EmprestimoDTO;
import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.repository.AlunoRepository;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;


@Service
public class AlunoService {
	@Autowired
	AlunoRepository alunoRepository;
	
	@Autowired
	EmprestimoRepository emprestimoRepository;
	
	@Autowired
	EmprestimoService emprestimoService;
	
	public List<Aluno> getAllAlunos(){
		return alunoRepository.findAll();
	}
	
	public List<AlunoDTO> getAllAlunosDTO(){
		List<AlunoDTO> alunosDTO = new ArrayList<>();
		for(Aluno aluno : alunoRepository.findAll()) {
			alunosDTO.add(this.toDTO(aluno));
		}
		return alunosDTO;
	}
	
	public Aluno getAlunoById(Integer id) {
		return alunoRepository.findById(id).orElse(null);
	}
	
	public Aluno saveAluno(Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
	public AlunoDTO saveAlunoDTO(AlunoDTO alunoDTO) {
		Aluno aluno = toEntidade(alunoDTO);
		Aluno novoAluno = alunoRepository.save(aluno);
		
		AlunoDTO alunoAtualizadoDTO = toDTO(novoAluno);		
		return alunoAtualizadoDTO;
	}
	
	public AlunoDTO updateAlunoDTO(AlunoDTO alunoDTO, Integer id) {
		Aluno alunoExistenteNoBanco = getAlunoById(id);
		AlunoDTO alunoAtualizadoDTO = new AlunoDTO();
		alunoDTO.setNumeroMatriculaAluno(id);

		if(alunoExistenteNoBanco != null) {
			
			alunoExistenteNoBanco = toEntidade(alunoDTO);
			
			Aluno alunoAtualizado = alunoRepository.save(alunoExistenteNoBanco);
			
			alunoAtualizadoDTO = toDTO(alunoAtualizado);
		}
		return alunoAtualizadoDTO;
	}
	
	private Aluno toEntidade (AlunoDTO alunoDTO) {
		Aluno aluno = new Aluno();
		
		aluno.setNumeroMatriculaAluno(alunoDTO.getNumeroMatriculaAluno());
		aluno.setNome(alunoDTO.getNome());
		aluno.setCpf(alunoDTO.getCpf());
		
		return aluno;
	}
	
	private AlunoDTO toDTO(Aluno aluno) {
		AlunoDTO alunoDTO = new AlunoDTO();
			
		alunoDTO.setNumeroMatriculaAluno(aluno.getNumeroMatriculaAluno());
		alunoDTO.setNome(aluno.getNome());
		alunoDTO.setCpf(aluno.getCpf());
		
		return alunoDTO;
	}
	
	public Aluno updateAluno(Aluno aluno, Integer id) {
		
		Aluno alunoExistenteNoBanco = getAlunoById(id);
		
		alunoExistenteNoBanco.setBairro(aluno.getBairro());
		alunoExistenteNoBanco.setCidade(aluno.getCidade());
		alunoExistenteNoBanco.setComplemento(aluno.getComplemento());
		alunoExistenteNoBanco.setCpf(aluno.getCpf());
		alunoExistenteNoBanco.setDataNascimento(aluno.getDataNascimento());
		alunoExistenteNoBanco.setLogradouro(aluno.getLogradouro());
		alunoExistenteNoBanco.setNome(aluno.getNome());
		alunoExistenteNoBanco.setNumeroLogradouro(aluno.getNumeroLogradouro());
		
		return alunoRepository.save(alunoExistenteNoBanco);
	}
	
	public Aluno deleteAluno(Integer id) {
		alunoRepository.deleteById(id);
		return getAlunoById(id);
	}
	
	public List<AlunoDTO> getAllAlunosEmprestimosDTO(){
		List<Aluno> listaAluno = alunoRepository.findAll();
		List<AlunoDTO> listaAlunoDTO = new ArrayList<>();
		
		for(Aluno aluno : listaAluno) {
			AlunoDTO alunoDTO = toDTO(aluno);
			List<Emprestimo> listaEmprestimos = new ArrayList<>();
			List<EmprestimoDTO> listaEmprestimosDTO = new ArrayList<>();
			
			listaEmprestimos = emprestimoRepository.findByAluno(aluno);
			for(Emprestimo emprestimo : listaEmprestimos) {
				EmprestimoDTO emprestimoDTO = emprestimoService.toDTO(emprestimo);
				listaEmprestimosDTO.add(emprestimoDTO);
			}
			alunoDTO.setListaEmprestimosDTO(listaEmprestimosDTO);
			listaAlunoDTO.add(alunoDTO);
		}
		
		return listaAlunoDTO;
	}
}
