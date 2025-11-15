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
@Table(name = "aulas")
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private String titulo;
    private String conteudo;
    private Integer ordem;

}
