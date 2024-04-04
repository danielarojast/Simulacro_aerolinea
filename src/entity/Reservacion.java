package entity;

public class Reservacion {
    private int  id_reservacion;
    private String fecha_reservacion;
    private String asiento;
    private int id_pasajero;
    private Pasajero pasajero;
    private int id_vuelo;
    private Vuelo vuelo;

    //contructor

    public Reservacion() {

    }

    public Reservacion(int id_reservacion, String fecha_reservacion, String asiento, int id_pasajero, Pasajero pasajero, int id_vuelo, Vuelo vuelo) {
        this.id_reservacion = id_reservacion;
        this.fecha_reservacion = fecha_reservacion;
        this.asiento = asiento;
        this.id_pasajero = id_pasajero;
        this.pasajero = pasajero;
        this.id_vuelo = id_vuelo;
        this.vuelo = vuelo;
    }

    //getter ans setter

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public String getFecha_reservacion() {
        return fecha_reservacion;
    }

    public void setFecha_reservacion(String fecha_reservacion) {
        this.fecha_reservacion = fecha_reservacion;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public int getId_pasajero() {
        return id_pasajero;
    }

    public void setId_pasajero(int id_pasajero) {
        this.id_pasajero = id_pasajero;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public int getId_vuelo() {
        return id_vuelo;
    }

    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    //toString

    @Override
    public String toString() {
        return "Reservacion: " +
                "id_reservacion=" + id_reservacion + "\n" +
                "fecha_reservacion='" + fecha_reservacion + "\n" +
                "asiento='" + asiento + "\n" +
                "id_pasajero=" + id_pasajero +
                ", pasajero=" + pasajero +
                ", id_vuelo=" + id_vuelo +
                ", vuelo=" + vuelo + "\n";
    }
}


