package com.example.Taller.Repository;

import com.example.Taller.Entity.PresupuestoEntity;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PresupuestoRepository extends JpaRepository<PresupuestoEntity, Integer> {

    PresupuestoEntity findByIngreso_Id(Integer ingresoId);

    List<PresupuestoEntity> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);

    @Query(value = "SELECT year(fecha) as año, sum(total_iva), sum(mano_de_obra), sum(costo_repuesto) \n" +
            "FROM taller4.presupuestos \n" +
            "GROUP BY year(fecha) \n" +
            "order by year(fecha) desc", nativeQuery = true)
    List<Object[]> obtenerPresupuestosPorAnio();

    @Query (value = "SELECT DATE_FORMAT(fecha, '%Y-%m') as mes, sum(total_iva), sum(mano_de_obra), sum(costo_repuesto) \n" +
            "FROM taller4.presupuestos \n" +
            "GROUP BY mes \n" +
            "order by mes desc", nativeQuery = true)
    List<Object[]>  obtenerPresupuestosPorMes();

}
