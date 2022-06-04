package cz.domin.pollingapi.repository;

import cz.domin.pollingapi.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
