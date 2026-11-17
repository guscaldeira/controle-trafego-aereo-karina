package estruturas;
public class NoArvore {

    Voo voo;
    NoArvore esquerda;
    NoArvore direita;

    public NoArvore(Voo voo) {
        this.voo = voo;
        this.esquerda = null;
        this.direita = null;
    }
}
