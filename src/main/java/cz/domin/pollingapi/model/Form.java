package cz.domin.pollingapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Form {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column
    private String formToken = UUID.randomUUID().toString();
    @Column
    private String name;
    @Column
    private String description;
    @ManyToMany(mappedBy = "forms", fetch = FetchType.LAZY)
    private List<Person> people = new ArrayList<>();
    @OneToMany(mappedBy = "form")
    private List<Question> questions = new ArrayList<>();
}
