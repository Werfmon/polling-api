package cz.domin.pollingapi.service;

import cz.domin.pollingapi.controller.dto.NewAnswerDTO;
import cz.domin.pollingapi.controller.dto.NewQuestionDTO;
import cz.domin.pollingapi.controller.dto.UpdateAnswerDTO;
import cz.domin.pollingapi.controller.dto.UpdateQuestionDTO;
import cz.domin.pollingapi.model.Question;
import cz.domin.pollingapi.repository.AnswerRepository;
import cz.domin.pollingapi.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    public Question create(NewQuestionDTO newQuestionDTO) {
        Question question = modelMapper.map(newQuestionDTO, Question.class);
        return questionRepository.save(question);
    }
    public Question getQuestionById(Long id){
        return questionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Question with id " + id + " not found"));
    }
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    public Question update(Long id, UpdateQuestionDTO updateQuestionDTO) {
        Question question = this.getQuestionById(id);
        question.setDescription(updateQuestionDTO.getDescription());
        question.setTitle(updateQuestionDTO.getTitle());
        return questionRepository.save(question);
    }
    public String delete(Long id) {
        questionRepository.delete(this.getQuestionById(id));
        return "Question was deleted successfully";
    }
}
