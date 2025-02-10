package org.botnicholas.projects.euricadiscoverymicro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EuricaDiscoveryMicroApplication {

  public static void main(String[] args) {
    SpringApplication.run(EuricaDiscoveryMicroApplication.class, args);
  }

}
