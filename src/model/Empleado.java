package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Empleado extends Usuario {

    public enum Cargo { GERENTE, AGENTE, MECANICO, ADMINISTRATIVO }

    private Cargo cargo;
    private BigDecimal salario;
    private LocalDate fechaContrato;

    public Empleado() {
        setRol(Rol.empleado);
    }

    public Cargo getCargo() { return cargo; }
    public void setCargo(Cargo cargo) { this.cargo = cargo; }

    public BigDecimal getSalario() { return salario; }
    public void setSalario(BigDecimal salario) { this.salario = salario; }

    public LocalDate getFechaContrato() { return fechaContrato; }
    public void setFechaContrato(LocalDate fechaContrato) { this.fechaContrato = fechaContrato; }
}