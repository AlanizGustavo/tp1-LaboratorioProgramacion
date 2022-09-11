package Concurrente;

import java.sql.Timestamp;

class Recordatorio implements Runnable {
    private Pastilla pastilla;

    public Recordatorio(Pastilla pastilla) {
        this.pastilla = pastilla;
    }

    @Override
    public void run() {
        System.out.println("¡TOMAR PASTILLA! " + pastilla.getNombre() +
                " - Hora actual: " + new Timestamp(System.currentTimeMillis()));

    }
}
