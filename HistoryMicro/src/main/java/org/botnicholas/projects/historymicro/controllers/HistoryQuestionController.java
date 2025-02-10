package org.botnicholas.projects.historymicro.controllers;

import java.util.Collections;
import java.util.List;
import org.botnicholas.projects.historymicro.models.Question;
import org.botnicholas.projects.historymicro.repos.QuestionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HistoryQuestionController {
  @Autowired
  private QuestionsRepo questionsRepo;

  @GetMapping("/questions")
  public List<Question> getQuestions(@RequestParam int amount) {
    List<Question> questions = questionsRepo.findAll();
    Collections.shuffle(questions);

    return questions.stream().limit(amount).toList();
  }
}
