package com.metrica.formacion.metricahorariosservice.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.metrica.formacion.metricahorariosservice.dto.gruposMapper;
import com.metrica.formacion.metricahorariosservice.dto.usuariosMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import lombok.extern.log4j.Log4j2;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.metrica.formacion.metricahorariosservice.dto.gruposDTO;
import com.metrica.formacion.metricahorariosservice.dto.usuariosDTO;
import com.metrica.formacion.metricahorariosservice.entity.grupos;
import com.metrica.formacion.metricahorariosservice.entity.usuarios;
import com.metrica.formacion.metricahorariosservice.service.gruposService;
import com.metrica.formacion.metricahorariosservice.service.usuariosService;

import apiexcelmetrica.modelo.Grupo;
import apiexcelmetrica.modelo.Usuario;
import apiexcelmetrica.util.ExcelUtils;

@Log4j2
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RefreshScope
@RestController
@CacheConfig
@RequestMapping("/serviceMetrica")
public class HorariosDashboard {

	@Value("@{servicioUsuarios.name}")
	private String servicioUsuarios;

	@Value("@{servicioGrupos.name}")
	private String servicioGrupos;

	@Autowired
	private usuariosService usuariosService;

	@Autowired
	private gruposService gruposService;

	/**/

	@CachePut(value = "usuarios")
	@GetMapping("/lista-clientes")
	public List<usuariosDTO> listarUsuarios() {

		return usuariosService.listarUsuarios();
	}

	@CachePut(value = "grupos")
	@GetMapping("/lista-grupos")
	public List<gruposDTO> listarGrupos() {

		return gruposService.listarGrupos();
	}

	@CachePut(value = "horarios")
	@GetMapping("/lista-horarios")
	public List<gruposDTO> listaHorarios() {

		return getGruposMasUsuarios(gruposService.listarGrupos(), usuariosService.listarUsuarios());
	}

//-------------------------------

	/* GET */

	// Clientes

	@GetMapping("/buscarUsuarioPorId/{key}")
	public usuariosDTO buscarUsuarioPorId(@PathVariable("key") Integer id) {

		return usuariosService.buscarPorID(id);
	}

	@CachePut(value = "buscarUsuarioPorNombre")
	@GetMapping("/buscarUsuarioPorNombre/{key}")
	public List<usuariosDTO> buscarUsuarioPorNombre(@PathVariable("key") String nombre) {

		return usuariosService.buscarPorNombre(nombre);
	}

	@GetMapping("/buscarUsuarioPorApellido/{key}")
	public List<usuariosDTO> buscarUsuarioPorApellido(@PathVariable("key") String apellido) {

		return usuariosService.buscarPorApellido(apellido);
	}

	@CachePut(value = "buscarUsuarioPorGrupo")
	@GetMapping("/buscarUsuarioPorGrupo/{key}")
	public List<usuariosDTO> buscarUsuarioPorGrupo(@PathVariable("key") Integer id) {

		return usuariosService.buscarPorGrupo(id);
	}

	// grupos

	@CachePut(value = "buscarGrupoPorId")
	@GetMapping("/buscarGrupoPorId/{key}")
	public gruposDTO buscarGrupoPorId(@PathVariable("key") Integer id) {

		gruposDTO grupo = gruposService.buscarPorID(id);

		try {
			grupo.setListaUsuarios(buscarUsuarioPorGrupo(id));
		} catch (NullPointerException e) {
			grupo.setListaUsuarios(null);
			log.error("Servicio de usuarios no disponible");
		}
		return grupo;
	}

	@CachePut(value = "buscarGrupoPorNombre")
	@GetMapping("/buscarGrupoPorNombre/{key}")
	public gruposDTO buscarGrupoPorNombre(@PathVariable("key") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime nombre) {

		gruposDTO grupo = gruposService.buscarPorNombre(nombre);

		try {
			grupo.setListaUsuarios(buscarUsuarioPorGrupo(grupo.getId()));
		} catch (NullPointerException e) {
			grupo.setListaUsuarios(null);
			log.error("Servicio de usuarios no disponible");
		}
		return grupo;
	}

	/* POST */

	@HystrixCommand(fallbackMethod = "fallbackResponse")
	@PostMapping("/guardarUsuario")
	public ResponseEntity<usuarios> guardarusuario(@RequestBody usuariosDTO usuarios) {

		return usuariosService.guardarUsuario(usuarios);
	}

	@HystrixCommand(fallbackMethod = "fallbackResponse")
	@PostMapping("/guardarGrupo")
	public ResponseEntity<grupos> guardarusuario(@RequestBody gruposDTO grupos) {

		return gruposService.guardarGrupo(grupos);
	}

	/* PUT */

	@PutMapping("/actualizarUsuario")
	public ResponseEntity<usuarios> actualizarUsuario(@RequestBody usuariosDTO usuarios) {

		return usuariosService.actualizarUsuarios(usuarios);
	}

	@PutMapping("/actualizarGrupo")
	public ResponseEntity<grupos> actualizargrupo(@RequestBody gruposDTO grupos) {

		return gruposService.actualizarGrupo(grupos);
	}

	/* DELETE */

	@DeleteMapping("/borrarUsuario/{key}")
	public void borrarUsuario(@PathVariable("key") Integer id) {

		usuariosService.borrarUsuario(id);
	}

	@DeleteMapping("borrarGrupo/{key}")
	public void borrarGrupo(@PathVariable("key") Integer id) {

		gruposService.borrarGrupo(id);
	}

	private List<gruposDTO> getGruposMasUsuarios(List<gruposDTO> gruposDTOS, List<usuariosDTO> usuariosDTOS) {

		for (gruposDTO grupoDTOS: gruposDTOS) {

			for (usuariosDTO usuarioDTO: usuariosDTOS) {

				if(grupoDTOS.getId() == usuarioDTO.getGrupo()){

					grupoDTOS.agregarUsuario(usuarioDTO);
				}
			}
		}

		return gruposDTOS;
	}

	/* EXCEL */

	@GetMapping("/generarExcel")
	public ResponseEntity<InputStreamResource> excelUsuarioReport() throws IOException {

		System.out.println("CONECTADOOOOOOOOOO");

		final List<gruposDTO> grupoDTO = getGruposMasUsuarios(gruposService.listarGrupos(),
				usuariosService.listarUsuarios());

		final List<Grupo> grupos = new ArrayList<>();

		for (int i = 0; i < grupoDTO.size(); i++) {
			final int id = grupoDTO.get(i).getId();
			final LocalTime nombre = grupoDTO.get(i).getNombre();
			final int huecos = grupoDTO.get(i).getHuecos();
			final Grupo grupo = new Grupo(id, nombre, huecos);

			grupo.setListaUsuarios(tranformaListaUsuario(grupoDTO.get(i).getListaUsuarios()));
			grupos.add(grupo);

		}

		final ByteArrayInputStream in = ExcelUtils.generarExcel(grupos, "ExcelTurnos");

		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=usuarios.xlsx");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

	private List<Usuario> tranformaListaUsuario(List<usuariosDTO> listaUsuariosDTO) {
		final List<Usuario> listaUsuarios = new ArrayList<>();

		for (int i = 0; i < listaUsuariosDTO.size(); i++) {

			final int id = listaUsuariosDTO.get(i).getId();
			final String nombre = listaUsuariosDTO.get(i).getNombre();
			final String apellido = listaUsuariosDTO.get(i).getApellido();
			final Usuario usuario = new Usuario(id, nombre, apellido);

			listaUsuarios.add(usuario);
		}

		return listaUsuarios;
	}

	/*Hystrix*/

	public ResponseEntity<grupos> fallbackResponse (gruposDTO dto){

		return new ResponseEntity<grupos>(gruposMapper.INSTANCE.togrupos(dto), HttpStatus.SERVICE_UNAVAILABLE);
	}

	public ResponseEntity<usuarios> fallbackResponse (usuariosDTO dto){

		return new ResponseEntity<usuarios>(usuariosMapper.INSTANCE.tousuario(dto), HttpStatus.SERVICE_UNAVAILABLE);
	}

}
