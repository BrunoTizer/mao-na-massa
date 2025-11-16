package br.com.maonamassa.gateways.dtos.request;

import br.com.maonamassa.domains.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioRequestDto {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    private String senha;

    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;

    private String areaInteresse;

    @NotBlank(message = "Tipo de usuário é obrigatório")
    private String tipoUsuario;

    public Usuario toUsuario() {
        return Usuario.builder()
                .nome(this.nome)
                .email(this.email)
                .senha(this.senha)
                .cidade(this.cidade)
                .areaInteresse(this.areaInteresse)
                .tipoUsuario(this.tipoUsuario)
                .dataCriacao(LocalDate.now())
                .build();
    }
}
