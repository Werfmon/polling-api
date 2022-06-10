package cz.domin.pollingapi.controller;

import cz.domin.pollingapi.controller.dto.NewQuestionDTO;
import cz.domin.pollingapi.controller.dto.UpdateQuestionDTO;
import cz.domin.pollingapi.model.Question;
import cz.domin.pollingapi.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }
    @GetMapping("/all")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }
    @PostMapping
    public Question create(@RequestBody NewQuestionDTO newQuestionDTO) {
        return questionService.create(newQuestionDTO);
    }
    @PutMapping("/{id}")
    public Question update(@PathVariable Long id, @RequestBody UpdateQuestionDTO updateQuestionDTO){
        return questionService.update(id, updateQuestionDTO);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return questionService.delete(id);
    }
}
