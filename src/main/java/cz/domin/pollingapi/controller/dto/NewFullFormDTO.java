package cz.domin.pollingapi.controller.dto;

import cz.domin.pollingapi.model.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NewFullFormDTO {
    private String name;
    private String description;
    private List<NewFullQuestionDTO> questions;
}
