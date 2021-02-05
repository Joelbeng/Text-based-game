import habitaciones.*;
import gameSystem.*;

import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class Juego {
    public static void main(String[] args) {
        GameLoader gameLoader = new GameLoader();

        try {
            gameLoader.cargarJson();
        } catch (IOException e) {
            System.out.println("Hubo un error al leer el json");
            System.exit(1);
        } catch (ParseException e) {
            System.out.println("Hubo un error al parsear el json");
            System.exit(1);
        }

        gameLoader.cargarHabitaciones();

        Juego juego = new Juego();
        Personaje personaje = new Personaje();
        InputCheck inputCheck = new InputCheck();

        juego.presentarJuego(gameLoader.dameNombreDelJuego());

        String descripcionInicial = gameLoader.dameDescripcionInicial();
        System.out.println(descripcionInicial);

        Habitacion habitacion = Habitacion.dameHabitacion(1);
        habitacion.describir();

        Scanner in = new Scanner(System.in);
        String[] inputParts;


        while (!Checkpoints.isActive(6) && !Checkpoints.isActive(5)) {

                while (true) {
                    String inputJugador = in.nextLine().trim().toLowerCase();
                    inputParts = inputJugador.split(" ");
                    boolean inputValid = inputCheck.chequearInput(inputParts, habitacion.getObjetos());
                    if (inputValid) break;
                }

                String accionJugador = inputParts[0];

                if (accionJugador.equals("ir")) {
                    String direccion = inputParts[inputParts.length - 1];
                    Habitacion habitacionNueva = personaje.desplazarse(direccion, habitacion);

                    if (habitacionNueva != null) {
                        if (habitacionNueva instanceof HabitacionBloqueada && ((HabitacionBloqueada) habitacionNueva).estaBloqueada()) {
                            habitacionNueva.describir();
                            continue;
                        }

                        habitacion = habitacionNueva;
                        habitacion.describir();
                        continue;
                    }

                    continue;
                }

                String nombreObjeto = inputParts[inputParts.length - 1];
                personaje.realizarAccionConObjeto(nombreObjeto, accionJugador, habitacion.getObjetos());
            }

        System.out.println(gameLoader.dameDescripcionFinal());
        System.exit(0);
    }

    private void presentarJuego(String nombreDelJuego) {
        System.out.println(nombreDelJuego);
        System.out.println();
        System.out.println("Antes de adentrarte en esta historia, es necesario que sepas como jugar.");
        System.out.println("Las acciones que vas a poder realizar son: 'ir', 'in (interactuar)' y 'ob (observar)'.\nPara llevarlas a cabo debes escribirlas por consola, seguida de una direccion \nsi te moves, o de un objeto si es otro caso.");
        System.out.println("Ejemplo de comando para desplazarse: 'ir sur'");
        System.out.println("Ejemplo de comando para observar objeto: 'ob mesa'");
        System.out.println("Ingresa cualquier caracter y dale enter para comenzar el juego");

        Scanner in = new Scanner(System.in);
        in.nextLine();
        System.out.println("_______________________________________________________________________________________");
        System.out.println();
    }

}
