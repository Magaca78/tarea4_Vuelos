package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que perimite realizar las operaciones principales de todo
 * el programa
 */
public class ControlMostrarVuelo {

    private Lector lector;
    private List<Vuelo> vuelos;

    public ControlMostrarVuelo() {
        vuelos = new ArrayList<>();
        lector = new Lector();
    }

    /**
     * Muestra la lista de vuelos disponibles , verificando que se cumplan con las
     * condiciones necesarias:
     * el JSON de rutas debe tener el formato que corresponde.
     * Debe existir alguna ruta con ese origen y alguna con ese destino.
     * 
     * @param origen
     * @param destino
     * @param rutas
     * @return
     * @throws Exception
     */
    public List<Vuelo> mostrarVuelos(String origen, String destino, List<String[]> rutas) throws Exception {

        if (!validarVuelo(origen, destino, rutas)) {
            throw new VueloException("No hay rutas para ese vuelo");
        }

        adicionarVuelo(origen, destino, rutas);

        if (vuelos.size() == 0) {
            throw new VueloException("No se pudo encontrar ningun vuelo");
        }
        return vuelos;
    }

    public List<String[]> cargarRutas(String ruta) throws Exception {
        return this.lector.leer(ruta);
    }

    /**
     * Valida que el origen y destino existan en la ruta.
     * 
     * @param origen
     * @param destino
     * @param rutas
     * @return
     * @throws VueloException
     */
    private boolean validarVuelo(String origen, String destino, List<String[]> rutas) throws VueloException {

        boolean existeDestino = false;
        boolean existeOrigen = false;

        for (String[] ruta : rutas) {

            if (!verificarEstructuraJSON(ruta)) {
                throw new VueloException("JSON incorrecto");
            }
            if (origen.equals(ruta[0])) {
                existeOrigen = true;
            }
            if (destino.equals(ruta[1])) {
                existeDestino = true;
            }
        }

        if (existeOrigen && existeDestino) {
            return true;
        }

        return false;

    }

    /**
     * Adiciona un vuelo a la lista de vuelos de tipo directo o escala que
     * cumplan con el origen y el destino.
     * 
     * @param origen
     * @param destino
     * @param rutas
     */
    private void adicionarVuelo(String origen, String destino, List<String[]> rutas) {

        for (String[] ruta : rutas) {
            if (ruta[0].equals(origen) && ruta[1].equals(destino)) {
                Vuelo vuelo = new Vuelo(origen, destino, Double.parseDouble(ruta[2]), Double.parseDouble(ruta[3]));
                vuelo.adicionarTipo("directo");
                vuelos.add(vuelo);
            }

            if (ruta[0].equals(origen) && !destino.equals(ruta[1])) {

                for (String[] ruta3 : rutas) {

                    if (destino.equals(ruta3[1]) && ruta[1].equals(ruta3[0])) {
                        double duracion = Double.parseDouble(ruta[2]) + Double.parseDouble(ruta3[2]);
                        double precio = Double.parseDouble(ruta[3]) + Double.parseDouble(ruta3[3]);

                        Vuelo vuelo = new Vuelo(origen, destino, duracion, precio);
                        vuelo.adicionarTipo("escala");
                        vuelos.add(vuelo);
                    }

                }

            }

        }

    }

    private boolean verificarEstructuraJSON(String[] linea) {

        if (linea.length != 4) {
            return false;
        }
        return true;
    }

}
