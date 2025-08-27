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
        if(cliente.getNombre().length() < 2 || cliente.getNombre().length() > 20 || cliente.getApellido().length() < 2 || cliente.getApellido().length() > 20 ){
            throw new IllegalArgumentException("El nombre y el apellido deben tener al menos 2 letras o maximo 20.");
        }

        if (cliente.getTelefono().length() < 6 || cliente.getTelefono().length() > 15){
            throw new IllegalArgumentException("Solo puede tener entre 6 y 15 numeros.");
        }

        if (!cliente.getNombre().matches("^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$") || !cliente.getApellido().matches("^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$")) {
            throw new IllegalArgumentException("El nombre y el apellido solo pueden tener letras.");
        }

        if (!cliente.getTelefono().matches("^\\+?[0-9]+$")){
            throw new IllegalArgumentException("Solo se pueden poner numeros y el simbolo +");
        }

        if (clienteRepository.findByCedula(cliente.getCedula()) != null){
            throw new IllegalArgumentException("La cedula/RUT ya existe");
        }


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
