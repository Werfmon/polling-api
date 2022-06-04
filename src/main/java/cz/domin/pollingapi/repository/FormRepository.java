package cz.domin.pollingapi.repository;

import cz.domin.pollingapi.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface FormRepository extends JpaRepository<Form, Long> {
}
