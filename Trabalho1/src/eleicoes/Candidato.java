package eleicoes;

import java.util.Date;

public class Candidato {
    private int numeroCandidato;
    private String nomeUrna;
    private Partido partido;
    private Date dataNascimento;
    private Boolean candidatoEleito;
    private int genero;
    private int numeroVotos;

    public Candidato(int numeroCandidato, String nomeUrna, Partido partido, Date dataNascimento,
            Boolean candidatoEleito, int genero) {
        this.numeroCandidato = numeroCandidato;
        this.nomeUrna = nomeUrna;
        this.partido = partido;
        this.dataNascimento = dataNascimento;
        this.candidatoEleito = candidatoEleito;
        this.genero = genero;
        
    }

    public void setNumeroVotos(int numeroVotos) {
        this.numeroVotos = numeroVotos;
    }
    
}
