package Composite;

class Text extends CompositeElement {
    private String texto;

    public Text(String nombre, String texto, int id) {
        super(nombre, id);
        this.texto = texto;
    }

    public boolean isText() {
        return true;
    }

    public String getTexto() {
        return this.texto;
    }

    public String randerizar() {
        String resultado = "<" + tagName + " id=" + id + ">\n";
        resultado += this.texto;
        resultado += "\n</" + tagName + ">\n";
        return resultado;
    }
}
