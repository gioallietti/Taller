package com.example.Taller.Service;

import com.example.Taller.Entity.ClienteEntity;
import com.example.Taller.Entity.MarcaEntity;
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
        if(cliente.getNombre().length() < 2 || cliente.getNombre().length() > 20 ||
                cliente.getApellido().length() < 2 || cliente.getApellido().length() > 20 ){
            throw new IllegalArgumentException("El nombre y el apellido deben tener al menos 2 letras o máximo 20.");
        }

        if (cliente.getTelefono().length() < 6 || cliente.getTelefono().length() > 15){
            throw new IllegalArgumentException("Solo puede tener entre 6 y 15 números.");
        }

        if (!cliente.getNombre().matches("^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$") ||
                !cliente.getApellido().matches("^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$")) {
            throw new IllegalArgumentException("El nombre y el apellido solo pueden tener letras.");
        }

        if (!cliente.getTelefono().matches("^\\+?[0-9]+$")){
            throw new IllegalArgumentException("Solo se pueden poner números y el símbolo +");
        }

        if(cliente.getTelefono2() != null && !cliente.getTelefono2().isEmpty()) {
            if (!cliente.getTelefono2().matches("^\\+?[0-9]+$")) {
                throw new IllegalArgumentException("Solo se pueden poner números y el símbolo + en el segundo teléfono");
            }

            if (cliente.getTelefono2().length() < 6 || cliente.getTelefono2().length() > 15) {
                throw new IllegalArgumentException("Solo puede tener entre 6 y 15 números el segundo teléfono.");
            }
        }

        ClienteEntity clienteExistente = clienteRepository.findByCedula(cliente.getCedula());

        if (clienteExistente != null) {
            if (Boolean.FALSE.equals(clienteExistente.getActivo())) {
                clienteExistente.setActivo(true);
                clienteExistente.setNombre(cliente.getNombre());
                clienteExistente.setApellido(cliente.getApellido());
                clienteExistente.setDireccion(cliente.getDireccion());
                clienteExistente.setCiudad(cliente.getCiudad());
                clienteExistente.setPais(cliente.getPais());
                clienteExistente.setTelefono(cliente.getTelefono());
                clienteExistente.setTelefono2(cliente.getTelefono2());
                return clienteRepository.save(clienteExistente);
            } else {
                String nombreCompleto = clienteExistente.getNombre() + " " + clienteExistente.getApellido();
                throw new IllegalArgumentException("La cédula/RUT ya existe y es: " + nombreCompleto);
            }
        }

        cliente.setActivo(true);
        return clienteRepository.save(cliente);
    }
    @Override
    public ClienteEntity obtenerClientePorId(int id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + id));
    }

    @Override
    public List<ClienteEntity> obtenerTodosLosClientes() {
        return clienteRepository.findAllByActivoTrue();
    }


    public String eliminarCliente(int id) {
        ClienteEntity cliente = this.clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El cliente con id " + id + " no existe"));
        cliente.setActivo(false);
        this.clienteRepository.save(cliente);
        return "Cliente eliminado lógicamente con éxito";
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
