package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.EmprestimoDTO;
import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;

@Service
public class EmprestimoService {
	@Autowired
	EmprestimoRepository emprestimoRepository;
	
	public List<Emprestimo> getAllEmprestimos(){
		return emprestimoRepository.findAll();
	}
	
	public List<EmprestimoDTO> getAllEmprestimosDTO(){
		List<EmprestimoDTO> emprestimosDTO = new ArrayList<>();
		for(Emprestimo emprestimo : emprestimoRepository.findAll()) {
			emprestimosDTO.add(this.toDTO(emprestimo));
		}
		return emprestimosDTO;
	}
	
	public Emprestimo getEmprestimoById(Integer id) {
		return emprestimoRepository.findById(id).orElse(null);
	}
	
	public Emprestimo saveEmprestimo(Emprestimo emprestimo) {
		return emprestimoRepository.save(emprestimo);
	}
	
	public EmprestimoDTO saveEmprestimoDTO(EmprestimoDTO emprestimoDTO) {
		Emprestimo emprestimo = toEntidade(emprestimoDTO);
		Emprestimo novoEmprestimo = emprestimoRepository.save(emprestimo);
		
		EmprestimoDTO emprestimoAtualizadoDTO = toDTO(novoEmprestimo);		
		return emprestimoAtualizadoDTO;
	}
	
	public EmprestimoDTO updateEmprestimoDTO(EmprestimoDTO emprestimoDTO, Integer id) {
		Emprestimo emprestimoExistenteNoBanco = getEmprestimoById(id);
		EmprestimoDTO emprestimoAtualizadoDTO = new EmprestimoDTO();
		emprestimoDTO.setCodigoEmprestimo(id);

		if(emprestimoExistenteNoBanco != null) {
			
			emprestimoExistenteNoBanco = toEntidade(emprestimoDTO);
			
			Emprestimo emprestimoAtualizado = emprestimoRepository.save(emprestimoExistenteNoBanco);
			
			emprestimoAtualizadoDTO = toDTO(emprestimoAtualizado);
		}
		return emprestimoAtualizadoDTO;
	}
	
	public Emprestimo toEntidade (EmprestimoDTO emprestimoDTO) {
		Emprestimo emprestimo = new Emprestimo();
		
		emprestimo.setCodigoEmprestimo(emprestimoDTO.getCodigoEmprestimo());
		emprestimo.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
		emprestimo.setDataEntrega(emprestimoDTO.getDataEntrega());
		
		return emprestimo;
	}
	
	public EmprestimoDTO toDTO (Emprestimo emprestimo) {
		EmprestimoDTO emprestimoDTO = new EmprestimoDTO();
		
		emprestimoDTO.setCodigoEmprestimo(emprestimo.getCodigoEmprestimo());
		emprestimoDTO.setDataEmprestimo(emprestimo.getDataEmprestimo());
		emprestimoDTO.setDataEntrega(emprestimo.getDataEntrega());
		
		return emprestimoDTO;
	}
	
	public Emprestimo updateEmprestimo(Emprestimo emprestimo, Integer id) {
		
		Emprestimo emprestimoExistenteNoBanco = getEmprestimoById(id);
		
		emprestimoExistenteNoBanco.setDataEmprestimo(emprestimo.getDataEmprestimo());
		emprestimoExistenteNoBanco.setDataEntrega(emprestimo.getDataEntrega());
		emprestimoExistenteNoBanco.setValorEmprestimo(emprestimo.getValorEmprestimo());
		
		return emprestimoRepository.save(emprestimoExistenteNoBanco);
	}
	
	public Emprestimo deleteEmprestimo(Integer id) {
		emprestimoRepository.deleteById(id);
		return getEmprestimoById(id);
	}
}
