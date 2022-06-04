package cz.domin.pollingapi.repository;

import cz.domin.pollingapi.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;

interface FormRepository extends JpaRepository<Form, Long> {
}
