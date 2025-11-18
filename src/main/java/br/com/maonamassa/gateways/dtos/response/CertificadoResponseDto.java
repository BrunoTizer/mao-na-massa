package br.com.maonamassa.gateways.dtos.response;

import br.com.maonamassa.domains.Certificado;

import java.time.LocalDate;
import java.util.UUID;

public record CertificadoResponseDto(
        UUID id,
        UsuarioResponseDto usuario,
        CursoResponseDto curso,
        Double notaFinal,
        LocalDate dataConclusao,
        String codigoCertificado
) {
    public static CertificadoResponseDto fromCertificado(Certificado certificado) {
        return new CertificadoResponseDto(
                certificado.getId(),
                UsuarioResponseDto.fromUsuario(certificado.getUsuario()),
                CursoResponseDto.fromCurso(certificado.getCurso()),
                certificado.getNotaFinal(),
                certificado.getDataConclusao(),
                certificado.getCodigoCertificado()
        );
    }
}
