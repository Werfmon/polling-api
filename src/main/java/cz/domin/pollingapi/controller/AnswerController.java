package cz.domin.pollingapi.controller;

import cz.domin.pollingapi.controller.dto.NewAnswerDTO;
import cz.domin.pollingapi.controller.dto.UpdateAnswerDTO;
import cz.domin.pollingapi.model.Answer;
import cz.domin.pollingapi.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
@AllArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    @GetMapping("/all")
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }
    @GetMapping("/{id}")
    public Answer getAnswerById(@PathVariable Long id) {
        return answerService.getAnswerById(id);
    }
    @PostMapping
    public Answer create(@RequestBody NewAnswerDTO newAnswerDTO) {
        return answerService.create(newAnswerDTO);
    }
    @PutMapping("/{id}")
    public Answer update(@PathVariable Long id, @RequestBody UpdateAnswerDTO updateAnswerDTO) {
        return answerService.update(id, updateAnswerDTO);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return answerService.delete(id);
    }
}
