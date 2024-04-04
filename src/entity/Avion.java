package entity;

public class Avion {
    private int  id_avion;
    private String modelo;
    private String capacidad;

    //constructor
    public Avion() {

    }
    public Avion(int id_avion, String modelo, String capacidad) {
        this.id_avion = id_avion;
        this.modelo = modelo;
        this.capacidad = capacidad;
    }

    //getter and setter

    public int getId_avion() {
        return id_avion;
    }

    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Avion: " +
                "id_avion=" + id_avion + "\n" +
                " modelo='" + modelo + "\n" +
                " capacidad='" + capacidad + "\n";
    }
}
