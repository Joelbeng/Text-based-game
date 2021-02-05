package habitaciones;

import gameSystem.Checkpoints;
import objetos.Objeto;

import java.util.*;

import org.json.simple.JSONObject;

public class Habitacion {
    protected int id;
    private String descripcion;
    private Map<String,Integer> coordenadas;
    private List<Objeto> objetos;
    private static Map<Integer,Habitacion> habitaciones = new HashMap<>();

    public Habitacion(String descripcion, List<Objeto> objs, Map<String, Integer> coordenadas, int id) {
        this.id = id;
        this.descripcion = descripcion;
        this.objetos = objs;
        this.coordenadas = coordenadas;
    }

    public int getId() {
        return id;
    }

    public Map<String, Integer> getCoordenadas() {
        return coordenadas;
    }

    public List<Objeto> getObjetos() {
        return objetos;
    }

    public static void construir(JSONObject json) {
        List<JSONObject> listaHabitaciones = (ArrayList)json.get("habitaciones");

        for (JSONObject habitacion :listaHabitaciones) {
            int id = (int)(long) habitacion.get("id");
            String description = (String) habitacion.get("descripcion");
            List<JSONObject> jsonItemsList = (ArrayList) habitacion.get("objetos");
            List<Objeto> roomItems = Objeto.construir(jsonItemsList);

            Map<String,Integer> roomCoordenates = new HashMap<>();
            String[] puntosCardinales= {"norte", "este", "sur", "oeste"};

            for (int i = 0; i < puntosCardinales.length; i++) {
                if (habitacion.containsKey(puntosCardinales[i])) {
                    int idHabitacion = (int)(long) habitacion.get(puntosCardinales[i]);
                    roomCoordenates.put(puntosCardinales[i],idHabitacion);
                }
            }

            Habitacion newRoom = null;

            if (habitacion.containsKey("bloqueada")) {
                String description2 = (String) habitacion.get("descripcionBloqueada");
                newRoom = new HabitacionBloqueada(description, description2, roomItems, roomCoordenates, id);
            } else {
                newRoom = new Habitacion(description, roomItems, roomCoordenates, id);
            }

            habitaciones.put(id, newRoom);
        }
    }

    public static Habitacion dameHabitacion(int id) {
        Habitacion habitacion = habitaciones.get(id);

        if (habitacion.id == 5) {
            Checkpoints.activarCheckpoint(1);
        }

        return habitaciones.get(id);
    }

    public void describir() {
        System.out.println();
        System.out.println(descripcion);

        if (id == 7) return;

        String objetosStr;
        String coordenadasStr;

        if (coordenadas.size() > 1) {
            coordenadasStr = "Las direcciones posibles son:";
        } else {
            coordenadasStr = "La direccion posible es:";
        }

        System.out.println(coordenadasStr);

        for (String coordenada:coordenadas.keySet()){
            System.out.println("_ " + coordenada);
        }

        if (objetos.size() > 1) {
            objetosStr = "Los objetos en el lugar son:";
        } else {
            objetosStr = "El objeto en el lugar es:";
        }

        System.out.println(objetosStr);

        for (Objeto objeto:objetos) {
            System.out.println("_ " + objeto.getNombre());
        }

        System.out.println();
    }
}
