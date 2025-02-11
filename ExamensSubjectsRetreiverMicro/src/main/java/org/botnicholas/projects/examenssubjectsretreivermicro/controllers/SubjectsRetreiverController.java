package org.botnicholas.projects.examenssubjectsretreivermicro.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SubjectsRetreiverController {
  @Autowired
  DiscoveryClient discoveryClient;

  @Autowired
  private StreamBridge streamBridge;

  @GetMapping("/subjects")
  public List<String> getSubjects() {
    List<String> subjects = discoveryClient.getServices();

    streamBridge.send("output", subjects); //here output - is binding name from application.properties

    return subjects;
  }
}
