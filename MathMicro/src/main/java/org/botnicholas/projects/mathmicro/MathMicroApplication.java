package org.botnicholas.projects.mathmicro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient <-- Not required in new versions of Spring Boot
public class MathMicroApplication {

  public static void main(String[] args) {
    SpringApplication.run(MathMicroApplication.class, args);
  }

}
