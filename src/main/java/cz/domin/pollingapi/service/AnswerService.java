package cz.domin.pollingapi.service;

import cz.domin.pollingapi.controller.dto.NewAnswerDTO;
import cz.domin.pollingapi.controller.dto.UpdateAnswerDTO;
import cz.domin.pollingapi.model.Answer;
import cz.domin.pollingapi.repository.AnswerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;

    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Answer with id " + id + " not found"));
    }
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }
    public Answer create(NewAnswerDTO newAnswerDTO) {
        Answer answer = modelMapper.map(newAnswerDTO, Answer.class);
        return answerRepository.save(answer);
    }
    public Answer update(Long id, UpdateAnswerDTO updateAnswerDTO) {
        Answer answer = this.getAnswerById(id);
        answer.setText(updateAnswerDTO.getText());
        answer.setCorrect(updateAnswerDTO.getCorrect());
        return answerRepository.save(answer);
    }
    public String delete(Long id) {
        answerRepository.delete(this.getAnswerById(id));
        return "Answer with id " + id + " was deleted successfully";
    }
}
