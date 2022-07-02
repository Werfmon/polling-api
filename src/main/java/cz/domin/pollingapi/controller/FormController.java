package cz.domin.pollingapi.controller;

import cz.domin.pollingapi.controller.dto.FullFormDTO;
import cz.domin.pollingapi.controller.dto.NewFormDTO;
import cz.domin.pollingapi.controller.dto.NewFullFormDTO;
import cz.domin.pollingapi.controller.dto.UpdateFormDTO;
import cz.domin.pollingapi.model.Form;
import cz.domin.pollingapi.model.Person;
import cz.domin.pollingapi.model.Question;
import cz.domin.pollingapi.service.FormService;
import cz.domin.pollingapi.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/form")
@AllArgsConstructor
@Slf4j
public class FormController {
    private final FormService formService;
    private final PersonService personService;
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
    public Map<String, String> createFullForm(@RequestBody NewFullFormDTO newFullFormDTO) {
        return formService.createFullForm(newFullFormDTO);
    }
    @GetMapping("/join/{form_token}")
    public FullFormDTO joinToForm(Authentication authentication, @PathVariable(name = "form_token") String formToken) throws Exception {
        String username = "";
        try {
            username = authentication.getName();
        } catch (NullPointerException exception) {
            log.error("Cannot get user from Authentication");
        }
        Person person = personService.getPersonByUsername(username);
        return formService.joinForm(formToken, person);
    }
    @PostMapping("/{form_token}/result/save")
    public Map<String, Number> saveFormResult(Authentication authentication, @RequestBody HashMap<String, Integer> result, @PathVariable(name = "form_token") String token) {
        String username = "";
        try {
            username = authentication.getName();
        } catch (NullPointerException exception) {
            log.error("Cannot get user from Authentication");
        }
        Person person = personService.getPersonByUsername(username);
        return this.formService.saveFormResult(result, token, person);
    }
}
