package Model;
/**
 * Excepcion para cuando no se encuentra el vuelo solicitado
 */
public class VueloException extends Exception {

    public VueloException(String mensaje) {
        super(mensaje);
    }

}
