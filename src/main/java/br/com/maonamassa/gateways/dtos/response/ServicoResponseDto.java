package br.com.maonamassa.gateways.dtos.response;

import br.com.maonamassa.domains.Servico;

import java.time.LocalDate;
import java.util.UUID;

public record ServicoResponseDto(
        UUID id,
        ProfissionalResponseDto profissional,
        String titulo,
        String descricao,
        String cidade,
        Double preco,
        LocalDate dataPublicacao
) {
    public static ServicoResponseDto fromServico(Servico servico) {
        return new ServicoResponseDto(
                servico.getId(),
                ProfissionalResponseDto.fromProfissional(servico.getProfissional()),
                servico.getTitulo(),
                servico.getDescricao(),
                servico.getCidade(),
                servico.getPreco(),
                servico.getDataPublicacao()
        );
    }
}
