package br.com.maonamassa.gateways.dtos.request;

import br.com.maonamassa.domains.Certificado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CertificadoRequestDto {

    @NotNull(message = "ID do usuário é obrigatório")
    private UUID usuarioId;

    @NotNull(message = "ID do curso é obrigatório")
    private UUID cursoId;

    @NotNull(message = "Nota final é obrigatória")
    private Double notaFinal;

    @NotBlank(message = "Código do certificado é obrigatório")
    private String codigoCertificado;

    public Certificado toCertificado() {
        return Certificado.builder()
                .notaFinal(this.notaFinal)
                .dataConclusao(LocalDate.now())
                .codigoCertificado(this.codigoCertificado)
                .build();
    }
}
