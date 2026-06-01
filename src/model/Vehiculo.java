package model;

import java.math.BigDecimal;

public class Vehiculo {

    public enum Categoria { ECONOMICO, COMPACTO, SUV, FAMILIAR, LUJO, FURGONETA }

    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private int anio;
    private Categoria categoria;
    private BigDecimal precioDia;
    private int kmActuales;
    private boolean disponible;
    private String color;
    private String imagenUrl;

    public Vehiculo() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public BigDecimal getPrecioDia() { return precioDia; }
    public void setPrecioDia(BigDecimal precioDia) { this.precioDia = precioDia; }

    public int getKmActuales() { return kmActuales; }
    public void setKmActuales(int kmActuales) { this.kmActuales = kmActuales; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    @Override
    public String toString() {
        return marca + " " + modelo + " (" + anio + ") - " + matricula;
    }
}
