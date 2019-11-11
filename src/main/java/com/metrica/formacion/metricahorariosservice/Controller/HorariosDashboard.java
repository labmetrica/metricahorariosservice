package com.metrica.formacion.metricahorariosservice.Controller;

import com.metrica.formacion.metricahorariosservice.dto.gruposDTO;
import com.metrica.formacion.metricahorariosservice.dto.usuariosDTO;
import com.metrica.formacion.metricahorariosservice.entity.grupos;
import com.metrica.formacion.metricahorariosservice.entity.usuarios;
import com.metrica.formacion.metricahorariosservice.service.gruposService;
import com.metrica.formacion.metricahorariosservice.service.usuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RefreshScope
@RestController
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

    @GetMapping("/lista-clientes")
    public List<usuariosDTO> listarUsuarios(){

        return usuariosService.listarUsuarios();
    }

    @GetMapping("/lista-grupos")
    public List<gruposDTO> listarGrupos(){

        return gruposService.listarGrupos();
    }

    @GetMapping("/lista-horarios")
    public List<gruposDTO> listaHorarios() {

        return getGruposMasUsuarios(gruposService.listarGrupos(),usuariosService.listarUsuarios());
    }

    /*GET*/

    //Clientes

    @GetMapping("/buscarUsuarioPorId/{key}")
    public usuariosDTO buscarUsuarioPorId(@PathVariable("key") Integer id) {

        return usuariosService.buscarPorID(id);
    }

    @GetMapping("/buscarUsuarioPorNombre/{key}")
    public List<usuariosDTO> buscarUsuarioPorNombre(@PathVariable("key") String nombre ) {

        return usuariosService.buscarPorNombre(nombre);
    }

    @GetMapping("/buscarUsuarioPorApellido/{key}")
    public List<usuariosDTO> buscarUsuarioPorApellido(@PathVariable("key") String apellido) {

        return usuariosService.buscarPorApellido(apellido);
    }

    @GetMapping("/buscarUsuarioPorGrupo/{key}")
    public List<usuariosDTO> buscarUsuarioPorGrupo(@PathVariable("key") Integer id){

        return usuariosService.buscarPorGrupo(id);
    }

    //grupos

    @GetMapping("/buscarGrupoPorId/{key}")
    public gruposDTO buscarGrupoPorId(@PathVariable("key") Integer id) {

        return gruposService.buscarPorID(id);
    }

    @GetMapping("/buscarGrupoPorNombre/{key}")
    public gruposDTO buscarGrupoPorNombre(@PathVariable("key") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime nombre) {

        return gruposService.buscarPorNombre(nombre);
    }

    /*POST*/

    @PostMapping("/guardarUsuario")
    public ResponseEntity<usuarios> guardarusuario(@RequestBody usuariosDTO usuarios) {

        return usuariosService.guardarUsuario(usuarios);
    }

    @PostMapping("/guardarGrupo")
    public ResponseEntity<grupos> guardarusuario(@RequestBody gruposDTO grupos) {

        return gruposService.guardarGrupo(grupos);
    }

    /*PUT*/

    @PutMapping("/actualizarUsuario")
    public ResponseEntity<usuarios> actualizarUsuario(@RequestBody usuariosDTO usuarios){

        return usuariosService.actualizarUsuarios(usuarios);
    }

    @PutMapping("/actualizarGrupo")
    public ResponseEntity<grupos> actualizargrupo(@RequestBody gruposDTO grupos){

        return gruposService.actualizarGrupo(grupos);
    }

    /*DELETE*/

    @DeleteMapping("/borrarUsuario/{key}")
    public void borrarUsuario(@PathVariable("key") Integer id) {

        usuariosService.borrarUsuario(id);
    }

    @DeleteMapping("borrarGrupo/{key}")
    public void borrarGrupo(@PathVariable("key") Integer id){

        gruposService.borrarGrupo(id);
    }

    private List<gruposDTO> getGruposMasUsuarios(List<gruposDTO> gruposDTOS, List<usuariosDTO> usuariosDTOS){

        for(int i = 0; i < gruposDTOS.size(); i ++){

            for(int e = 0; e < usuariosDTOS.size(); e ++){

                if(gruposDTOS.get(i).getId() == usuariosDTOS.get(e).getGrupo()){

                    gruposDTOS.get(i).agregarUsuario(usuariosDTOS.get(e));
                }
            }
        }

        return gruposDTOS;
    }
}
