package br.com.maonamassa.gateways.dtos.request;

import br.com.maonamassa.domains.Servico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ServicoRequestDto {

    @NotNull(message = "ID do profissional é obrigatório")
    private UUID profissionalId;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;

    @NotNull(message = "Preço é obrigatório")
    private Double preco;

    public Servico toServico() {
        return Servico.builder()
                .titulo(this.titulo)
                .descricao(this.descricao)
                .cidade(this.cidade)
                .preco(this.preco)
                .dataPublicacao(LocalDate.now())
                .build();
    }
}
