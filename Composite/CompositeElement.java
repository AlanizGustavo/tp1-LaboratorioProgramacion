package Composite;

abstract class CompositeElement {
    protected String tagName;
    protected int id;

    public CompositeElement(String nombre, int id) {
        this.tagName = nombre;
        this.id = id;
    }

    public CompositeElement(String nombre) {
        this.tagName = nombre;
    }

    public abstract String randerizar();

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.tagName;
    }

    public boolean isText() {
        return false;
    }

}
