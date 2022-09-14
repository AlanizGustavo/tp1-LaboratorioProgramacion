package Composite;

class Text extends CompositeElement {
    private String texto;

    // constructor
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

    public void add(CompositeElement child) {
        System.out.println("No es posible agregar a " + child + " como hijo");
        ;
    }

    public String randerizar() {
        String resultado = "\n<" + tagName + " id=" + id + ">\n";
        resultado += this.texto;
        resultado += "\n</" + tagName + ">";
        return resultado;
    }
}
