import gameSystem.Checkpoints;
import habitaciones.Habitacion;
import objetos.Objeto;

import java.util.*;

public class Personaje {

     Habitacion desplazarse(String direccion, Habitacion habitacionActual) {
        if (!habitacionActual.getCoordenadas().containsKey(direccion)) {
            System.out.println("No hay nada en esa direccion. Intenta ir para otro lado");
            return null;
        }

        int idHabitacion = habitacionActual.getCoordenadas().get(direccion);
        Habitacion habitacion = Habitacion.dameHabitacion(idHabitacion);

        // la habitacion 7 es la salida, por lo que al salir activas el checkpoint 6 que indica el final del juego.
        if (habitacion.getId() == 7 && Checkpoints.isActive(4)) Checkpoints.activarCheckpoint(6);

        return habitacion;
    }

    void realizarAccionConObjeto(String nombreObjeto, String accionJugador, List<Objeto> objetosExistentes) {
        Objeto objetoSeleccionado = null;

        for (Objeto objeto: objetosExistentes) {
            if (objeto.getNombre().equals(nombreObjeto)) {
                objetoSeleccionado = objeto;
                break;
            }
        }

        if (accionJugador.equals("ob")) {
            objetoSeleccionado.observar();
        } else {
            objetoSeleccionado.interactuar();
        }
    }
}
