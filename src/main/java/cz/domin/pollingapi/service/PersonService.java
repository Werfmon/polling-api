package cz.domin.pollingapi.service;

import cz.domin.pollingapi.controller.dto.NewPersonDTO;
import cz.domin.pollingapi.controller.dto.UpdatePersonDTO;
import cz.domin.pollingapi.model.Person;
import cz.domin.pollingapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.hibernate.sql.Update;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PersonService implements UserDetailsService {
    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    public Person create(NewPersonDTO newPersonDTO) {
        Person person = modelMapper.map(newPersonDTO, Person.class);
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Person with id " + id + " not found"));
    }
    public Person update(Long id, UpdatePersonDTO updatePersonDTO) {
        Person updatedPerson = modelMapper.map(updatePersonDTO, Person.class);
        updatedPerson.setPassword(this.getPersonById(id).getPassword());
        updatedPerson.setId(id);
        return personRepository.save(updatedPerson);
    }
    public String delete(Long id) {
        personRepository.delete(this.getPersonById(id));
        return "Person was deleted successfully";
    }
    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }
    public Person getPersonByUsername(String username) {
        return personRepository.findPersonByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found with username: " + username));
    }
    public String updatePersonPassword(Long id, String password) {
        Person person = this.getPersonById(id);
        person.setPassword(bCryptPasswordEncoder.encode(password));
        personRepository.save(person);
        return "Password of " + person.getUsername() + " was updated";
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = this.getPersonByUsername(username);
        return new User(person.getUsername(), person.getPassword(), new ArrayList<>());
    }
}
