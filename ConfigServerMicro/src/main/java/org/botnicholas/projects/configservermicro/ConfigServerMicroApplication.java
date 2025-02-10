package org.botnicholas.projects.configservermicro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerMicroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerMicroApplication.class, args);
    }

}
