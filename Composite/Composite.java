package Composite;

import java.util.Scanner;

public class Composite {
    static String etiquetaBody = "body";
    static String etiquetaP = "p";
    static String etiquetaDiv = "div";
    static int nuevoId = 0;
    static Scanner in = new Scanner(System.in);
    static Tag body = new Tag(etiquetaBody, nuevoId++);

    public static void main(String[] args) {

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
                case 1 -> {
                    CompositeElement padre;
                    // Buscamos el padre
                    if (nuevoId == 1) {
                        padre = body;
                    } else {
                        System.out.println("BUSCANDO ID DEL PADRE");
                        padre = buscarTag();
                    }
                    // Ahora agregamos el div
                    if (padre != null) {
                        if (padre.getNombre().equals(etiquetaP)) {
                            System.out.println("No se puede agregar un div a un texto");
                        } else {
                            ((Tag) padre).add(new Tag(etiquetaDiv, nuevoId++));
                        }
                    } else {
                        System.out.println("No se encontro el tag");
                    }
                }
                case 2 -> {
                    CompositeElement padre;
                    // Buscamos el padre
                    if (nuevoId == 1) {
                        padre = body;
                    } else {
                        System.out.println("BUSCANDO ID DEL PADRE");
                        padre = buscarTag();
                    }
                    // Ahora agregamos el texto
                    if (padre != null) {
                        if (padre.getNombre().equals(etiquetaP)) {
                            System.out.println("No se puede agregar un texto a un texto");
                        } else {
                            System.out.print("Ingrese El texto: ");
                            String texto = in.next();
                            ((Tag) padre).add(new Text(etiquetaP, texto, nuevoId++));
                        }
                    }
                }
                case 3 -> {
                    System.out.println("BUSCANDO ID PARA BORRAR");
                    CompositeElement buscado = buscarTag();
                    if (buscado != null) {
                        if (buscado.getNombre().equals(etiquetaBody)) {
                            System.out.println("Seguro que desea eliminar el body? (s/n)");
                            String respuesta = in.next();
                            if (respuesta.equals("s") || respuesta.equals("S")) {
                                body = new Tag(etiquetaBody, 0);
                                opcion = 5;
                            }
                        } else {
                            ((Tag) buscado).eliminarTag(buscarTag());
                        }
                    }
                }
                case 4 -> {
                    ejemploArmado(body);
                }
                case 5 -> {
                    System.out.println("Saliendo...");
                    System.out.println(body.randerizar());
                    break;
                }
            }
        } while (opcion < 4);

    }

    public static CompositeElement buscarTag() {
        System.out.print("Ingrese El id del tag: ");
        return body.buscarTag(in.nextInt());
    }

    public static void ejemploArmado(Tag body) {
        Tag div1 = new Tag(etiquetaDiv, nuevoId++);
        Tag div2 = new Tag(etiquetaDiv, nuevoId++);
        Tag div3 = new Tag(etiquetaDiv, nuevoId++);
        Tag div4 = new Tag(etiquetaDiv, nuevoId++);
        Text text1 = new Text(etiquetaP, "texto 1", nuevoId++);
        Text text2 = new Text(etiquetaP, "texto 2", nuevoId++);

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
