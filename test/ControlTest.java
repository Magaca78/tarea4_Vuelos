package test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Model.ControlMostrarVuelo;

public class ControlTest {

    @Test
    void testMostrarVuelos() throws Exception {

        ControlMostrarVuelo control = new ControlMostrarVuelo();

        String origen = "Manizales";
        String destino = "Cartagena";
        double duracionEsperada = 5.0;
        double PrecioEsperado = 390000;

        control.mostrarVuelos(origen, destino);

        assertAll("Vuelo a una escala",
                () -> assertEquals(origen, control.mostrarVuelos(origen, destino).get(0).getOrigen()),
                () -> assertEquals(destino, control.mostrarVuelos(origen, destino).get(0).getDestino()),
                () -> assertEquals(duracionEsperada, control.mostrarVuelos(origen, destino).get(0).getDuracion()),
                () -> assertEquals(PrecioEsperado, control.mostrarVuelos(origen, destino).get(0).getPrecio()));

    }
}