package cz.domin.pollingapi.repository;

import cz.domin.pollingapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
