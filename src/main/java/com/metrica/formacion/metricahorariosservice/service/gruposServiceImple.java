package com.metrica.formacion.metricahorariosservice.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.metrica.formacion.metricahorariosservice.dto.gruposDTO;
import com.metrica.formacion.metricahorariosservice.dto.gruposMapper;
import com.metrica.formacion.metricahorariosservice.entity.grupos;
import com.metrica.formacion.metricahorariosservice.util.eurekaClientGetServiceURL;

@Service
public class gruposServiceImple implements gruposService {

	@Autowired
	private eurekaClientGetServiceURL restCliente;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private gruposMapper gruposMapper;

	@Value("${servicioUsuarios.name}")
	private String servicioUsuarios;

	@Value("${servicioGrupos.name}")
	private String servicioGrupos;

	HttpHeaders headers = new HttpHeaders();

	@Override
	public void borrarGrupo(Integer id) {
		restTemplate.delete(restCliente.getURL(servicioGrupos, "/grupos/borrarPorId", id.toString()));
	}

	@Override
	public ResponseEntity<grupos> guardarGrupo(gruposDTO grupos) {

		String url = restCliente.getURL(servicioGrupos, "/grupos/crearGrupo");

		return restTemplate.postForEntity(url, gruposMapper.togrupos(grupos), grupos.class);
	}

	@Override
	public List<gruposDTO> listarGrupos() {

		String url = restCliente.getURL(servicioGrupos, "/grupos/lista-grupos");

		return gruposMapper.togruposDTOs(getListaGrupos(url));
	}

	@Override
	public ResponseEntity<grupos> actualizarGrupo(gruposDTO grupos) {

		String url = restCliente.getURL(servicioGrupos, "/grupos/actualizarGrupo");

		return restTemplate.postForEntity(url, gruposMapper.togrupos(grupos), grupos.class);
	}

	@Override
	public gruposDTO buscarPorID(Integer id) {

		String url = restCliente.getURL(servicioGrupos, "/grupos/buscarPorId/" + id);

		return gruposMapper.togruposDTO(restTemplate.getForObject(url, grupos.class));
	}

	@Override
	public gruposDTO buscarPorNombre(LocalTime localTime) {

		String url = restCliente.getURL(servicioGrupos, "/grupos/buscarPorNombre/", localTime.toString());

		return gruposMapper.INSTANCE.togruposDTO(restTemplate.getForObject(url, grupos.class));
	}

	private List<grupos> getListaGrupos(String url) {

		HttpEntity<List<grupos>> entity = new HttpEntity<>(null, headers);

		ParameterizedTypeReference<List<grupos>> responseType = new ParameterizedTypeReference<List<grupos>>() {
		};

		ResponseEntity<List<grupos>> resp = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);

		return resp.getBody();
	}

}
