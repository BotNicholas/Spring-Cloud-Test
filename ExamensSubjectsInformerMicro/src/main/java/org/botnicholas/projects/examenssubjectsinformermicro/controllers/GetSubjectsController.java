package org.botnicholas.projects.examenssubjectsinformermicro.controllers;

import java.util.List;
import org.botnicholas.projects.examenssubjectsinformermicro.contexts.SubjectsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class GetSubjectsController {
  private static final String HOST = "examenssubjectsretreivermicro";
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private SubjectsContext subjectsContext;

  @GetMapping("/get-subgects-sync")
  public List<String> getSubjectsSync() throws InterruptedException {
    HttpEntity<String> entity = new HttpEntity<>(null);
    restTemplate.exchange("http://" + HOST + "/api/subjects", HttpMethod.GET, null, List.class);
//    subjectsContext.setSubjects(restTemplate.getForObject("http://" + HOST + "/api/subjects", List.class));
    Thread.sleep(2000);
    return subjectsContext.getSubjects();
  }

}
