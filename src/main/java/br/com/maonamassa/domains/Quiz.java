package br.com.maonamassa.domains;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@With
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private String pergunta;
    private String respostaCorreta;

}
