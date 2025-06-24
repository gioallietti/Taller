package com.example.Taller.Entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("CLIENTE")
public class ClienteEntity extends PersonaEntity{

}
