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
            System.out.println("4. Ejemplo ya cargado");
            System.out.println("5. Salir");
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
                        ((Tag) padre).add(new Tag("div", id));
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
                        System.out.println("Ingrese El texto");
                        String texto = in.next();
                        System.out.println("Ingrese El id del nuevo tag");
                        int idText = in.nextInt();
                        ((Tag) padre).add(new Text("p", texto, idText));
                    }
                    break;
                }
                case 3: {
                    System.out.println("Ingrese El id del tag al que desea eliminar un hijo");
                    int id = in.nextInt();
                    CompositeElement padre = body.buscarTag(id);
                    System.out.println("Ingrese El id del elemento que desea eliminar");
                    int idHijo = in.nextInt();
                    CompositeElement hijo = ((Tag) padre).buscarTag(idHijo);
                    ((Tag) padre).eliminarTag(hijo);
                    break;
                }
                case 4: {
                    ejemploArmado(body);
                    break;
                }
                case 5: {
                    System.out.println("Saliendo...");
                    System.out.println(body.randerizar());
                    break;
                }
            }
        } while (opcion < 4);

    }

    public static void ejemploArmado(Tag body) {
        Tag div1 = new Tag("div", 1);
        Tag div2 = new Tag("div", 2);
        Tag div3 = new Tag("div", 3);
        Tag div4 = new Tag("div", 4);
        Text text1 = new Text("p", "unTexto", 5);
        Text text2 = new Text("p", "otroTexto", 6);

        body.add(div1);
        body.add(div2);
        div1.add(div3);
        div3.add(text1);
        div3.add(div4);
        div4.add(text2);

        System.out.println(
                "<body id=0>\n\t<div id=1>\n\t\t<div id=3>\n\t\t\t<p id=5>\n\t\t\t\tunTexto\n\t\t\t</p>\n\t\t\t<div id=4>\n\t\t\t\t<p id=6>\n\t\t\t\t\totroTexto\n\t\t\t\t</p>\n\t\t\t</div>\n\t\t</div>\n\t</div>\n\t<div id=2>\n\t</div>\n</body>\n");
    }
}
