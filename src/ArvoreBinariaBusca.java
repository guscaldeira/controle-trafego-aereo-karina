public class ArvoreBinariaBusca {

    private NoArvore raiz;

    public ArvoreBinariaBusca() {
        this.raiz = null;
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    public boolean inserir(Voo voo) {
        if (buscar(voo.getCodigo()) != null) {
            return false;
        }
        raiz = inserirRec(raiz, voo);
        return true;
    }

    private NoArvore inserirRec(NoArvore atual, Voo voo) {
        if (atual == null) {
            return new NoArvore(voo);
        }
        int cmp = voo.getCodigo().compareTo(atual.voo.getCodigo());
        if (cmp < 0) {
            atual.esquerda = inserirRec(atual.esquerda, voo);
        } else {
            atual.direita = inserirRec(atual.direita, voo);
        }
        return atual;
    }

    public Voo buscar(String codigo) {
        NoArvore atual = raiz;
        while (atual != null) {
            int cmp = codigo.compareTo(atual.voo.getCodigo());
            if (cmp == 0) {
                return atual.voo;
            } else if (cmp < 0) {
                atual = atual.esquerda;
            } else {
                atual = atual.direita;
            }
        }
        return null;
    }

    public boolean remover(String codigo) {
        if (buscar(codigo) == null) {
            return false;
        }
        raiz = removerRec(raiz, codigo);
        return true;
    }

    private NoArvore removerRec(NoArvore atual, String codigo) {
        if (atual == null) {
            return null;
        }
        int cmp = codigo.compareTo(atual.voo.getCodigo());
        if (cmp < 0) {
            atual.esquerda = removerRec(atual.esquerda, codigo);
        } else if (cmp > 0) {
            atual.direita = removerRec(atual.direita, codigo);
        } else {
            if (atual.esquerda == null && atual.direita == null) {
                return null;
            }
            if (atual.esquerda == null) {
                return atual.direita;
            }
            if (atual.direita == null) {
                return atual.esquerda;
            }
            NoArvore sucessor = menorNo(atual.direita);
            atual.voo = sucessor.voo;
            atual.direita = removerRec(atual.direita, sucessor.voo.getCodigo());
        }
        return atual;
    }

    private NoArvore menorNo(NoArvore no) {
        while (no.esquerda != null) {
            no = no.esquerda;
        }
        return no;
    }

    public void percursos() {
        if (estaVazia()) {
            System.out.println("Nenhum voo programado.");
            return;
        }
        System.out.println("--- Em ordem ---");
        emOrdem();
        System.out.println("--- Pre-ordem ---");
        preOrdem();
        System.out.println("--- Pos-ordem ---");
        posOrdem();
    }

    public void emOrdem() {
        if (estaVazia()) {
            System.out.println("Nenhum voo programado.");
            return;
        }
        emOrdemRec(raiz);
    }

    private void emOrdemRec(NoArvore no) {
        if (no == null) return;
        emOrdemRec(no.esquerda);
        System.out.println(no.voo);
        emOrdemRec(no.direita);
    }

    public void preOrdem() {
        if (estaVazia()) {
            System.out.println("Nenhum voo programado.");
            return;
        }
        preOrdemRec(raiz);
    }

    private void preOrdemRec(NoArvore no) {
        if (no == null) return;
        System.out.println(no.voo);
        preOrdemRec(no.esquerda);
        preOrdemRec(no.direita);
    }

    public void posOrdem() {
        if (estaVazia()) {
            System.out.println("Nenhum voo programado.");
            return;
        }
        posOrdemRec(raiz);
    }

    private void posOrdemRec(NoArvore no) {
        if (no == null) return;
        posOrdemRec(no.esquerda);
        posOrdemRec(no.direita);
        System.out.println(no.voo);
    }
}
