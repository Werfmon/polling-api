package cz.domin.pollingapi.repository;

import cz.domin.pollingapi.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    Optional<Form> findFormByFormToken(String formToken);
}
