package com.example.Taller.Service;

import com.example.Taller.Entity.IngresoEntity;
import com.example.Taller.Entity.IngresoRepuestoEntity;
import com.example.Taller.Repository.IngresoRepository;
import com.example.Taller.Entity.PresupuestoEntity;
import com.example.Taller.Repository.PresupuestoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PresupuestoServiceImpl implements PresupuestoService{
    @Autowired
    private PresupuestoRepository presupuestoRepository;

    @Autowired
    private IngresoRepository ingresoRepository;

    @Override
    public PresupuestoEntity guardarPresupuesto(PresupuestoEntity presupuesto) {

        if(presupuestoRepository.findByIngreso_Id(presupuesto.getIngreso().getId()) != null){
            throw new IllegalArgumentException("Ya existe un presupuesto para ese ingreso");
        }

        double totalSinIva = presupuesto.getCostoRepuesto() + presupuesto.getManoDeObra();
        presupuesto.setTotalSinIva(totalSinIva);
        double totalConIva = presupuesto.getTotalSinIva() + (presupuesto.getTotalSinIva() * 0.22);
        presupuesto.setTotalIva(totalConIva);

        return presupuestoRepository.save(presupuesto);
    }

    @Override
    public PresupuestoEntity obtenerPresupuestoPorId(int id) {
        return presupuestoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Presupuesto no encontrado con id: " + id));
    }

    @Override
    public PresupuestoEntity obtenerPresupuestoPorIngresoId(Integer ingresoId) {
        PresupuestoEntity presupuesto = presupuestoRepository.findByIngreso_Id(ingresoId);

        if (presupuesto == null) {
            throw new EntityNotFoundException("Presupuesto no encontrado para el ingreso con id: " + ingresoId);
        }

        return presupuesto;
    }

    @Override
    public List<PresupuestoEntity> obtenerTodosLosPresupuestos() {
        return presupuestoRepository.findAll();
    }

    @Override
    public String eliminarPresupuesto(int id) {
        if (!presupuestoRepository.existsById(id)) {
            throw new EntityNotFoundException("El presupuesto con id " + id + " no existe");
        }
        presupuestoRepository.deleteById(id);
        return "Presupuesto eliminado con éxito";
    }

    @Override
    public PresupuestoEntity actualizarPresupuesto(int id, PresupuestoEntity presupuesto) {
        if (!presupuestoRepository.existsById(id)) {
            throw new EntityNotFoundException("El presupuesto con id " + id + " no existe");
        }
        double totalSinIva = presupuesto.getCostoRepuesto() + presupuesto.getManoDeObra();
        presupuesto.setTotalSinIva(totalSinIva);
        double totalConIva = presupuesto.getTotalSinIva() + (presupuesto.getTotalSinIva() * 0.22);
        presupuesto.setTotalIva(totalConIva);
        presupuesto.setId(id);
        return presupuestoRepository.save(presupuesto);
    }

    @Override
    public double calcularGanancia(PresupuestoEntity presupuesto) {
        IngresoEntity ingreso = presupuesto.getIngreso();

        double costoRepuestoCargado = presupuesto.getCostoRepuesto();
        double manoDeObra = presupuesto.getManoDeObra();

        double costoReal = 0.0;
        List<IngresoRepuestoEntity> repuestosUsados = ingreso.getIngresoRepuestos();

        for (IngresoRepuestoEntity ingresoRepuesto : repuestosUsados) {
            double precioUno = ingresoRepuesto.getRepuesto().getPrecio();
            int cantidad = ingresoRepuesto.getCantidad();
            costoReal += precioUno * cantidad;
        }

        return manoDeObra + (costoRepuestoCargado - costoReal);
    }

    @Override
    public double calcularGananciaMensual(int anio, int mes) {
        List<PresupuestoEntity> todos = presupuestoRepository.findAll();

        double totalGanancia = 0.0;

        for (PresupuestoEntity presupuesto : todos) {
            IngresoEntity ingreso = presupuesto.getIngreso();
            LocalDate fecha = ingreso.getFechaFinalizacion();

            if (fecha != null &&
                    fecha.getYear() == anio &&
                    fecha.getMonthValue() == mes) {

                totalGanancia += calcularGanancia(presupuesto);
            }
        }

        return totalGanancia;
    }
}
