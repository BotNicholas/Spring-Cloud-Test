package org.botnicholas.projects.examinatormicro.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
public class Section {
  private List<Question> questions;

  public Section() {
  }

  public Section(List<Question> questions) {
    this.questions = questions;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(
      List<Question> questions) {
    this.questions = questions;
  }
}
