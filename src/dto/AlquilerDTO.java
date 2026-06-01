package dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AlquilerDTO {

    private int id;
    private String clienteNombreCompleto;
    private String clienteDni;
    private String vehiculoMatricula;
    private String vehiculoNombre;
    private String empleadoNombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private long diasAlquiler;
    private int kmSalida;
    private Integer kmEntrada;
    private BigDecimal precioTotal;
    private String estado;
    private String observaciones;

    public AlquilerDTO() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getClienteNombreCompleto() { return clienteNombreCompleto; }
    public void setClienteNombreCompleto(String clienteNombreCompleto) { this.clienteNombreCompleto = clienteNombreCompleto; }

    public String getClienteDni() { return clienteDni; }
    public void setClienteDni(String clienteDni) { this.clienteDni = clienteDni; }

    public String getVehiculoMatricula() { return vehiculoMatricula; }
    public void setVehiculoMatricula(String vehiculoMatricula) { this.vehiculoMatricula = vehiculoMatricula; }

    public String getVehiculoNombre() { return vehiculoNombre; }
    public void setVehiculoNombre(String vehiculoNombre) { this.vehiculoNombre = vehiculoNombre; }

    public String getEmpleadoNombre() { return empleadoNombre; }
    public void setEmpleadoNombre(String empleadoNombre) { this.empleadoNombre = empleadoNombre; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public long getDiasAlquiler() { return diasAlquiler; }
    public void setDiasAlquiler(long diasAlquiler) { this.diasAlquiler = diasAlquiler; }

    public int getKmSalida() { return kmSalida; }
    public void setKmSalida(int kmSalida) { this.kmSalida = kmSalida; }

    public Integer getKmEntrada() { return kmEntrada; }
    public void setKmEntrada(Integer kmEntrada) { this.kmEntrada = kmEntrada; }

    public BigDecimal getPrecioTotal() { return precioTotal; }
    public void setPrecioTotal(BigDecimal precioTotal) { this.precioTotal = precioTotal; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}