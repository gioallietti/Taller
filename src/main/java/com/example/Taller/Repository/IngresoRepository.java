package com.example.Taller.Repository;

import com.example.Taller.DTO.IngreosPorMesAnioDTO;
import com.example.Taller.Entity.IngresoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface IngresoRepository extends JpaRepository<IngresoEntity, Integer> {
    List<IngresoEntity> findAllByOrderByPrioridadAsc();

    List<IngresoEntity> findByFechaIngresoBetween(LocalDate desde, LocalDate hasta);

    List<IngresoEntity> findAllByIngresadoPor_Id(Integer registradoPorId);

    List<IngresoEntity> findAllByReparadoPor_TipoUsuario_IdOrderByFechaFinalizacionDesc(Integer tipoUsuarioId);

    List<IngresoEntity> findAllByEstado_IdAndReparadoPor_TipoUsuario_Id(Integer estadoId, Integer tipoUsuarioId);

    List<IngresoEntity> findByEstadoIdNot(Integer estadoId);

    List<IngresoEntity> findByEstadoId(Integer estadoId);

    List<IngresoEntity> findByFechaFinalizacionBeforeOrderByFechaFinalizacionAsc(LocalDate fechaMaxima);

    IngresoEntity findByNumeroSerie(String numeroSerie);

    @Query (value = "SELECT year(fecha_ingreso) as anio, COUNT(*) as cantidad \n" +
            "FROM taller4.ingresos \n" +
            "GROUP BY year(fecha_ingreso) \n" +
            "order by year(fecha_ingreso) desc", nativeQuery = true)
    List<Object[]>  obtenerIngresosPorAnio();

    @Query (value = "SELECT DATE_FORMAT(fecha_ingreso, '%Y-%m') as mes,  COUNT(*) as cantidad \n" +
            "FROM taller4.ingresos \n" +
            "GROUP BY mes \n" +
            "order by mes desc", nativeQuery = true)
    List<Object[]>  obtenerIngresosPorMes();

    @Query (value = "SELECT * FROM taller4.ingresos \n " +
            "WHERE (estado_id = 5 OR estado_id = 6) \n " +
            "  AND fecha_finalizacion >= DATE_SUB(CURDATE(), INTERVAL 3 MONTH) \n " +
            "  order by fecha_finalizacion desc", nativeQuery = true)
    List<IngresoEntity>  obtenerIngresosFinalizados();

    List<IngresoEntity> findByEstadoIdAndFechaFinalizacionAfterOrderByFechaFinalizacionDesc(Integer estado, LocalDate fecha);
}
