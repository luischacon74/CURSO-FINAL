package curso.foro.foroAlura.domain.usuario;

import jakarta.persistence.*;
import curso.foro.foroAlura.domain.rol.Rol;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Entity
@Table(name= "usuarios")
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String email;
    private String clave;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private List<Rol> rol = new ArrayList<>();

    public Usuario() {
    }

    public List<Rol> getRoles() {
        return rol;
    }

    public Usuario(DatosCrearUsuario parametro) {

        this.login = parametro.usuario();
        this.email = parametro.email();
        this.clave = parametro.contrasena();
        /*this.perfiles = parametro.rol()*/

    }
    // Métodos auxiliares para manejar perfiles
    public void agregarRol(Rol rol) {
        this.rol.add(rol);
        rol.getUsuarios().add(this); // Sincroniza la relación inversa
    }

    public void removerPerfil(Rol rol) {
        this.rol.remove(rol);
        rol.getUsuarios().remove(this); // Sincroniza la relación inversa
    }


    public Long getid() {
        return id;
    }

    public String getemail() {
        return email;
    }

    public String getLogin() {
        return login;
    }
    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }*/

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rol.stream()
                .map(perfil -> new SimpleGrantedAuthority("ROLE_" + perfil.getRol().name()))
                .toList();
    }

}
