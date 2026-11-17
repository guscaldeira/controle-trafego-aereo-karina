import model.InformacaoDoVoo;
import estruturas.ArvoreBinariaBusca;

public class Principal {
    
    public static void main(String[] args) {
        ArvoreBinariaBusca abb = new ArvoreBinariaBusca();

        abb.inserir(new InformacaoDoVoo("LA300", "Salvador", "São Paulo", "14:30"));
        abb.inserir(new InformacaoDoVoo("LA100", "Salvador", "Recife", "09:00"));
        abb.inserir(new InformacaoDoVoo("LA200", "Salvador", "Rio", "11:15"));
        abb.inserir(new InformacaoDoVoo("LA400", "Salvador", "Brasília", "16:45"));

        abb.percursos();

        System.out.println("Busca LA200: " + abb.buscar("LA200"));
        abb.remover("LA300");
        System.out.println("Depois de remover LA300:");
        abb.emOrdem();
    }
}
