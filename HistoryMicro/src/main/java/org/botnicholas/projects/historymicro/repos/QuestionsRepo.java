package org.botnicholas.projects.historymicro.repos;

import org.botnicholas.projects.historymicro.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(collectionResourceRel = "questions", path = "questions")
public interface QuestionsRepo extends JpaRepository<Question, Integer> {
}
