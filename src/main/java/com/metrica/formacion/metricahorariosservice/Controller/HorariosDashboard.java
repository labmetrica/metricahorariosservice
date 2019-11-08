package com.metrica.formacion.metricahorariosservice.Controller;

import com.metrica.formacion.metricahorariosservice.entity.usuarios;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
@RequestMapping("/serviceMetrica")
public class HorariosDashboard {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @Value("servicioUsuarios")
    private String servicioUsuarios;

    @Value("servicioGrupos")
    private String servicioGrupos;

    /*GET*/

    @GetMapping("/buscarPorId/{key}")
    public usuarios buscarPorId(@PathVariable("key") Integer integer) {

        Application application = eurekaClient.getApplication(servicioUsuarios);
        InstanceInfo instanceInfo = application.getInstances().get(0);

        String url = "http://" + instanceInfo.getIPAddr() + ":" +
                instanceInfo.getPort() + "/" + "/clientes/buscarPorID/" + integer;

        usuarios usuarios = restTemplate.getForObject(url, com.metrica.formacion.metricahorariosservice.entity.usuarios.class);

        return usuarios;
    }

    /*POST*/

    /*GET*/

    /*DELETE*/
}
