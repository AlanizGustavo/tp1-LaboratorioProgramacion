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
        Scanner in = new Scanner(System.in);
        System.out.print("Ingrese el nombre de su medicamento: ");
        pastilla = new Pastilla(in.nextLine());

        System.out.print("Ingrese cada cuantas horas debe tomarlo: ");
        horas = in.nextInt();

        System.out.print("Ingrese cuantos días debe tomarlo: ");
        cantDias = in.nextInt();

        procesadores = Runtime.getRuntime().availableProcessors();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(procesadores);

        // Forma 1
        System.out.println("Forma 1");
        for (i = (cantDias * horasDelDia); i >= 0; i--) {
            scheduledExecutorService.schedule(new Reloj(), (horasDelDia * cantDias) - i, TimeUnit.SECONDS);
            if (i % horas == 0) {
                scheduledExecutorService.schedule(new Recordatorio(pastilla), (horasDelDia * cantDias) - i,
                        TimeUnit.SECONDS);
            }
        }
        try {
            Thread.sleep(cantDias * horasDelDia * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduledExecutorService.shutdown();

        // Forma 2
        System.out.println("Forma 2");
        ScheduledExecutorService scheduledExecutorService2 = Executors.newScheduledThreadPool(procesadores);
        scheduledExecutorService2.scheduleAtFixedRate(new Reloj(), 0, 1, TimeUnit.SECONDS);
        scheduledExecutorService2.scheduleAtFixedRate(new Recordatorio(pastilla), 0, horas, TimeUnit.SECONDS);
        try {
            Thread.sleep(cantDias * horasDelDia * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduledExecutorService2.shutdown();
    }

}
