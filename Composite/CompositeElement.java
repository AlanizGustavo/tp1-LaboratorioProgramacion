package Composite;

import java.util.ArrayList;

abstract class CompositeElement {
    private String tagName;
    private int id;
    private ArrayList<CompositeElement> children = new ArrayList<>();

    public CompositeElement(String nombre, int id) {
        this.tagName = nombre;
        this.id = id;
    }

    public CompositeElement(String nombre) {
        this.tagName = nombre;
    }

    public void add(CompositeElement child) {
        children.add(child);
    }

    public String randerizar() {
        String resultado = "\n<" + tagName + " id=" + id + ">\n";
        for (CompositeElement child : children) {
            resultado += child.randerizar();
        }
        resultado += "\n</" + tagName + ">";
        return resultado;
    }

    public CompositeElement buscarTag(int id) {
        boolean encontrado = false;
        CompositeElement buscado = null;
        int i = 0;
        if (this.id == id) {
            buscado = this;
        } else {
            while (!encontrado && i < this.children.size()) {
                if (this.children.get(i).getId() == id) {
                    encontrado = true;
                    buscado = this.children.get(i);
                } else {
                    buscado = this.children.get(i).buscarTag(id);
                }
                i++;
            }
        }
        return buscado;
    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.tagName;
    }

    public void eliminarTag(CompositeElement element) {
        this.children.remove(element);

    }

    public boolean isText() {
        return false;
    }

}
