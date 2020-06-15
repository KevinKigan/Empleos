package com.example.empleos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${empleos.pathImages}")
    private String pathImages;

    /**
     * Metodo para Configurar una ruta externa al proyecto como carpeta de busqueda
     * de los logos de las vacantes
     *
     * @param registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/logos/**").addResourceLocations("file:"+pathImages);
    }

}
