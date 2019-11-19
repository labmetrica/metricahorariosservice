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
import org.springframework.web.bind.annotation.ResponseBody;
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

    private HttpHeaders headers = new HttpHeaders();

    @Override
    public void borrarUsuario(Integer id) {
        restTemplate.delete(restCliente.getURL(servicioUsuarios, "/clientes/borrarUsuario",id.toString()));
    }

    @Override
    public ResponseEntity<usuarios> guardarUsuario(usuariosDTO usuariosDTO) {

        String url = restCliente.getURL(servicioUsuarios,"/clientes/guardarUsuario");

        return restTemplate.postForEntity(url,usuariosMapper.tousuario(usuariosDTO), usuarios.class);
    }

    @Override
    public List<usuariosDTO> listarUsuarios() {

        String url = restCliente.getURL(servicioUsuarios,"/clientes/lista-clientes");

        return usuariosMapper.tousuariosDTOs(getListaClientes(url));
    }

    @Override
    public ResponseEntity<usuarios> actualizarUsuarios(usuariosDTO usuarios) {

        String url = restCliente.getURL(servicioUsuarios,"/clientes/guardarUsuario");

        return restTemplate.postForEntity(url,usuariosMapper.tousuario(usuarios), usuarios.class);
    }

    @Override
    public usuariosDTO buscarPorID(Integer id) {

        String url = restCliente.getURL(servicioUsuarios,"/clientes/buscarPorID/",id.toString());

        return usuariosMapper.tousuariosDTO(restTemplate.getForObject(url,usuarios.class));
    }

    @Override
    public List<usuariosDTO> buscarPorGrupo(Integer id) {

        String url = restCliente.getURL(servicioUsuarios, "/clientes/buscarPorGrupo/",id.toString());

        return usuariosMapper.tousuariosDTOs(getListaClientes(url));
    }

    @Override
    public List<usuariosDTO> buscarPorNombre(String nombre) {

        String url = restCliente.getURL(servicioUsuarios,"/clientes/buscarPorNombre/",nombre);

        return usuariosMapper.tousuariosDTOs(getListaClientes(url));
    }

    @Override
    public List<usuariosDTO> buscarPorApellido(String apellido) {

        String url = restCliente.getURL(servicioUsuarios,"/clientes/buscarPorApellido/",apellido);

        return usuariosMapper.tousuariosDTOs(getListaClientes(url));
    }

    @Override
    public List<usuariosDTO> buscarPorNombreoApellido(String nombre, String apellido) {
        return null;
    }

    private List<usuarios> getListaClientes(String url){

        HttpEntity<List<usuarios>> entity = new HttpEntity<>(null,headers);

        ParameterizedTypeReference<List<usuarios>> responseType = new ParameterizedTypeReference<List<usuarios>>() {};

        ResponseEntity<List<usuarios>> resp = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);

        return resp.getBody();
    }
}
