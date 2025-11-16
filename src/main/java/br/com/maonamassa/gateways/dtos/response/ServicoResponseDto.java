package br.com.maonamassa.gateways.dtos.response;

import br.com.maonamassa.domains.Servico;

import java.time.LocalDate;
import java.util.UUID;

public record ServicoResponseDto(
        UUID id,
        UUID profissionalId,
        String titulo,
        String descricao,
        String cidade,
        Double preco,
        LocalDate dataPublicacao
) {
    public static ServicoResponseDto fromServico(Servico servico) {
        return new ServicoResponseDto(
                servico.getId(),
                servico.getProfissional().getId(),
                servico.getTitulo(),
                servico.getDescricao(),
                servico.getCidade(),
                servico.getPreco(),
                servico.getDataPublicacao()
        );
    }
}
