package Composite;

class Text extends CompositeElement {
    private String texto;

    // constructor
    public Text(String nombre, String texto) {
        super(nombre);
        this.texto = texto;
    }

    public boolean isText() {
        return true;
    }

    public String getTexto() {
        return this.texto;
    }
}
