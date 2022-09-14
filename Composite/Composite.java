package Composite;

import java.util.Scanner;

public class Composite {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Tag body = new Tag("body", 0);

        int opcion;
        do {
            System.out.println(body.randerizar());
            System.out.println("-------------------------");
            System.out.println("Menu de opciones");
            System.out.println("1. Agregar un div");
            System.out.println("2. Agregar un texto");
            System.out.println("3. Eliminar un tag");
            System.out.println("4. Salir");
            System.out.println("Ingrese una opcion: ");
            System.out.println("-------------------------");

            opcion = in.nextInt();
            switch (opcion) {
                case 1: {
                    System.out.println("Ingrese El id del tag al que desea agregarlo");
                    int id = in.nextInt();
                    CompositeElement padre = body.buscarTag(id);
                    System.out.println(padre.getNombre());
                    if (padre.getNombre() == "texto") {
                        System.out.println("NO ES POSIBLE AGREGAR UN DIV DENTRO DE TEXTO");
                    } else {
                        System.out.println("Ingrese El id del nuevo tag");
                        id = in.nextInt();
                        padre.add(new Tag("div", id));
                    }
                    break;
                }
                case 2: {
                    System.out.println("Ingrese El id del tag al que desea agregarlo");
                    int id = in.nextInt();
                    CompositeElement padre = body.buscarTag(id);
                    if (padre.getNombre() == "texto") {
                        System.out.println("NO ES POSIBLE AGREGAR UN TEXTO DENTRO DE TEXTO");
                    } else {
                        System.out.println("Ingrese El id del nuevo tag");
                        String texto = in.nextLine();
                        padre.add(new Text("texto", texto));
                    }
                    break;
                }
                case 3: {
                    System.out.println("Ingrese El id del tag al que desea eliminar un hijo");
                    int id = in.nextInt();
                    CompositeElement padre = body.buscarTag(id);
                    System.out.println("Ingrese El id del elemento que desea eliminar");
                    int idHijo = in.nextInt();
                    CompositeElement hijo = padre.buscarTag(idHijo);
                    padre.eliminarTag(hijo);
                    break;
                }
                case 4:
                    System.out.println("Saliendo...");
                    break;
            }
        } while (opcion != 4);

        System.out.println(body.randerizar());

    }
}
