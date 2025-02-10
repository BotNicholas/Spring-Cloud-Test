package org.botnicholas.projects.examinatormicro.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.botnicholas.projects.examinatormicro.models.Exam;
import org.botnicholas.projects.examinatormicro.models.Question;
import org.botnicholas.projects.examinatormicro.models.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ExamController {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private DiscoveryClient discoveryClient;

  @PostMapping("/exam")
  public Exam getExam(@RequestBody Map<String, Integer> spec) {
    List<Section> sections = spec.entrySet().stream()
        .map(this::getUrl)
        .map(url -> restTemplate.getForObject(url, Question[].class))
        .map(Arrays::asList)
        .map(Section::new)
        .toList();

//    return Exam.builder().title("EXAM").sections(sections).build();
    return new Exam("EXAM", sections);
  }

  private String getUrl(Entry<String, Integer> entry) {
    // We can use
//    discoveryClient like this (Without @LoadBalanced):
//    var host = discoveryClient.getInstances(entry.getKey()).get(0).getHost();
//    var port = discoveryClient.getInstances(entry.getKey()).get(0).getPort();
//
//    return "http://" + host + ":" + port + "/api/questions?amount=" + entry.getValue();


    // Or we can do a way easier
    // we can just add an interceptor for rest template by adding an annotation @LoadBalanced above RestTemplate
    // it knows, that if in the URL we have "mathmicro" or "historymicro", they must be replaced with correspondin url:post from eurica...
    return "http://" + entry.getKey() + "/api/questions?amount=" + entry.getValue();
  }
}
