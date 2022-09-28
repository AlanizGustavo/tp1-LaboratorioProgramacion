package Concurrente;

import java.sql.Timestamp;

class Reloj implements Runnable {

    public void run() {
        System.out.println("HORA ACTUAL: " + new Timestamp(System.currentTimeMillis()));
    }
}
