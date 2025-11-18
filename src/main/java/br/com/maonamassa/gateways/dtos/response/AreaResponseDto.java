package br.com.maonamassa.gateways.dtos.response;

import br.com.maonamassa.domains.Area;

import java.util.UUID;

public record AreaResponseDto(
        UUID id,
        String nome
) {
    public static AreaResponseDto fromArea(Area area) {
        return new AreaResponseDto(
                area.getId(),
                area.getNome()
        );
    }
}
