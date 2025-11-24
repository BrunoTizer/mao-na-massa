package br.com.maonamassa.services;

import br.com.maonamassa.domains.Curso;
import br.com.maonamassa.domains.Quiz;
import br.com.maonamassa.gateways.CursoRepository;
import br.com.maonamassa.gateways.QuizRepository;
import br.com.maonamassa.gateways.dtos.request.QuizRequestDto;
import br.com.maonamassa.gateways.dtos.response.QuizResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final CursoRepository cursoRepository;

    public Page<QuizResponseDto> listarTodos(Pageable pageable) {
        return quizRepository.findAll(pageable)
                .map(QuizResponseDto::fromQuiz);
    }

    public QuizResponseDto buscarPorId(UUID id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz não encontrado"));
        return QuizResponseDto.fromQuiz(quiz);
    }

    public QuizResponseDto criar(QuizRequestDto dto) {
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Quiz quiz = dto.toQuiz().withCurso(curso);
        Quiz salvo = quizRepository.save(quiz);
        return QuizResponseDto.fromQuiz(salvo);
    }

    public QuizResponseDto atualizar(UUID id, QuizRequestDto dto) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz não encontrado"));

        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Quiz atualizado = quiz
                .withCurso(curso)
                .withPergunta(dto.getPergunta())
                .withRespostaCorreta(dto.getRespostaCorreta());

        Quiz salvo = quizRepository.save(atualizado);
        return QuizResponseDto.fromQuiz(salvo);
    }

    public void deletar(UUID id) {
        if (!quizRepository.existsById(id)) {
            throw new RuntimeException("Quiz não encontrado");
        }
        quizRepository.deleteById(id);
    }
}
