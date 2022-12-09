
import java.util.Scanner;

import Model.ControlMostrarVuelo;
import Model.Vuelo;

public class Pruebas {

    public static void main(String[] args) throws Exception {

        ControlMostrarVuelo control = new ControlMostrarVuelo();

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el origen: ");
        String origen = sc.nextLine().toLowerCase();
        System.out.print("Ingrese el destino: ");
        String destino = sc.nextLine().toLowerCase();
        sc.close();
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("Busqueda de vuelos de " + origen + " a " + destino + " (directos o con escala)");
        System.out.println("-------------------------------------------------");
        for (Vuelo vuelo : control.mostrarVuelos(origen, destino)) {

            System.out.println(
                    "Origen: " + vuelo.getOrigen() + "\n" + "Destino: " + vuelo.getDestino() + "\n" + "Duracion: "
                            + vuelo.getDuracion() + "\n" + "Precio: " + vuelo.getPrecio() + "\n" + "Tipo Vuelo: "
                            + vuelo.getTipovuelo().getNombre());
            System.out.println("-------------------------------------------------");

        }

    }

}
