package com.example.Taller.Service;

import com.example.Taller.DTO.IngreosPorMesAnioDTO;
import com.example.Taller.Entity.EstadoEntity;
import com.example.Taller.Entity.IngresoEntity;
import com.example.Taller.Entity.IngresoRepuestoEntity;
import com.example.Taller.Repository.ClienteRepository;
import com.example.Taller.Repository.EquipoRepository;
import com.example.Taller.Repository.IngresoRepository;
import com.example.Taller.Repository.IngresoRepuestoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class IngresoServiceImpl implements IngresoService{
    @Autowired
    private IngresoRepository ingresoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private IngresoRepuestoRepository ingresoRepuestoRepository;

    @Override
    public IngresoEntity guardarIngreso(IngresoEntity ingreso) {

        if (ingreso.getCliente() == null || ingreso.getCliente().getId() == null || !clienteRepository.existsById(ingreso.getCliente().getId())) {
            throw new IllegalArgumentException("El cliente no existe, Por favor proba con otro");
        }

        if(ingreso.getIngresadoPor() == null || ingreso.getIngresadoPor().getId() == null || !clienteRepository.existsById(ingreso.getIngresadoPor().getId())){
            throw new IllegalArgumentException("No esta la sesion ingresada, intentelo de nuevo");
        }


        if (ingreso.getEquipo() == null || ingreso.getEquipo().getId() == null || !equipoRepository.existsById(ingreso.getEquipo().getId())){
            throw new IllegalArgumentException("El equipo no existe, Por favor proba con otro");
        }

        return ingresoRepository.save(ingreso);
    }

    @Override
    public IngresoEntity obtenerIngresoPorId(int id) {
        return ingresoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingreso no encontrado con id: " + id));
    }

    @Override
    public List<IngresoEntity> obtenerTodosLosIngresos() {
        return ingresoRepository.findAll();
    }

    @Override
    public String eliminarIngreso(int id) {
        if (!ingresoRepository.existsById(id)) {
            throw new EntityNotFoundException("El ingreso con id " + id + " no existe");
        }
        ingresoRepository.deleteById(id);
        return "Ingreso eliminado con éxito";
    }

    @Override
    public List<IngresoEntity> listarPorPrioridad(){
        return ingresoRepository.findAllByOrderByPrioridadAscFechaIngresoAsc();
    }

    @Override
    public List<IngresoEntity> ingresosPorFechas(LocalDate desde, LocalDate hasta) {
        return this.ingresoRepository.findByFechaIngresoBetween(desde, hasta);
    }

    @Override
    public List<IngresoEntity> findAllByRegistradoPor_Id(Integer registradoPorId) {
        return this.ingresoRepository.findAllByIngresadoPor_Id(registradoPorId);
    }

    @Override
    public List<IngresoEntity> findAllByTecnicos(Integer id) {
        return this.ingresoRepository.findAllByReparadoPor_TipoUsuario_IdOrderByFechaFinalizacionDesc(id);
    }

    @Override
    public List<IngresoEntity> ingresoPorEstado_IdTipoUsuario(Integer estadoId, Integer tipoUsuarioId) {
        return this.ingresoRepository.findAllByEstado_IdAndReparadoPor_TipoUsuario_Id(estadoId, tipoUsuarioId);
    }

    @Override
    public IngresoEntity actualizarIngreso(int id, IngresoEntity ingreso) {
        IngresoEntity ingresoExistente = ingresoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El ingreso con id " + id + " no existe"));

        ingresoExistente.setCliente(ingreso.getCliente());
        ingresoExistente.setIngresadoPor(ingreso.getIngresadoPor());
        ingresoExistente.setReparadoPor(ingreso.getReparadoPor());
        ingresoExistente.setEquipo(ingreso.getEquipo());
        ingresoExistente.setNumeroSerie(ingreso.getNumeroSerie());
        ingresoExistente.setProblema(ingreso.getProblema());
        ingresoExistente.setPrioridad(ingreso.getPrioridad());
        ingresoExistente.setFechaIngreso(ingreso.getFechaIngreso());
        ingresoExistente.setFechaFinalizacion(ingreso.getFechaFinalizacion());
        ingresoExistente.setEstado(ingreso.getEstado());
        ingresoExistente.setDetalle(ingreso.getDetalle());
        ingresoExistente.setPresupuestado(ingreso.isPresupuestado());
        ingresoExistente.setSolucion(ingreso.getSolucion());

        ingresoExistente.getIngresoRepuestos().clear();
        if (!CollectionUtils.isEmpty(ingreso.getIngresoRepuestos())) {
            for (IngresoRepuestoEntity ingresoRepuesto : ingreso.getIngresoRepuestos()) {

                ingresoRepuesto.setIngreso(ingresoExistente);

                ingresoRepuestoRepository.save(ingresoRepuesto);
            }
        }
        return ingresoRepository.save(ingresoExistente);
    }

    @Override
    public IngresoEntity guardarMensaje(int id, IngresoEntity ingreso) {
        IngresoEntity ingresoExistente = ingresoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El ingreso con id " + id + " no existe"));

        ingresoExistente.setAvisoCliente(ingreso.getAvisoCliente());
        return ingresoRepository.save(ingresoExistente);
    }

    @Override
    public IngresoEntity actualizarIngresoEstado(int id, EstadoEntity estado) {
        IngresoEntity ingresoExistente = ingresoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El ingreso con id " + id + " no existe"));

        ingresoExistente.setEstado(estado);

        return ingresoRepository.save(ingresoExistente);
    }

    @Override
    public List<IngreosPorMesAnioDTO>  obtenerIngresosPorAnio() {
        List<Object[]> ingreosPorMesAnioObjeto = ingresoRepository.obtenerIngresosPorAnio();

        List<IngreosPorMesAnioDTO> ingreosPorMesAnioDTOList = new ArrayList<>();

        for (Object[] fila : ingreosPorMesAnioObjeto) {
            IngreosPorMesAnioDTO dto = new IngreosPorMesAnioDTO();

            dto.setMesAnio(((String) fila[0].toString()));
            dto.setCantidad(((Number) fila[1]).intValue());

            ingreosPorMesAnioDTOList.add(dto);
        }
        return ingreosPorMesAnioDTOList;
    }

    @Override
    public List<IngreosPorMesAnioDTO>  obtenerIngresosPorMes() {
        List<Object[]> ingreosPorMesAnioObjeto = ingresoRepository.obtenerIngresosPorMes();

        List<IngreosPorMesAnioDTO> ingreosPorMesAnioDTOList = new ArrayList<>();

        for (Object[] fila : ingreosPorMesAnioObjeto) {
            IngreosPorMesAnioDTO dto = new IngreosPorMesAnioDTO();

            dto.setMesAnio(((String) fila[0].toString()));
            dto.setCantidad(((Number) fila[1]).intValue());

            ingreosPorMesAnioDTOList.add(dto);
        }
        return ingreosPorMesAnioDTOList;
    }

    @Override
    public List<IngresoEntity> IngresosFinalizadosMasTresMeses() {
        LocalDate fechaMaxima = LocalDate.now().minusMonths(3);
        return ingresoRepository.findByFechaFinalizacionBeforeOrderByFechaFinalizacionAsc(fechaMaxima);
    }

    @Override
    public List<IngresoEntity> obtenerIngresosFinalizados() {
        return ingresoRepository.obtenerIngresosFinalizados();
    }

    @Override
    public List<IngresoEntity> listarIngresosNoFinalizados() {
        return ingresoRepository.findByEstadoIdNot(5);
    }

    @Override
    public List<IngresoEntity> listarIngresosFinalizados() {
        return ingresoRepository.findByEstadoId(5) ;
    }

    @Override
    public boolean existeNumeroSerie(String numeroSerie) {
        return ingresoRepository.findByNumeroSerie(numeroSerie) != null;
    }
    
    @Override
    public List<IngresoEntity> obtenerIngresosSinSolucion() {
        LocalDate fecha = LocalDate.now().minusMonths(3);
        return ingresoRepository.findByEstadoIdAndFechaFinalizacionAfterOrderByFechaFinalizacionDesc(6, fecha);
    }

}
