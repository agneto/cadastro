package br.com.neto.cadastro;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CadastroApplication {

    public static void main(String[] args) {
    	TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(CadastroApplication.class, args);
    }

}
