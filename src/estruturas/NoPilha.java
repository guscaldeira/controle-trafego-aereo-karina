package estruturas;
public class NoPilha {

    private String evento;
    private String dataHora;
    private NoPilha abaixo;

    public NoPilha(String evento, String dataHora) {
        this.evento = evento;
        this.dataHora = dataHora;
        this.abaixo = null;
    }

    public String getEvento() {
        return evento;
    }

    public String getDataHora() {
        return dataHora;
    }

    public NoPilha getAbaixo() {
        return abaixo;
    }

    public void setAbaixo(NoPilha abaixo) {
        this.abaixo = abaixo;
    }

    @Override
    public String toString() {
        return "[" + dataHora + "] " + evento;
    }
}
