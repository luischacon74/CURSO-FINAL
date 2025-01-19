package curso.foro.foroAlura.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import curso.foro.foroAlura.domain.rol.EnumRol;

public record DatosCrearUsuario(
        @NotBlank (message = "El campo Usuario no puede estar vacio")
        String usuario,
        @Email
        @NotBlank (message = "El campo Email no puede estar vacio")
        String email,
        @NotBlank
        String contrasena,
        EnumRol rol
) {
}
