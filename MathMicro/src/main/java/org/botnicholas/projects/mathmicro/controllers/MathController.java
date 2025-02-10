package org.botnicholas.projects.mathmicro.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.botnicholas.projects.mathmicro.models.Question;
import org.botnicholas.projects.mathmicro.services.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MathController {
  @Autowired private MathService mathService;

  @GetMapping("/questions")
  public List<Question> getRandomQuestion(@RequestParam int amount) {
    ArrayList<Question> questions = new ArrayList<>();

    return Stream.generate(() -> mathService.getRandom()).limit(amount).toList();

//    for (int i=0; i< amount; i++) {
//      questions.add(mathService.getRandom());
//    }
//    return questions;
  }
}
