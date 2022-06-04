package cz.domin.pollingapi.controller;

import cz.domin.pollingapi.controller.dto.NewPersonDTO;
import cz.domin.pollingapi.controller.dto.UpdatePersonDTO;
import cz.domin.pollingapi.model.Person;
import cz.domin.pollingapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {
    private final PersonService personService;
    @PostMapping
    public Person create(@RequestBody NewPersonDTO newPersonDTO) {
        return personService.create(newPersonDTO);
    }
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }
    @PutMapping("/{id}")
    public Person update(@PathVariable Long id, @RequestBody UpdatePersonDTO updatePersonDTO){
        return personService.update(id, updatePersonDTO);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return personService.delete(id);
    }

    @GetMapping("/all")
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }
    @PutMapping("/{id}/password/{password}")
    public String updatePersonPassword(@PathVariable Long id, @PathVariable String password) {
        return personService.updatePersonPassword(id, password);
    }
}
