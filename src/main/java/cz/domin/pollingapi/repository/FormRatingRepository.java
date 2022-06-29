package cz.domin.pollingapi.repository;

import cz.domin.pollingapi.model.FormRating;
import cz.domin.pollingapi.model.FormRatingKey;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FormRatingRepository extends JpaRepository<FormRating, FormRatingKey> {
}
