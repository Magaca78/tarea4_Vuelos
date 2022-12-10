package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import Model.ControlMostrarVuelo;
import Model.Vuelo;
import Model.VueloException;

public class ControlTest {

        @Test
        void testVueloEscala() throws Exception {

                ControlMostrarVuelo control = new ControlMostrarVuelo();
                List<String[]> rutas = control.cargarRutas(
                                "C:/Users/magac/Desktop/Universidad/6to semestre/Ingenieria del software I/CORTE 3/Tarea4/VuelosTarea4/src/Json/Rutas.json");

                String origen = "manizales";
                String destino = "cartagena";
                double duracionEsperada = 5.0;
                double PrecioEsperado = 390000.0;

                List<Vuelo> vuelos = control.mostrarVuelos(origen, destino, rutas);

                assertAll("Vuelo a una escala",
                                () -> assertEquals(origen, vuelos.get(0).getOrigen()),
                                () -> assertEquals(destino, vuelos.get(0).getDestino()),
                                () -> assertEquals(duracionEsperada, vuelos.get(0).getDuracion()),
                                () -> assertEquals(PrecioEsperado, vuelos.get(0).getPrecio()));
        }

        @Test
        void testVueloInexistente() throws Exception {

                ControlMostrarVuelo control = new ControlMostrarVuelo();
                List<String[]> rutas = control.cargarRutas(
                                "C:/Users/magac/Desktop/Universidad/6to semestre/Ingenieria del software I/CORTE 3/Tarea4/VuelosTarea4/src/Json/Rutas.json");

                String origen = "cali";
                String destino = "barranquilla";

                assertThrows(VueloException.class,
                                () -> control.mostrarVuelos(origen, destino, rutas));
        }
}
