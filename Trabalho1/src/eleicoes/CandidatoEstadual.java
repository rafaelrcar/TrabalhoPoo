package eleicoes;

import java.util.Date;

public class CandidatoEstadual extends Candidato {
    public CandidatoEstadual(int numeroCandidato, String nomeUrna, Partido partido, Date dataNascimento, 
    Boolean candidatoEleito, int genero){
        super(numeroCandidato, nomeUrna, partido, dataNascimento, candidatoEleito, genero);
    }
}
