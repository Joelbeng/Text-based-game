package objetos.deVacaciones;

import gameSystem.Checkpoints;
import objetos.Objeto;

import java.util.Scanner;

public class Celular extends Objeto {
    private boolean decisionTomada = false; // atributo para saber si el usuario tomó la decisión de si llamar o no a la policía. Esto es para que se pregunte solo una vez al usuario

    public Celular(String nombre, String descripcion1, String descripcion2, String interaccion1, String interaccion2) {
        super(nombre, descripcion1, descripcion2, interaccion1, interaccion2);
    }

    @Override
    public void observar() {
        if (decisionTomada) {
            System.out.println(descripcion2);
            return;
        }

        if (Checkpoints.isActive(3)){
            System.out.println("Ahora que puedo usar el celular debo tomar una decisión");
            return;
        }

        System.out.println(descripcion1);
    }

    @Override
    public void interactuar() {
        if (Checkpoints.isActive(3) && !decisionTomada) {

            if (llamarAlaPolicia()) {
                Checkpoints.activarCheckpoint(5); // termina el juego
            } else {
                System.out.println("'Tengo que irme lo antes posible de este lugar'");
                System.out.println("Ahora debes continuar en búsqueda de una salida");
            }
            return;

        } else if (Checkpoints.isActive(3)) {
            System.out.println("Ya tomé la decisión no hay vuelta atrás, tengo que irme");
            return;
        }

        System.out.println(interaccion1);
    }

    private boolean llamarAlaPolicia() {
        System.out.println(interaccion2);
        System.out.println("Ingresar 'si' o 'no'");

        Scanner in = new Scanner(System.in);
        String inputDecision;

        while(true) {
            inputDecision = in.nextLine().trim().toLowerCase();
            if (inputDecision.equals("si") || inputDecision.equals("no")) break;
            System.out.println("La respuesta no es la esperada. Ingrese si o no para decidir");
        }

        decisionTomada = true;

        return (inputDecision.equals("si"));
    }


}
