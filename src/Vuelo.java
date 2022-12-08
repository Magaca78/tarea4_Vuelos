/**
 * Representa un vuelo dese un origen a un destino, que puede estar conformado
 * por 1 o 2 rutas.
 */
public class Vuelo {

    private String origen, destino;
    private double duracion, precio;

    private TipoVuelo tipovuelo;

    public Vuelo(String origen, String destino, double duracion, double precio) {
        this.origen = origen;
        this.destino = destino;
        this.duracion = duracion;
        this.precio = precio;
    }

    public void adicionarTipo(String tipo) {

    }

}