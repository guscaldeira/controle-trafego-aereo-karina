package estruturas;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PilhaLog {

    private static final DateTimeFormatter FORMATO =
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        private NoPilha topo;
        private int tamanho;

    public PilhaLog() {
        this.topo = null;
        this.tamanho = 0;
    }

    public void adicionar(String evento) {
        String agora = LocalDateTime.now().format(FORMATO);
        NoPilha novo = new NoPilha(evento, agora);
        novo.setAbaixo(topo);
        topo = novo;
        tamanho++;
    }

    public NoPilha remover() {
        if (estaVazia()) {
            return null;
        }
        NoPilha removido = topo;
        topo = topo.getAbaixo();
        removido.setAbaixo(null);
        tamanho--;
        return removido;
    }

    public NoPilha topo() {
        return topo;
    }

    public NoPilha buscar(String termo) {
        for (NoPilha atual = topo; atual != null; atual = atual.getAbaixo()) {
            if (atual.getEvento().contains(termo)) {
                return atual;
            }
        }
        return null;
    }

    public void listar() {
        if (estaVazia()) {
            System.out.println("Log de eventos vazio.");
            return;
        }
        System.out.println("=== LOG DE EVENTOS CRÍTICOS (mais recente primeiro) ===");
        int posicao = 1;
        for (NoPilha atual = topo; atual != null; atual = atual.getAbaixo(), posicao++) {
            System.out.println(posicao + ". " + atual);
        }
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public int tamanho() {
        return tamanho;
    }
}
