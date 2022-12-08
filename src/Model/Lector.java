package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Clase que permite leer un archivo JSON enviado
 */
public class Lector {

    public List<String[]> Leer(String ruta) {

        try {
            List<String[]> rutas = new ArrayList<>();
            JSONParser parser = new JSONParser();
            FileReader fileReader;

            File file = new File(ruta);
            fileReader = new FileReader(file);

            JSONArray array = (JSONArray) parser.parse(fileReader);

            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = (JSONObject) array.get(i);
                parseObject(obj, rutas);
            }

            return rutas;
        } catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace() + " :File Not Found");
        } catch (ParseException e) {
            System.out.println(e.getStackTrace() + " :Could not parse");
        } catch (IOException e) {
            System.out.println(e.getStackTrace() + " :IOException");
        }
        return null;

        /*
         * 
         * for (String[] strings : rutas) {
         * System.out.println("Origen: " + strings[0] + "\n" +
         * "Destino: " + strings[1]);
         * System.out.println("---------------------------");
         * 
         * }
         */

    }

    private static void parseObject(JSONObject obj, List<String[]> rutas) {
        String origen = (String) obj.get("origen");
        String destino = (String) obj.get("destino");
        double duracion = (double) obj.get("duracion");
        double precio = (double) obj.get("precio");

        String[] ruta = new String[4];
        ruta[0] = origen;
        ruta[1] = destino;
        ruta[2] = String.valueOf(duracion);
        ruta[3] = String.valueOf(precio);

        rutas.add(ruta);

    }
}