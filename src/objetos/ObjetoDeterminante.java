package objetos;
import gameSystem.Checkpoints;

// Esta clase es para los objetos que activan los checkpoints.
public class ObjetoDeterminante extends Objeto {
    private int nCheckpoint; // numero de checkpoint que activa
    private boolean checkpointActivo = false;

    public ObjetoDeterminante(String nombre, String descripcion1, String descripcion2, String interaccion1, String interaccion2, int nCheckpoint) {
        super(nombre, descripcion1, descripcion2, interaccion1, interaccion2);
        this.nCheckpoint = nCheckpoint;
    }

    @Override
    public void interactuar() {
        if (!checkpointActivo) {
            System.out.println(interaccion1);

            Checkpoints.activarCheckpoint(nCheckpoint);
            checkpointActivo = true;

            return;
        }

        System.out.println(interaccion2);
    }

    // Override para que varíe la descripcion del objeto mesita
    @Override
    public void observar() {
        if (getNombre().equals("mesita")) {
          if (Checkpoints.isActive(3)) {
              System.out.println(descripcion2);
          } else{
              System.out.println(descripcion1);
          }

          return;
        }

        super.observar();
    }
}
