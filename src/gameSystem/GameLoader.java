package gameSystem;

import habitaciones.Habitacion;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;


public class GameLoader {
    private JSONObject json;

    public void cargarJson() throws IOException,ParseException {
        FileReader archivo = null;

        // try catch para leer el json desde el IDE o desde la consola de Windows
        try {
            archivo = new FileReader("data/juego.json");
        } catch(FileNotFoundException e){
            archivo = new FileReader("../data/juegoCMD.json"); // versión sin caracteres especiales
        }

        JSONParser parser = new JSONParser();
        json = (JSONObject) parser.parse(archivo);

        archivo.close();
    }

    public void cargarHabitaciones() {
        Habitacion.construir(json);
    }

    public String dameNombreDelJuego() {
        return (String) json.get("nombreDelJuego");
    }

    public String dameDescripcionInicial() {
        return (String) json.get("inicio");
    }

    public String dameDescripcionFinal() {
        if (Checkpoints.isActive(5)) {
            return (String) json.get("final2");
        }

        return (String) json.get("final1");
    }
}
