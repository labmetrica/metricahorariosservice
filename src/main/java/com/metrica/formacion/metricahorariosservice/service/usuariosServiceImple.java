package com.metrica.formacion.metricahorariosservice.service;

import com.metrica.formacion.metricahorariosservice.dto.usuariosDTO;
import com.metrica.formacion.metricahorariosservice.dto.usuariosMapper;
import com.metrica.formacion.metricahorariosservice.entity.usuarios;
import com.metrica.formacion.metricahorariosservice.util.eurekaClientGetServiceURL;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Log4j2
@Service
public class usuariosServiceImple implements usuariosService {

    @Autowired
    private eurekaClientGetServiceURL restCliente;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private usuariosMapper usuariosMapper;

    @Value("${servicioUsuarios.name}")
    private String servicioUsuarios;

    @Value("${servicioGrupos.name}")
    private String servicioGrupos;

    HttpHeaders headers = new HttpHeaders();

    @Override
    public void borrarUsuario(Integer id) {
        restTemplate.delete(restCliente.getURL(servicioUsuarios, "",id.toString()));
    }

    @Override
    public void guardarUsuario(usuariosDTO usuariosDTO) {

        String url = restCliente.getURL(servicioUsuarios,"");

        //restTemplate.postForObject(url,usuariosMapper.tousuario(usuariosDTO), ResponseEntity.class);
    }

    @Override
    public List<usuariosDTO> listarUsuarios() {

        String url = restCliente.getURL(servicioUsuarios,"servicioUsuarios.listaClientes");

        HttpEntity<List<usuarios>> entity = new HttpEntity<>(null,headers);

        ParameterizedTypeReference<List<usuarios>> responseType = new ParameterizedTypeReference<List<usuarios>>() {};

        ResponseEntity<List<usuarios>> resp = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);

        return usuariosMapper.tousuariosDTOs(resp.getBody());
    }

    @Override
    public void actualizarUsuarios(usuarios usuarios) {

        String url = restCliente.getURL("api-usuarios","servicioUsuarios.guardarUsuario");

    }

    @Override
    public usuariosDTO buscarPorID(Integer id) {
        return null;
    }

    @Override
    public List<usuariosDTO> buscarPorNomre(String nombre) {
        return null;
    }
}
