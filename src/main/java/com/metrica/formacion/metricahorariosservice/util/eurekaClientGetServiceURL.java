package com.metrica.formacion.metricahorariosservice.util;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class eurekaClientGetServiceURL {

    @Autowired
    private EurekaClient eurekaClient;

    private String getDireccionController() {

        return null;
    }

    public String getURL(String serviceURI, String direccion) {

        Application application = eurekaClient.getApplication(serviceURI);
        InstanceInfo instanceInfo = application.getInstances().get(0);

        String url = "http://" + instanceInfo.getIPAddr() + ":" +
                instanceInfo.getPort() + "/" + direccion;

        return url;
    }

    public String getURL(String serviceURI, String direccion, String parametro) {

        Application application = eurekaClient.getApplication(serviceURI);
        InstanceInfo instanceInfo = application.getInstances().get(0);

        String url = "http://" + instanceInfo.getIPAddr() + ":" +
                instanceInfo.getPort() + "/" + direccion + parametro;

        return url;
    }
}
