package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Alquiler {

    public enum Estado { ACTIVO, FINALIZADO, CANCELADO }

    private int id;
    private int clienteId;
    private int vehiculoId;
    private Integer empleadoId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int kmSalida;
    private Integer kmEntrada;
    private BigDecimal precioTotal;
    private Estado estado;
    private String observaciones;

    public Alquiler() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }

    public int getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(int vehiculoId) { this.vehiculoId = vehiculoId; }

    public Integer getEmpleadoId() { return empleadoId; }
    public void setEmpleadoId(Integer empleadoId) { this.empleadoId = empleadoId; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public int getKmSalida() { return kmSalida; }
    public void setKmSalida(int kmSalida) { this.kmSalida = kmSalida; }

    public Integer getKmEntrada() { return kmEntrada; }
    public void setKmEntrada(Integer kmEntrada) { this.kmEntrada = kmEntrada; }

    public BigDecimal getPrecioTotal() { return precioTotal; }
    public void setPrecioTotal(BigDecimal precioTotal) { this.precioTotal = precioTotal; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}