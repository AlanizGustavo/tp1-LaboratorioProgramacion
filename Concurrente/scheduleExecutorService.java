package Concurrente;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class scheduleExecutorService {
    public static void main(String[] args) {
        final int horasDelDia = 24;
        int cantDias, horas, procesadores, i;
        Pastilla pastilla;
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Ingrese el nombre de su medicamento: ");
            pastilla = new Pastilla(in.nextLine());

            System.out.print("Ingrese cada cuantas horas debe tomarlo: ");
            horas = in.nextInt();

            System.out.print("Ingrese cuantos días debe tomarlo: ");
            cantDias = in.nextInt();
        }
        procesadores = Runtime.getRuntime().availableProcessors();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(procesadores);

        // Forma 1 - Se ejecutan todas las tareas juntas pero se el delay hace que se
        // vean en orden
        int duracion = horasDelDia * cantDias;
        System.out.println("Forma 1");
        for (i = duracion; i >= 0; i--) {
            scheduledExecutorService.schedule(new Reloj(), duracion - i, TimeUnit.SECONDS);
            if (i % horas == 0) {
                scheduledExecutorService.schedule(new Recordatorio(pastilla), duracion - i, TimeUnit.SECONDS);
            }
        }
        try {
            // Se espera a que terminen todas las tareas
            Thread.sleep(duracion * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduledExecutorService.shutdown();

        // Forma 2 - Todas las tareas se ejecutan al mismo tiempo, pero luego de 1
        // segundo se ejecuta la tarea siguiente
        System.out.println("Forma 2");
        ScheduledExecutorService scheduledExecutorService2 = Executors.newScheduledThreadPool(procesadores);
        scheduledExecutorService2.scheduleAtFixedRate(new Reloj(), 0, 1, TimeUnit.SECONDS);
        scheduledExecutorService2.scheduleAtFixedRate(new Recordatorio(pastilla), 0, horas, TimeUnit.SECONDS);
        try {
            // Se espera a que terminen todas las tareas
            Thread.sleep(duracion * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduledExecutorService2.shutdown();

        // Forma 3 - Se ejecuta la proxima tarea cuando termina la anterior
        System.out.println("Forma 3");
        ScheduledExecutorService scheduledExecutorService3 = Executors.newScheduledThreadPool(procesadores);
        scheduledExecutorService3.scheduleWithFixedDelay(new Reloj(), 0, 1, TimeUnit.SECONDS);
        scheduledExecutorService3.scheduleWithFixedDelay(new Recordatorio(pastilla), 0, horas, TimeUnit.SECONDS);
        try {
            // Se espera a que terminen todas las tareas
            Thread.sleep(duracion * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduledExecutorService3.shutdown();
    }

}
