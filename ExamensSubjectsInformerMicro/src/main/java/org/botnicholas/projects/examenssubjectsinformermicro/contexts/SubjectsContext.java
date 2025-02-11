package org.botnicholas.projects.examenssubjectsinformermicro.contexts;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SubjectsContext {
  private List<String> subjects = new ArrayList<>();

  public List<String> getSubjects() {
    return subjects;
  }

  public void setSubjects(List<String> subjects) {
    this.subjects = subjects;
  }
}
