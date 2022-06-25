package cz.domin.pollingapi.controller.dto;

import cz.domin.pollingapi.model.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NewFullQuestionDTO {
    private String title;
    private String description;
    private List<NewAnswerDTO> answers;
}
