package br.com.maonamassa.gateways.dtos.response;

import br.com.maonamassa.domains.Certificado;

import java.time.LocalDate;
import java.util.UUID;

public record CertificadoResponseDto(
        UUID id,
        UUID usuarioId,
        UUID cursoId,
        Double notaFinal,
        LocalDate dataConclusao,
        String codigoCertificado
) {
    public static CertificadoResponseDto fromCertificado(Certificado certificado) {
        return new CertificadoResponseDto(
                certificado.getId(),
                certificado.getUsuario().getId(),
                certificado.getCurso().getId(),
                certificado.getNotaFinal(),
                certificado.getDataConclusao(),
                certificado.getCodigoCertificado()
        );
    }
}
