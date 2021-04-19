package mytest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import mytest.models.Pagamento;
import mytest.repository.PagamentoRepository;

@Service
public class PagamentoService {
	
	@Autowired
	private PagamentoRepository repository;
	
	@Autowired
	private RestTemplate rt;

	public Iterable<Contrato> obterTodos() {
		return repository.findAll();
	}
	
	public void salvar(Pagamento contrato) {
		repository.save(contrato);
	}

	public void remover(String id) {
		repository.deleteById(id);
	}
	
	public PagamentoDTO buscarPagamentos(String next_cursor) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<HttpHeaders> entity = new HttpEntity<HttpHeaders>(headers);
			String uri = "https://platform.brexapis.com/interview/v1/transactions";
			return ResponseEntity <PagamentoDTO> response = rt.exchange(
					uri,
					HttpMethod.GET,
					entity,
					PagamentoDTO.class);
			return response.getBody();
		} catch(RestClientException e){
			return null
		}
	}
}
