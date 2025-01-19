package curso.foro.foroAlura.domain.respuesta;

import jakarta.validation.constraints.NotNull;

public record DatosEditarRespuesta(
        @NotNull
        String mensajeRespuestaEditado
) {
}
