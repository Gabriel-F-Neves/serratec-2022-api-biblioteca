package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.AlunoDTO;
import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.repository.AlunoRepository;

@Service
public class AlunoService {
	@Autowired
	AlunoRepository alunoRepository;
	
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
		//return alunoRepository.findById(id).get();
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

		if(alunoExistenteNoBanco != null) {
			
			alunoExistenteNoBanco = toEntidade(alunoDTO);
			
			Aluno alunoAtualizado = alunoRepository.save(alunoExistenteNoBanco);
			
			alunoAtualizadoDTO = toDTO(alunoAtualizado);
		}
		return alunoAtualizadoDTO;
	}
	
	private Aluno toEntidade (AlunoDTO alunoDTO) {
		Aluno aluno = new Aluno();
		
		aluno.setNome(alunoDTO.getNome());
		aluno.setDataNascimento(alunoDTO.getDataNascimento());
		aluno.setCpf(alunoDTO.getCpf());
		aluno.setLogradouro(alunoDTO.getLogradouro());
		aluno.setNumeroLogradouro(alunoDTO.getNumeroLogradouro());
		aluno.setComplemento(alunoDTO.getComplemento());
		aluno.setBairro(alunoDTO.getBairro());
		aluno.setCidade(alunoDTO.getCidade());
		
		return aluno;
	}
	
	private AlunoDTO toDTO(Aluno aluno) {
		AlunoDTO alunoDTO = new AlunoDTO();
			
		alunoDTO.setNumeroMatriculaAluno(aluno.getNumeroMatriculaAluno());
		alunoDTO.setNome(aluno.getNome());
		alunoDTO.setDataNascimento(aluno.getDataNascimento());
		alunoDTO.setCpf(aluno.getCpf());
		alunoDTO.setLogradouro(aluno.getLogradouro());
		alunoDTO.setNumeroLogradouro(aluno.getNumeroLogradouro());
		alunoDTO.setComplemento(aluno.getComplemento());
		alunoDTO.setBairro(aluno.getBairro());
		alunoDTO.setCidade(aluno.getCidade());
		
		return alunoDTO;
	}
	
	public Aluno updateAluno(Aluno aluno, Integer id) {
		
		Aluno alunoExistenteNoBanco = getAlunoById(id);
		
		alunoExistenteNoBanco.setBairro(aluno.getBairro());
		alunoExistenteNoBanco.setCidade(aluno.getCidade());
		alunoExistenteNoBanco.setComplemento(aluno.getComplemento());
		alunoExistenteNoBanco.setCpf(aluno.getCpf());
		alunoExistenteNoBanco.setDataNascimento(aluno.getDataNascimento());
	//	alunoExistenteNoBanco.setEmprestimos(aluno.getEmprestimos());
		alunoExistenteNoBanco.setLogradouro(aluno.getLogradouro());
		alunoExistenteNoBanco.setNome(aluno.getNome());
		alunoExistenteNoBanco.setNumeroLogradouro(aluno.getNumeroLogradouro());
		
		return alunoRepository.save(alunoExistenteNoBanco);
	}
	
	public Aluno deleteAluno(Integer id) {
		alunoRepository.deleteById(id);
		return getAlunoById(id);
	}
	
}
