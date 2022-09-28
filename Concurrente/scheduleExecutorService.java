package Concurrente;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class scheduleExecutorService {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        final int horasDelDia = 24;
        System.out.print("Ingrese el nombre de su medicamento: ");
        Pastilla pastilla = new Pastilla(in.nextLine());

        System.out.print("Ingrese cada cuantas horas debe tomarlo: ");
        int horas = in.nextInt();

        System.out.print("Ingrese cuantos días debe tomarlo: ");
        int cantDias = in.nextInt();

        int duracion = horasDelDia * cantDias;
        int procesadores = Runtime.getRuntime().availableProcessors();
        ScheduledExecutorService ses1 = Executors.newScheduledThreadPool(procesadores);

        // Forma 1 - Se ejecutan todas las tareas juntas pero se el delay hace que se
        // vean en orden
        for (int i = duracion; i >= 0; i--) {
            ses1.schedule(new Reloj(), duracion - i, TimeUnit.SECONDS);
            if (i % horas == 0) {
                ses1.schedule(new Recordatorio(pastilla), duracion - i, TimeUnit.SECONDS);
            }
        }
        try {
            // Se espera a que terminen todas las tareas
            Thread.sleep(duracion * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ses1.shutdown();

        // Forma 2 - Todas las tareas se ejecutan al mismo tiempo, pero luego de 1
        // segundo se ejecuta la tarea siguiente
        System.out.println("Forma 2");
        ScheduledExecutorService ses2 = Executors.newScheduledThreadPool(procesadores);
        ses2.scheduleAtFixedRate(new Reloj(), 0, 1, TimeUnit.SECONDS);
        ses2.scheduleAtFixedRate(new Recordatorio(pastilla), 0, horas, TimeUnit.SECONDS);
        try {
            // Se espera a que terminen todas las tareas
            Thread.sleep(duracion * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ses2.shutdown();

        // Forma 3 - Se ejecuta la proxima tarea cuando termina la anterior
        System.out.println("Forma 3");
        ScheduledExecutorService ses3 = Executors.newScheduledThreadPool(procesadores);
        ses3.scheduleWithFixedDelay(new Reloj(), 0, 1, TimeUnit.SECONDS);
        ses3.scheduleWithFixedDelay(new Recordatorio(pastilla), 0, horas, TimeUnit.SECONDS);
        try {
            // Se espera a que terminen todas las tareas
            Thread.sleep(duracion * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ses3.shutdown();
    }

}
