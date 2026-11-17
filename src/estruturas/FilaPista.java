package estruturas;

import model.InformacaoDoVoo;
public class FilaPista {

    private NoFila inicio;
    private NoFila fim;
    private int tamanho;

    public FilaPista() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public boolean estaVazia() {
        return inicio == null;
    }

    public int getTamanho() {
        return tamanho;
    }

    
    public void adicionar(InformacaoDoVoo voo) {
        NoFila novo = new NoFila(voo);
        if (estaVazia()) {
            inicio = novo;
        } else {
            fim.setProximo(novo);
        }
        fim = novo;
        tamanho++;
    }

    
    public InformacaoDoVoo remover() {
        if (estaVazia()) {
            return null;
        }
        InformacaoDoVoo voo = inicio.getVoo();
        inicio = inicio.getProximo();
        if (inicio == null) {
            fim = null;
        }
        tamanho--;
        return voo;
    }

    public InformacaoDoVoo primeiro() {
        return estaVazia() ? null : inicio.getVoo();
    }

    public InformacaoDoVoo buscar(String codigo) {
        for (NoFila atual = inicio; atual != null; atual = atual.getProximo()) {
            if (atual.getVoo().getCodigo().equalsIgnoreCase(codigo)) {
                return atual.getVoo();
            }
        }
        return null;
    }

    public int posicao(String codigo) {
        int pos = 1;
        for (NoFila atual = inicio; atual != null; atual = atual.getProximo(), pos++) {
            if (atual.getVoo().getCodigo().equalsIgnoreCase(codigo)) {
                return pos;
            }
        }
        return -1;
    }

    public InformacaoDoVoo removerPorCodigo(String codigo) {
        for (NoFila anterior = null, atual = inicio; atual != null;
            anterior = atual, atual = atual.getProximo()) {

            if (atual.getVoo().getCodigo().equalsIgnoreCase(codigo)) {
                if (anterior == null) {
                    inicio = atual.getProximo();
                } else {
                    anterior.setProximo(atual.getProximo());
                }
                if (atual == fim) {
                    fim = anterior;
                }
                tamanho--;
                return atual.getVoo();
            }
        }
        return null;
    }

    public void listar() {
        if (estaVazia()) {
            System.out.println("Fila da pista vazia.");
            return;
        }
        System.out.println("=== FILA DA PISTA (" + tamanho + " voo(s)) ===");
        int pos = 1;
        for (NoFila atual = inicio; atual != null; atual = atual.getProximo(), pos++) {
            System.out.println(pos + "º - " + atual.getVoo());
        }
    }
}
