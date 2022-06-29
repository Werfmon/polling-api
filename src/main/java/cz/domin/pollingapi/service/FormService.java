package cz.domin.pollingapi.service;

import cz.domin.pollingapi.controller.dto.*;
import cz.domin.pollingapi.model.*;
import cz.domin.pollingapi.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class FormService {
    private final FormRepository formRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final PersonRepository personRepository;
    private final FormRatingRepository formRatingRepository;
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
    public Map<String, String> createFullForm(NewFullFormDTO newFullFormDTO) {
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

        while (newFullQuestionDTOIterator.hasNext()) {
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

        return Map.of("token", form.getFormToken());
    }
    public Form getFormByFormToken(String formToken) {
        return formRepository.findFormByFormToken(formToken)
                .orElseThrow(() -> new IllegalArgumentException("Form with token: " + formToken + " not found"));
    }
    public FullFormDTO joinForm(String formToken, Person person) {
        Form form;
        try {
            form = this.getFormByFormToken(formToken);
        } catch (IllegalArgumentException e) {
            log.error("Form not found");
            return null;
        }
        // TODO: pokud bude nejaky zaznam o tom, ze se uzivatel zurcastnil dotazniku, nepusti ho to k nemu: DONE
        // TODO: (pokud nebude, zapise jej do tabulky): DONE, ze se zucatnil a vrati cely dotaznik
        long filled = person.getForms().stream()
                .filter(f -> f.getFormToken().equals(formToken))
                .count();
        if(filled < 1) {
            List<Form> forms = person.getForms();
            forms.add(form);
            person.setForms(forms);
            personRepository.save(person);

            List<Person> people = form.getPeople();
            people.add(person);
            form.setPeople(people);
            formRepository.save(form);
        }
        return modelMapper.map(form, FullFormDTO.class);
    }
    public Map<String, Number> saveFormResult(HashMap<String, Integer> result, Long id, Person person) {
        final Integer points = result.get("result");

        FormRatingKey formRatingKey = new FormRatingKey();
        Form form = this.getFormById(id);

        formRatingKey.setFormId(form.getId());
        formRatingKey.setPersonId(person.getId());

        FormRating formRating = new FormRating();
        formRating.setForm(form);
        formRating.setPerson(person);
        formRating.setRating(points);
        formRating.setId(formRatingKey);

        this.formRatingRepository.save(formRating);

        HashMap<String, Number> response = new HashMap<>();

        response.put("formId", form.getId());
        response.put("personId", person.getId());
        response.put("rating", points);

        return response;
    }
}
