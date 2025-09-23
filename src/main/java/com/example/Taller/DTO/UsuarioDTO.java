package com.example.Taller.DTO;

import com.example.Taller.Entity.PersonaEntity;
import com.example.Taller.Entity.TipoUsuarioEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

public class UsuarioDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private TipoUsuarioEntity tipoUsuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoUsuarioEntity getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuarioEntity tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
