package gameSystem;

import objetos.Objeto;

import java.util.List;

public class InputCheck {

    public boolean chequearInput(String[] accionJugador, List<Objeto> objetos) {
        String[] accionesValidas = {"ir", "in", "ob"};
        String accionRealizada = accionJugador[0];
        boolean isValid = false;

        for (String accionValida: accionesValidas) {
            if (accionRealizada.equals(accionValida)) {
                isValid = true;
                break;
            }
        }

        if (!isValid) {
            System.out.println("El comando ingresado no es valido. Los comandos posibles son:\nir (desplazarse), in (interactuar), ob (observar)");
            return false;
        }

        if (accionRealizada.equals("ir")) {
            String direccion = accionJugador[accionJugador.length-1];
            return (chequearDireccion(direccion));
        } else {
            String objeto = accionJugador[accionJugador.length-1];
            return (chequearObjeto(objeto, objetos));
        }
    }

    private boolean chequearDireccion(String direccion) {
        String[] direccionesValidas = {"norte", "este", "sur", "oeste"};

         for(String dir:direccionesValidas){
            if (dir.equals(direccion)) {
                return true;
            }
        }

         System.out.println("La direccion ingresada no es valida. Ingresela nuevamente");
         return false;
     }

    private boolean chequearObjeto(String accionJugador, List<Objeto> objetos) {
        for (Objeto objeto : objetos) {
            if (accionJugador.contains(objeto.getNombre())) {
                return true;
            }
        }

        System.out.println("El objeto ingresado no existe dentro de este lugar");
        return false;
    }

}
