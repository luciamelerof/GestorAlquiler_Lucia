package model;

import java.time.LocalDate;

public class Cliente extends Usuario {

    private String telefono;
    private String direccion;
    private String carnetConducir;
    private LocalDate fechaNacimiento;

    public Cliente() {
        setRol(Rol.cliente);
    }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCarnetConducir() { return carnetConducir; }
    public void setCarnetConducir(String carnetConducir) { this.carnetConducir = carnetConducir; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
}
