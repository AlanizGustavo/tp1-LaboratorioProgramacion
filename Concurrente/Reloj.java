package Concurrente;

import java.sql.Timestamp;

class Reloj implements Runnable {

    @Override
    public void run() {
        System.out.println("HORA ACTUAL: " + new Timestamp(System.currentTimeMillis()));

    }
}
