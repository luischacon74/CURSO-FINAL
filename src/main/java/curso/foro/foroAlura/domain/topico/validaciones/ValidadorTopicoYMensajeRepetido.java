package curso.foro.foroAlura.domain.topico.validaciones;

import curso.foro.foroAlura.ValidacionException;
import curso.foro.foroAlura.domain.topico.DatosCrearTopico;
import curso.foro.foroAlura.domain.topico.Topico;
import curso.foro.foroAlura.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ValidadorTopicoYMensajeRepetido {
    @Autowired
    TopicoRepository topicoRepository;
    public void validar(DatosCrearTopico datos){
        var topicoExiste = topicoRepository.topicoYMensajeRepetido(datos.tituloTopico(), datos.mensajeTopico());
        System.out.println(topicoExiste);
        if (topicoExiste.isPresent()) {
            throw new ValidacionException("El topico que desea crear ya existe");
        }


    }

}
