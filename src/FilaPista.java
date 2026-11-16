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

    
    public void adicionar(Voo voo) {
        NoFila novo = new NoFila(voo);
        if (estaVazia()) {
            inicio = novo;
        } else {
            fim.setProximo(novo);
        }
        fim = novo;
        tamanho++;
    }

    
    public Voo remover() {
        if (estaVazia()) {
            return null;
        }
        Voo voo = inicio.getVoo();
        inicio = inicio.getProximo();
        if (inicio == null) {
            fim = null;
        }
        tamanho--;
        return voo;
    }

    public Voo primeiro() {
        return estaVazia() ? null : inicio.getVoo();
    }

    public Voo buscar(String codigo) {
        NoFila atual = inicio;
        while (atual != null) {
            if (atual.getVoo().getCodigo().equalsIgnoreCase(codigo)) {
                return atual.getVoo();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    public int posicao(String codigo) {
        NoFila atual = inicio;
        int pos = 1;
        while (atual != null) {
            if (atual.getVoo().getCodigo().equalsIgnoreCase(codigo)) {
                return pos;
            }
            atual = atual.getProximo();
            pos++;
        }
        return -1;
    }

    public Voo removerPorCodigo(String codigo) {
        NoFila anterior = null;
        NoFila atual = inicio;

        while (atual != null) {
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
            anterior = atual;
            atual = atual.getProximo();
        }
        return null;
    }

    public void listar() {
        if (estaVazia()) {
            System.out.println("Fila da pista vazia.");
            return;
        }
        System.out.println("=== FILA DA PISTA (" + tamanho + " voo(s)) ===");
        NoFila atual = inicio;
        int pos = 1;
        while (atual != null) {
            System.out.println(pos + "º - " + atual.getVoo());
            atual = atual.getProximo();
            pos++;
        }
    }
}
