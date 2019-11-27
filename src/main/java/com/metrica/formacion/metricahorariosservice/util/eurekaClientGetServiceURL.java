package com.metrica.formacion.metricahorariosservice.util;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class eurekaClientGetServiceURL {

    @Autowired
    private EurekaClient eurekaClient;

    private String getDireccionController() {

        return null;
    }

    public String getURL(String serviceURI, String direccion) {

        Application application = eurekaClient.getApplication(serviceURI);


        if(application != null){

            InstanceInfo instanceInfo = application.getInstances().get(0);

            String url = "http://" + instanceInfo.getIPAddr() + ":" +
                    instanceInfo.getPort() + "/" + direccion;

            log.info("url --> " + url);

            return url;
        }
        else{

            return null;
        }

    }

    public String getURL(String serviceURI, String direccion, String parametro) {

        Application application = eurekaClient.getApplication(serviceURI);

        if(application != null){

            InstanceInfo instanceInfo = application.getInstances().get(0);

            String url = "http://" + instanceInfo.getIPAddr() + ":" +
                    instanceInfo.getPort() + "/" + direccion + parametro;

            log.info("url --> " + url);

            return url;
        }

        else{
            return null;
        }
    }
}
