package entity;

public class Vuelo {
    private int id_vuelo;
    private String destino;
    private String fecha_salida;
    private String hora_salida;
    private int id_avion;
    private Avion avion;

    //constructor

    public Vuelo() {

    }

    public Vuelo(int id_vuelo, String destino, String fecha_salida, String hora_salida, int id_avion, Avion avion) {
        this.id_vuelo = id_vuelo;
        this.destino = destino;
        this.fecha_salida = fecha_salida;
        this.hora_salida = hora_salida;
        this.id_avion = id_avion;
        this.avion = avion;
    }

    //getter and setter

    public int getId_vuelo() {
        return id_vuelo;
    }

    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public int getId_avion() {
        return id_avion;
    }

    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    //toString

    @Override
    public String toString() {
        return "Vuelo: " +
                "id_vuelo=" + id_vuelo + "\n" +
                "destino='" + destino + "\n" +
                "fecha_salida='" + fecha_salida + "\n" +
                "hora_salida='" + hora_salida + "\n" +
                "id_avion=" + id_avion +
                ", avion=" + avion + "\n";
    }
}
