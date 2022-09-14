package Composite;

import java.util.ArrayList;

class Tag extends CompositeElement {
    private ArrayList<CompositeElement> children = new ArrayList<>();

    public Tag(String nombre, int id) {
        super(nombre, id);
    }

    public void add(CompositeElement child) {
        children.add(child);
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
                    buscado = ((Tag) this.children.get(i)).buscarTag(id);
                }
                i++;
            }
        }
        return buscado;
    }

    public void eliminarTag(CompositeElement element) {
        this.children.remove(element);
    }

    public String randerizar() {
        String resultado = "\n<" + tagName + " id=" + id + ">\n";
        for (CompositeElement child : children) {
            resultado += child.randerizar();
        }
        resultado += "\n</" + tagName + ">";
        return resultado;
    }
}
