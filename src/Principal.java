import model.InformacaoDoVoo;
import estruturas.ArvoreBinariaBusca;
import estruturas.PilhaLog;

public class Principal {
    
    public static void main(String[] args) {
        PilhaLog log = new PilhaLog();

        log.adicionar("Voo LA100: arremetida");
        log.adicionar("Voo LA200: pane no motor");
        log.adicionar("Pista 1 fechada");
        System.out.println("Removido: " + log.remover());       // tem que ser "Pista 1 fechada"
        System.out.println("Busca LA100: " + log.buscar("LA100"));

        log.listar();
    }
}
