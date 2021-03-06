package cz.domin.pollingapi.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table
public class Answer {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column
    private String text;
    @Column
    private Boolean correct = false;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
