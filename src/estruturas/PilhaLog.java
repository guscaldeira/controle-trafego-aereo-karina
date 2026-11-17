package estruturas;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Pilha (LIFO) implementada manualmente com nós encadeados.
 * Usada como Log de eventos críticos do Controle de Tráfego Aéreo:
 * o evento mais recente fica no topo, o que facilita a auditoria rápida.
 *
 * Não usa nenhuma Collection do Java (Stack, ArrayList, etc.).
 */
public class PilhaLog {

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private NoPilha topo;
    private int tamanho;

    public PilhaLog() {
        this.topo = null;
        this.tamanho = 0;
    }

    /** Insere (empilha) um novo evento no topo, com data e hora atuais. */
    public void adicionar(String evento) {
        String agora = LocalDateTime.now().format(FORMATO);
        NoPilha novo = new NoPilha(evento, agora);
        novo.setAbaixo(topo);
        topo = novo;
        tamanho++;
    }

    /** Remove (desempilha) o evento do topo e o devolve. Retorna null se vazia. */
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

    /** Consulta o evento do topo sem removê-lo. */
    public NoPilha topo() {
        return topo;
    }

    /**
     * Busca o evento mais recente que contenha o texto informado
     * (ex.: o código do voo). Retorna null se não achar.
     */
    public NoPilha buscar(String termo) {
        NoPilha atual = topo;
        while (atual != null) {
            if (atual.getEvento().contains(termo)) {
                return atual;
            }
            atual = atual.getAbaixo();
        }
        return null;
    }

    /** Lista todos os eventos, do mais recente (topo) para o mais antigo. */
    public void listar() {
        if (estaVazia()) {
            System.out.println("Log de eventos vazio.");
            return;
        }
        System.out.println("=== LOG DE EVENTOS CRÍTICOS (mais recente primeiro) ===");
        NoPilha atual = topo;
        int posicao = 1;
        while (atual != null) {
            System.out.println(posicao + ". " + atual);
            atual = atual.getAbaixo();
            posicao++;
        }
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public int tamanho() {
        return tamanho;
    }
}
