package Composite;

import java.util.ArrayList;

import utiles.TecladoIn;

public class Composite {
    private static int identificador = 0;
    //Hola 

    public static void main(String[] args) {
        ArrayList<Tag> tags = new ArrayList<>();
        identificador += 1;
        Tag body = new Tag("body", identificador);
        tags.add(body);
        menu(tags, body);
    }

    private static void menu(ArrayList<Tag> tags, Tag raiz) {
        int opcion;
        do {
            System.out.println("-------------------------");
            System.out.println(tags.get(0).randerizar());
            System.out.println("-------------------------");
            System.out.println("Menu de opciones de " + raiz.getTagName() + " id:" + raiz.getId());
            System.out.println("1. Agregar un div");
            System.out.println("2. Agregar un p");
            System.out.println("3. Moverse a otro Tag");
            System.out.println("4. Agregar texto");
            System.out.println("5. Salir");
            System.out.println("Ingrese una opcion: ");
            opcion = TecladoIn.readLineInt();
            System.out.println("-------------------------");
            switch (opcion) {
                case 1 -> {
                    if (!raiz.getTagName().equals("p")) {
                        identificador += 1;
                        Tag div = new Tag("div", identificador);
                        raiz.add(div);
                        tags.add(div);
                    } else {
                        System.out.println("No se puede agregar un div dentro de un p");
                    }
                }
                case 2 -> {
                    identificador += 1;
                    tags.add(new Tag("p", identificador));
                    raiz.add(tags.get(identificador - 1));
                }
                case 3 -> {
                    System.out.println(tags.get(0).randerizar());
                    System.out.println("Que id tiene el tag que desea agregarle?");
                    int id = TecladoIn.readLineInt();
                    menu(tags, buscarTagId(raiz, id));
                }
                case 4 -> {
                    raiz.add(new Text("Texto"));
                }
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion incorrecta");
            }
        } while (opcion != 5);
    }

    private static Tag buscarTagId(Tag puntero, int id) {
        if (puntero.getId() == id) {
            return puntero;
        } else {
            for (int i = 0; i < puntero.getTags().size(); i++) {
                Tag tag = buscarTagId((Tag) puntero.getTags().get(i), id);
                if (tag != null) {
                    return tag;
                }
            }
        }
        return null;
    }
}

interface CompositeElement {
    String randerizar();
}

class Tag implements CompositeElement {
    private String tagName;
    private int id;
    private ArrayList<CompositeElement> children = new ArrayList<>();

    // constructor
    Tag(String nombre, int id) {
        this.tagName = nombre;
        this.id = id;
    }

    public void add(CompositeElement child) {
        children.add(child);
    }

    @Override
    public String randerizar() {
        String resultado = "\n<" + tagName + " id=" + id + ">\n";
        for (CompositeElement child : children) {
            resultado += child.randerizar();
        }
        resultado += "\n</" + tagName + ">";
        return resultado;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<CompositeElement> getTags() {
        return children;
    }

    public void setTags(ArrayList<CompositeElement> tags) {
        this.children = tags;
    }
}

class Text implements CompositeElement {
    private String texto;

    Text(String texto) {
        this.texto = texto;
    }

    @Override
    public String randerizar() {
        return this.texto;
    }
}