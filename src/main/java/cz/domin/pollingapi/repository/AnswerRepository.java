package cz.domin.pollingapi.repository;

import cz.domin.pollingapi.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
