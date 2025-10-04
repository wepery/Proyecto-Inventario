package com.example.backend.entity;


import javax.persistence.*;

@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @Column(name = "tr_codigo", nullable = false, unique = true, length = 4)
    private Long codigo;

    @Column(name = "tr_nombre", nullable = false, length = 100)
    private String nombre;

    public Rol() {
    }

    public Rol(String nombre, Long codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
