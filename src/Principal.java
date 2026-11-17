import java.util.Scanner;

import estruturas.ArvoreBinariaBusca;
import estruturas.FilaPista;
import estruturas.NoPilha;
import estruturas.PilhaLog;
import model.InformacaoDoVoo;
public class Principal {

    private static final Scanner entrada = new Scanner(System.in);

    private static final ArvoreBinariaBusca voos = new ArvoreBinariaBusca();
    private static final FilaPista pista = new FilaPista();
    private static final PilhaLog log = new PilhaLog();
    
    public static void main(String[] args) {
        for (int opcao = -1; opcao != 0; ) {
            exibirMenu();
            opcao = lerOpcao();
            executar(opcao);
        }
        entrada.close();

    }

    private static void exibirMenu() {
        System.out.println();
        System.out.println("===== CONTROLE DE TRÁFEGO AÉREO =====");
        System.out.println("--- Voos (ABB) ---");
        System.out.println("1  - Cadastrar voo");
        System.out.println("2  - Buscar voo");
        System.out.println("3  - Listar voos (in-ordem, pré-ordem e pós-ordem)");
        System.out.println("4  - Cancelar voo");
        System.out.println("--- Pista (Fila) ---");
        System.out.println("5  - Solicitar pista");
        System.out.println("6  - Ver fila da pista");
        System.out.println("7  - Liberar pista (próximo da fila)");
        System.out.println("--- Log de eventos críticos (Pilha) ---");
        System.out.println("8  - Registrar evento crítico");
        System.out.println("9  - Ver log");
        System.out.println("10 - Buscar evento no log");
        System.out.println("11 - Remover último evento do log");
        System.out.println("0  - Sair");
    }

    private static void executar(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarVoo();
                break;
            case 2:
                buscarVoo();
                break;
            case 3:
                voos.percursos();
                break;
            case 4:
                cancelarVoo();
                break;
            case 5:
                solicitarPista();
                break;
            case 6:
                pista.listar();
                break;
            case 7:
                liberarPista();
                break;
            case 8:
                registrarEvento();
                break;
            case 9:
                log.listar();
                break;
            case 10:
                buscarEvento();
                break;
            case 11:
                removerUltimoEvento();
                break;
            case 0:
                System.out.println("Encerrando o sistema...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void cadastrarVoo() {
        String codigo = lerCodigo("Código do voo (ex: LA100): ");
        if (codigo.isEmpty()) {
            System.out.println("O código não pode ficar vazio.");
            return;
        }
        String origem = lerTexto("Origem: ");
        String destino = lerTexto("Destino: ");
        String horario = lerTexto("Horário (ex: 14:30): ");

        InformacaoDoVoo voo = new InformacaoDoVoo(codigo, origem, destino, horario);
        if (voos.inserir(voo)) {
            System.out.println("Voo cadastrado: " + voo);
        } else {
            System.out.println("Já existe um voo com o código " + codigo + ".");
        }
    }

    private static void buscarVoo() {
        String codigo = lerCodigo("Código do voo: ");
        InformacaoDoVoo voo = voos.buscar(codigo);
        if (voo == null) {
            System.out.println("Voo " + codigo + " não encontrado.");
        } else {
            System.out.println(voo);
        }
    }

    private static void cancelarVoo() {
        String codigo = lerCodigo("Código do voo a cancelar: ");
        if (voos.buscar(codigo) == null) {
            System.out.println("Voo " + codigo + " não encontrado.");
            return;
        }
        voos.remover(codigo);
        pista.removerPorCodigo(codigo);
        log.adicionar("Voo " + codigo + ": cancelado");
        System.out.println("Voo " + codigo + " cancelado e registrado no log.");
    }

    private static void solicitarPista() {
        String codigo = lerCodigo("Código do voo: ");
        InformacaoDoVoo voo = voos.buscar(codigo);
        if (voo == null) {
            System.out.println("Voo " + codigo + " não está cadastrado.");
            return;
        }
        if (pista.buscar(codigo) != null) {
            System.out.println("O voo " + codigo + " já está na fila da pista.");
            return;
        }
        pista.adicionar(voo);
        voo.setStatus("Na fila");
        System.out.println("Voo " + codigo + " entrou na fila. Posição: " + pista.posicao(codigo));
    }

    private static void liberarPista() {
        InformacaoDoVoo voo = pista.remover();
        if (voo == null) {
            System.out.println("Fila da pista vazia.");
            return;
        }
        voo.setStatus("Liberado");
        System.out.println("Pista liberada para: " + voo);
    }

    private static void registrarEvento() {
        String evento = lerTexto("Descreva o evento crítico (ex: Voo LA100: arremetida): ");
        if (evento.isEmpty()) {
            System.out.println("O evento não pode ficar vazio.");
            return;
        }
        log.adicionar(evento);
        System.out.println("Evento registrado no log.");
    }

    private static void buscarEvento() {
        String termo = lerTexto("Termo a buscar (ex: código do voo): ");
        NoPilha evento = log.buscar(termo);
        if (evento == null) {
            System.out.println("Nenhum evento encontrado com \"" + termo + "\".");
        } else {
            System.out.println("Evento mais recente encontrado: " + evento);
        }
    }

    private static void removerUltimoEvento() {
        NoPilha removido = log.remover();
        if (removido == null) {
            System.out.println("Log de eventos vazio.");
        } else {
            System.out.println("Removido do log: " + removido);
        }
    }

    private static int lerOpcao() {
        System.out.print("Escolha uma opção: ");
        try {
            return Integer.parseInt(entrada.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return entrada.nextLine().trim();
    }

    private static String lerCodigo(String mensagem) {
        return lerTexto(mensagem).toUpperCase();
    }
}
