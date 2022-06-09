package cz.domin.pollingapi.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateAnswerDTO {
    private String text;
    private Boolean correct;
}
