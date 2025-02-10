package org.botnicholas.projects.mathmicro.services;

import java.util.Random;
import org.botnicholas.projects.mathmicro.models.Question;
import org.springframework.stereotype.Service;

@Service
public class MathService {
  private Random random = new Random();
  private int max = 10;

  public Question getRandom() {
    int a = random.nextInt(max);
    int b = random.nextInt(max);
    return Question.builder().question(a + "+" + b + " = ?").answer(String.valueOf(a+b)).build();
  }
}
