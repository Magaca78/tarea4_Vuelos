package Model;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que perimite realizar las operaciones principales de todo
 * el programa
 */
public class ControlMostrarVuelo {

    private Lector lector;
    private List<Vuelo> vuelos;

    public ControlMostrarVuelo(Lector lector, List<Vuelo> vuelos) {
        vuelos = new ArrayList<>();
        lector = new Lector();
    }

    public List<Vuelo> mostrarVuelos(String origen, String destino)
            throws IOException, ParseException, VueloException, org.json.simple.parser.ParseException {

        List<String[]> rutas = this.cargarRutas();

        if (!validarVuelo(origen, destino, rutas)) {

            throw new VueloException("No hay rutas para ese vuelo");
        }
        adicionarVuelo(origen, destino, rutas);

        if (vuelos.size() == 0) {

            throw new VueloException("No se pudo encontrar ningun vuelo");

        }

        return vuelos;

    }

    private List<String[]> cargarRutas() throws IOException, org.json.simple.parser.ParseException {

        return this.lector.Leer("src/JSON/Rutas.json");

    }

    private boolean validarVuelo(String origen, String destino, List<String[]> rutas) throws VueloException {

        boolean existeDestino = false;
        boolean existeOrigen = false;

        for (String[] ruta : rutas) {
            if (!verificarEstructuraJSON(ruta)) {
                throw new VueloException("JSON incorrecto");

            }
            if (origen == ruta[0]) {
                existeOrigen = true;
            }
            if (destino == ruta[1]) {
                existeDestino = true;
            }

        }

        if (existeOrigen && existeDestino) {
            return true;
        }

        return false;

    }

    private void adicionarVuelo(String origen, String destino, List<String[]> rutas) {

        for (String[] ruta : rutas) {
            if (ruta[0] == origen && ruta[1] == destino) {
                Vuelo vuelo = new Vuelo(origen, destino, Double.parseDouble(ruta[2]), Double.parseDouble(ruta[3]));
                vuelo.adicionarTipo("directo");
                vuelos.add(vuelo);
            }

            if (ruta[0] == origen && destino != ruta[1]) {

                for (String[] ruta3 : rutas) {
                    if (destino == ruta3[1] && ruta[1] == ruta[3]) {
                        double duracion = Double.parseDouble(ruta[2] + ruta3[2]);
                        double precio = Double.parseDouble(ruta[3] + ruta3[3]);

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
