package org.botnicholas.projects.mathmicro.services;

import java.util.Random;
import org.botnicholas.projects.mathmicro.models.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class MathService {
  private Random random = new Random();
  @Value("${max.number.value:1}")
  private int max;

  public Question getRandom() {
    int a = random.nextInt(max);
    int b = random.nextInt(max);
    return Question.builder().question(a + "+" + b + " = ?").answer(String.valueOf(a+b)).build();
  }
}
