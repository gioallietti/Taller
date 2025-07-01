package com.example.Taller.Service;

import com.example.Taller.Entity.ClienteEntity;
import com.example.Taller.Repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteEntity guardarCliente(ClienteEntity cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public ClienteEntity obtenerClientePorId(int id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + id));
    }

    @Override
    public List<ClienteEntity> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public String eliminarCliente(int id) {
        if (!clienteRepository.existsById(id)) {
            throw new EntityNotFoundException("El cliente con id " + id + " no existe");
        }
        clienteRepository.deleteById(id);
        return "Cliente eliminado correctamente";
    }

    @Override
    public ClienteEntity actualizarCliente(int id, ClienteEntity cliente) {
        if (!clienteRepository.existsById(id)) {
            throw new EntityNotFoundException("El cliente con id " + id + " no existe");
        }
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }
}
