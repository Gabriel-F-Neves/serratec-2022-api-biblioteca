package br.com.residencia.biblioteca.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.residencia.biblioteca.dto.ConsultaCnpjDTO;
import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.dto.LivroDTO;
import br.com.residencia.biblioteca.dto.imgbb.ImgBBDTO;
import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.repository.EditoraRepository;
import br.com.residencia.biblioteca.repository.LivroRepository;

@Service
public class EditoraService {
	@Autowired
	EditoraRepository editoraRepository;
	
	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	LivroService livroService;
	
	@Autowired
	EmailService emailService;
	
	@Value("${imgbb.host.url}")
	private String imgBBHostUrl;
	
	@Value("${imgbb.host.key}")
    private String imgBBHostKey;
	
	public List<Editora> getAllEditoras(){
		return editoraRepository.findAll();
	}
	
	public List<EditoraDTO> getAllEditorasDTO(){
		List<EditoraDTO> editorasDTO = new ArrayList<>();
		for(Editora editora : editoraRepository.findAll()) {
			editorasDTO.add(this.toDTO(editora));
		}
		return editorasDTO;
	}
	
	public Editora getEditoraById(Integer id) {
		return editoraRepository.findById(id).get();
		//return editoraRepository.findById(id).orElse(null);
	}
	
	public Editora saveEditora(Editora editora) {
		return editoraRepository.save(editora);
	}
	
	public Editora saveEditoraFromApi(String cnpj) {
		ConsultaCnpjDTO consultaCnpjDTO = consultaCnpjApiExterna(cnpj);
		
		Editora editora = new Editora();
		editora.setNome(consultaCnpjDTO.getNome());
		
		return editoraRepository.save(editora);
	}
	
	public EditoraDTO saveEditoraDTO(EditoraDTO editoraDTO) {
		Editora editora = toEntidade(editoraDTO);
		Editora novaEditora = editoraRepository.save(editora);
		
		EditoraDTO editoraAtualizadaDTO = toDTO(novaEditora);		
		return editoraAtualizadaDTO;
	}
	
	public EditoraDTO saveEditoraFoto(
			String editoraTxt,
			MultipartFile file
	) throws IOException {
				
			RestTemplate restTemplate = new RestTemplate();
			String serverUrl = imgBBHostUrl + imgBBHostKey;
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			
			MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
			
			ContentDisposition contentDisposition = ContentDisposition
					.builder("form-data")
					.name("image")
					.filename(file.getOriginalFilename())
					.build();
			
			fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
			
			HttpEntity<byte[]> fileEntity = new HttpEntity<>(file.getBytes(), fileMap);
			
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("image", fileEntity);
			
			HttpEntity<MultiValueMap<String, Object>> requestEntity =
					new HttpEntity<>(body, headers);
			
			ResponseEntity<ImgBBDTO> response = null;
			ImgBBDTO imgDTO = new ImgBBDTO();
			Editora novaEditora = new Editora(); 
			try {
				response = restTemplate.exchange(
						serverUrl,
						HttpMethod.POST,
						requestEntity,
						ImgBBDTO.class);
				
				imgDTO = response.getBody();
				System.out.println("ImgBBDTO: " + imgDTO.getData().toString());
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
			}
			
			//Converte os dados da editora recebidos no formato String em Entidade
			//  Coleta os dados da imagem, após upload via API, e armazena na Entidade Editora
			if(null != imgDTO) {
				Editora editoraFromJson = convertEditoraFromStringJson(editoraTxt);
				editoraFromJson.setImagemFileName(imgDTO.getData().getImage().getFilename());
				editoraFromJson.setImagemNome(imgDTO.getData().getTitle());
				editoraFromJson.setImagemUrl(imgDTO.getData().getUrl());
				novaEditora = editoraRepository.save(editoraFromJson);
			}
			
			return toDTO(novaEditora);
	}
	
	
	private Editora convertEditoraFromStringJson(String editoraJson) {
		Editora editora = new Editora();
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			editora = objectMapper.readValue(editoraJson, Editora.class);
		} catch (IOException err) {
			System.out.printf("Ocorreu um erro ao tentar converter a string json para um instância da entidade Editora", err.toString());
		}
		
		return editora;
	}

	public EditoraDTO updateEditoraDTO(EditoraDTO editoraDTO, Integer id) {
		Editora editoraExistenteNoBanco = getEditoraById(id);
		EditoraDTO editoraAtualizadaDTO = new EditoraDTO();
		editoraDTO.setCodigoEditora(id);

		if(editoraExistenteNoBanco != null) {
			
			editoraExistenteNoBanco = toEntidade(editoraDTO);
			
			Editora editoraAtualizada = editoraRepository.save(editoraExistenteNoBanco);
			
			editoraAtualizadaDTO = toDTO(editoraAtualizada);
		}
		emailService.sendEmail("gab@yahoo.com", "Teste de envio de email", editoraAtualizadaDTO.toString());
		return editoraAtualizadaDTO;
	}
	
	private Editora toEntidade (EditoraDTO editoraDTO) {
		Editora editora = new Editora();
		
		editora.setNome(editoraDTO.getNome());
		editora.setCodigoEditora(editoraDTO.getCodigoEditora());
		
		return editora;
	}
	
	private EditoraDTO toDTO(Editora editora) {
		EditoraDTO editoraDTO = new EditoraDTO();
		/*	
		editoraDTO.setCodigoEditora(editora.getCodigoEditora());
		editoraDTO.setNome(editora.getNome());
		*/
		editoraDTO.setCodigoEditora(editora.getCodigoEditora());
		editoraDTO.setNome(editora.getNome());
		editoraDTO.setImagemFileName(editora.getImagemFileName());
		editoraDTO.setImagemNome(editora.getImagemNome());
		editoraDTO.setImagemUrl(editora.getImagemUrl());
		
		return editoraDTO;
	}
	
	public Editora updateEditora(Editora editora, Integer id) {
		
		Editora editoraExistenteNoBanco = getEditoraById(id);
		
		editoraExistenteNoBanco.setNome(editora.getNome());
				
		
		return editoraRepository.save(editoraExistenteNoBanco);
	}
	
	public ConsultaCnpjDTO consultaCnpjApiExterna(String cnpj) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://receitaws.com.br/v1/cnpj/{cnpj}";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("cnpj", cnpj);
		
		ConsultaCnpjDTO consultaCnpjDTO = restTemplate.getForObject(uri, ConsultaCnpjDTO.class, params);
		
		return consultaCnpjDTO;
	}
	
	public Editora deleteEditora(Integer id) {
		editoraRepository.deleteById(id);
		return getEditoraById(id);
	}
	
	public List<EditoraDTO> getAllEditorasLivrosDTO(){
		List<Editora> listaEditora = editoraRepository.findAll();
		List<EditoraDTO> listaEditoraDTO = new ArrayList<>();
		
		
		for(Editora editora : listaEditora) {
			EditoraDTO editoraDTO = toDTO(editora);
			List<Livro> listaLivros = new ArrayList<>();
			List<LivroDTO> listaLivrosDTO = new ArrayList<>();
			
			listaLivros = livroRepository.findByEditora(editora);
			for(Livro livro : listaLivros) {
				LivroDTO livroDTO = livroService.toDTO(livro);
				listaLivrosDTO.add(livroDTO);
			}
			editoraDTO.setListaLivrosDTO(listaLivrosDTO);
			
			listaEditoraDTO.add(editoraDTO);
		}
		return listaEditoraDTO;
	}
}
