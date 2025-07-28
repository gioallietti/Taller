package com.example.Taller.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "equipos")
public class EquipoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private TipoEquipoEntity tipoEquipo;

    @ManyToOne(optional = false)
    private MarcaEntity marca;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoEquipoEntity getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(TipoEquipoEntity tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public MarcaEntity getMarca() {
        return marca;
    }

    public void setMarca(MarcaEntity marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
