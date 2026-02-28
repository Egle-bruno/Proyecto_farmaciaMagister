package com.cibertec.farmacia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// Esta línea es la que "conecta" los cables entre tus paquetes
@ComponentScan(basePackages = {"com.cibertec.farmacia"}) 
public class SistemaFarmaciaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SistemaFarmaciaApplication.class, args);
    }
}