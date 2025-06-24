package com.example.Taller.Service;

import com.example.Taller.Entity.ClienteEntity;

import java.util.List;

public interface ClienteService {
    ClienteEntity guardarCliente(ClienteEntity cliente);
    ClienteEntity obtenerClientePorId(int id);
    List<ClienteEntity> obtenerTodosLosClientes();
    String eliminarCliente(int id);
}
