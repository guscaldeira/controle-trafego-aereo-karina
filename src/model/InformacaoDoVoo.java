package model;

public class InformacaoDoVoo {
    private int codigo;
    private String origem;
    private String destino;
    private String horario;
    private String status;

    public InformacaoDoVoo(int codigo, String origem, String destino, String horario) {
        this.codigo = codigo;
        this.origem = origem;
        this.destino = destino;
        this.horario = horario;
        this.status = "Programado";
    }

    public int getCodigo() {
        return codigo;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public String getHorario() {
        return horario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Número do voo: " + codigo + " | Origem: " + origem + " | Destino: " + destino + " | Horário: " + horario + " | Status: " + status;
    }

}
