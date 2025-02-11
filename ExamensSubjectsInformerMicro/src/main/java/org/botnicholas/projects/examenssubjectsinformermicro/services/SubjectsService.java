package org.botnicholas.projects.examenssubjectsinformermicro.services;

import java.util.List;
import java.util.function.Consumer;
import org.botnicholas.projects.examenssubjectsinformermicro.contexts.SubjectsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class SubjectsService {
  @Autowired
  private SubjectsContext context;


//  The name must be the same as in spring.cloud.stream.bindings property...
  @Bean
  public Consumer<List<String>> subjectsProcessor() {
    return subjects -> {
      System.out.println("Received: " + subjects);
      context.setSubjects(subjects);
    };
  }
}
