package com.example.Taller.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class ClienteEntity extends PersonaEntity{

}
