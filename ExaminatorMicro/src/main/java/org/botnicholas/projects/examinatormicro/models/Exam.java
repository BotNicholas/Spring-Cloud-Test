package org.botnicholas.projects.examinatormicro.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class Exam {
  private String title;
  private List<Section> sections;

  public Exam() {
  }

  public Exam(String title, List<Section> sections) {
    this.title = title;
    this.sections = sections;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Section> getSections() {
    return sections;
  }

  public void setSections(List<Section> sections) {
    this.sections = sections;
  }
}
