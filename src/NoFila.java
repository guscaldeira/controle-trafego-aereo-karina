public class NoFila {

    private Voo voo;
    private NoFila proximo;

    public NoFila(Voo voo) {
        this.voo = voo;
        this.proximo = null;
    }

    public Voo getVoo() {
        return voo;
    }

    public NoFila getProximo() {
        return proximo;
    }

    public void setProximo(NoFila proximo) {
        this.proximo = proximo;
    }
}
