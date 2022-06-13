package cz.domin.pollingapi.controller;

import cz.domin.pollingapi.controller.dto.NewPersonDTO;
import cz.domin.pollingapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final PersonService personService;

    @PostMapping("/registration")
    public void registerPerson(@RequestBody NewPersonDTO newPersonDTO) {
        personService.create(newPersonDTO);
    }
}
