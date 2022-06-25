package cz.domin.pollingapi.service;

import cz.domin.pollingapi.controller.dto.*;
import cz.domin.pollingapi.model.Answer;
import cz.domin.pollingapi.model.Form;
import cz.domin.pollingapi.model.Question;
import cz.domin.pollingapi.repository.AnswerRepository;
import cz.domin.pollingapi.repository.FormRepository;
import cz.domin.pollingapi.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FormService {
    private final FormRepository formRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;

    public Form getFormById(Long id) {
        return formRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Form with id " + " not found"));
    }
    public List<Form> getAllForms() {
        return formRepository.findAll();
    }
    public Form create(NewFormDTO newFormDTO) {
        Form form = modelMapper.map(newFormDTO, Form.class);
        return formRepository.save(form);
    }
    public Form update(Long id, UpdateFormDTO updateFormDTO) {
        Form updatedForm = this.getFormById(id);
        updatedForm.setDescription(updateFormDTO.getDescription());
        updatedForm.setName(updateFormDTO.getName());
        return formRepository.save(updatedForm);
    }
    public String delete(Long id) {
        formRepository.delete(this.getFormById(id));
        return "Form with id " + id + " was deleted successfully";
    }
    public Form createFullForm(NewFullFormDTO newFullFormDTO){
        Form form = modelMapper.map(newFullFormDTO, Form.class);
        formRepository.save(form);

        List<Question> questions = newFullFormDTO.getQuestions().stream()
                .map(q -> modelMapper.map(q, Question.class))
                .map(question -> {
                    question.setForm(form);
                    return questionRepository.save(question);
                }).toList();

        List<NewFullQuestionDTO> newFullQuestionDTOS = newFullFormDTO.getQuestions();
        Iterator<NewFullQuestionDTO> newFullQuestionDTOIterator = newFullQuestionDTOS.listIterator();
        List<Answer> answers = new ArrayList<>();

        while(newFullQuestionDTOIterator.hasNext()) {
            newFullQuestionDTOIterator
                    .next()
                    .getAnswers()
                    .forEach(a -> answers.add(modelMapper.map(a, Answer.class)));
        }
        for (int i = 0; i < answers.size(); i += 3) {
            answers.get(i).setQuestion(questions.get(i / 3));
            answers.get(i + 1).setQuestion(questions.get(i / 3));
            answers.get(i + 2).setQuestion(questions.get(i / 3));
        }
        answers.forEach(a -> answerRepository.save(a));

        return form;
    }
}
