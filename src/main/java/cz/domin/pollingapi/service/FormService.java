package cz.domin.pollingapi.service;

import cz.domin.pollingapi.controller.dto.NewFormDTO;
import cz.domin.pollingapi.controller.dto.UpdateFormDTO;
import cz.domin.pollingapi.model.Form;
import cz.domin.pollingapi.repository.FormRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FormService {
    private final FormRepository formRepository;
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
}
