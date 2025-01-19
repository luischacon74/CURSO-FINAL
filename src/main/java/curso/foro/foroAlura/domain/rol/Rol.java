package curso.foro.foroAlura.domain.rol;

import jakarta.persistence.*;
import curso.foro.foroAlura.domain.usuario.Usuario;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "rol")
@EqualsAndHashCode(of = "id")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING) // Almacena el valor del enum como cadena en la base de datos
    @Column(nullable = false, unique = true)
    private EnumRol rol;

    // Relación inversa muchos a muchos con Usuario
    @ManyToMany(mappedBy = "rol")
    private List<Usuario> usuarios = new ArrayList<>();

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Long getId() {
        return id;
    }

    public EnumRol getRol() {
        return rol;
    }

    // Métodos auxiliares
    public void agregarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        usuario.getRoles().add(this);
    }

    public void removerUsuario(Usuario usuario) {
        this.usuarios.remove(usuario);
        usuario.getRoles().remove(this);
    }
}
