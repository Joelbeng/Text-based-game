package objetos;

import gameSystem.Checkpoints;
import objetos.deVacaciones.Celular;
import org.json.simple.JSONObject;

import java.util.*;

public class Objeto implements Acciones {
    private String nombre;
    protected String descripcion1;
    protected String descripcion2;
    protected String interaccion1;
    protected String interaccion2;

    public Objeto(String nombre, String descripcion1, String descripcion2, String interaccion1, String interaccion2) {
        this.nombre = nombre;
        this.descripcion1 = descripcion1;
        this.descripcion2 = descripcion2;
        this.interaccion1 = interaccion1;
        this.interaccion2 = interaccion2;
    }

    public String getNombre() {
        return nombre;
    }

    public static ArrayList<Objeto> construir(List<JSONObject> jsonItems) {
        List<Objeto> roomItems = new ArrayList<>();

        for (JSONObject jsonItem : jsonItems) {
            String itemName = (String) jsonItem.get("nombre");
            String itemDescription1 = (String) jsonItem.get("descripcion1");
            String itemDescription2 = (String) jsonItem.get("descripcion2");
            String itemInteraction1 = (String) jsonItem.get("interaccion1");
            String itemInteraction2 = (String) jsonItem.get("interaccion2");

            if (jsonItem.containsKey("nCheckpoint")) {
                int nCheckpoint = (int)(long)jsonItem.get("nCheckpoint");
                ObjetoDeterminante item = new ObjetoDeterminante(itemName, itemDescription1, itemDescription2, itemInteraction1, itemInteraction2, nCheckpoint);
                roomItems.add(item);
                continue;
            }

            if (itemName.equals("celular")) {
                Celular item = new Celular(itemName, itemDescription1, itemDescription2, itemInteraction1, itemInteraction2);
                roomItems.add(item);
                continue;
            }

            if (itemName.equals("cuerpo") || itemName.equals("bolso")) {
                ObjetoCambiante item = new ObjetoCambiante(itemName, itemDescription1, itemDescription2, itemInteraction1, itemInteraction2);
                roomItems.add(item);
                continue;
            }

            Objeto item = new Objeto(itemName, itemDescription1, itemDescription2, itemInteraction1, itemInteraction2);
            roomItems.add(item);
        }

        return (ArrayList<Objeto>) roomItems;
    }

    @Override
    public void observar() {
        if (!Checkpoints.isActive(1)) {
            System.out.println(descripcion1);
            return;
        }

        System.out.println(descripcion2);
    }

    @Override
    public void interactuar() {
        if (!Checkpoints.isActive(1)) {
            System.out.println(interaccion1);
            return;
        }

        System.out.println(interaccion2);
    }
}
