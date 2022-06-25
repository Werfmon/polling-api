package cz.domin.pollingapi.controller;

import cz.domin.pollingapi.controller.dto.NewFormDTO;
import cz.domin.pollingapi.controller.dto.NewFullFormDTO;
import cz.domin.pollingapi.controller.dto.UpdateFormDTO;
import cz.domin.pollingapi.model.Form;
import cz.domin.pollingapi.service.FormService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/form")
@AllArgsConstructor
public class FormController {
    private final FormService formService;
    @GetMapping("/{id}")
    public Form getFormById(@PathVariable Long id) {
        return formService.getFormById(id);
    }
    @GetMapping("/all")
    public List<Form> getAllForms() {
        return formService.getAllForms();
    }
    @PostMapping
    public Form create(@RequestBody NewFormDTO newFormDTO) {
        return formService.create(newFormDTO);
    }
    @PutMapping("/{id}")
    public Form update(@PathVariable Long id, @RequestBody UpdateFormDTO updateFormDTO) {
        return formService.update(id, updateFormDTO);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return formService.delete(id);
    }

    @PostMapping("/create")
    public Form createFullForm(@RequestBody NewFullFormDTO newFullFormDTO) {
        return formService.createFullForm(newFullFormDTO);
    }
}
