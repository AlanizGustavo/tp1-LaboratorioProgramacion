package Composite;

import java.util.ArrayList;

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
        String html = "<" + this.tagName + ">\n";
        for (CompositeElement child : this.children) {
            html += child.randerizar();
        }
        html += "\n</" + this.tagName + ">";
        return html;
    }

    @Override
    public CompositeElement buscarTag(int id) {
        boolean encontrado = false;
        CompositeElement buscado = null;
        int i = 0;
        while (!encontrado && i < this.children.size()) {
            if (this.children.get(i).getId() == id) {
                encontrado = true;
                buscado = this.children.get(i);
            } else {
                buscado = this.children.get(i).buscarTag(id);
            }
            i++;
        }
        return buscado;
    }

    public int getId() {
        return this.id;
    }
}

interface CompositeElement {
    String randerizar();

    CompositeElement buscarTag(int id);

    int getId();
}