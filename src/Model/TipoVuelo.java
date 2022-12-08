package Model;

/**
 * Permite identificar si un vuelo es directo o de una escala.
 */
public class TipoVuelo {

    private String nombre;

    public TipoVuelo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
