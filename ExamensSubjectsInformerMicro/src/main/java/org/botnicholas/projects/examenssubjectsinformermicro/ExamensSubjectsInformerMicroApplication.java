package org.botnicholas.projects.examenssubjectsinformermicro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ExamensSubjectsInformerMicroApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExamensSubjectsInformerMicroApplication.class, args);
  }

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

}
