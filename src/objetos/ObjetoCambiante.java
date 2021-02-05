package objetos;

import objetos.Objeto;

// Esta clase es para los objetos que cambian su descripcion e interaccion, luego que el usuario interactúa con ellos
public class ObjetoCambiante extends Objeto {
    private int cantidadInteracciones;

    public ObjetoCambiante(String nombre, String descripcion1, String descripcion2, String interaccion1, String interaccion2) {
        super(nombre, descripcion1, descripcion2, interaccion1, interaccion2);
    }

    @Override
    public void interactuar() {
        if (cantidadInteracciones == 0) {
            cantidadInteracciones++;
            System.out.println(interaccion1);
            return;
        }

        System.out.println(interaccion2);
    }

    @Override
    public void observar() {
        if (cantidadInteracciones == 0) {
            System.out.println(descripcion1);
            return;
        }

        System.out.println(descripcion2);
    }

}
