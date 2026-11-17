package estruturas;

import model.InformacaoDoVoo;
public class NoArvore {
    InformacaoDoVoo voo;
    NoArvore esquerda;
    NoArvore direita;

    public NoArvore(InformacaoDoVoo voo) {
        this.voo = voo;
        this.esquerda = null;
        this.direita = null;
    }
}
