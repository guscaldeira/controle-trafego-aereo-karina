package estruturas;

import model.InformacaoDoVoo;
public class NoFila {

    private InformacaoDoVoo voo;
    private NoFila proximo;

    public NoFila(InformacaoDoVoo voo) {
        this.voo = voo;
        this.proximo = null;
    }

    public InformacaoDoVoo getVoo() {
        return voo;
    }

    public NoFila getProximo() {
        return proximo;
    }

    public void setProximo(NoFila proximo) {
        this.proximo = proximo;
    }
}
