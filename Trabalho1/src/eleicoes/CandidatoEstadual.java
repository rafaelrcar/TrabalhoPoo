package eleicoes;

import java.time.LocalDate;
//import java.util.Date;

public class CandidatoEstadual extends Candidato {
    public CandidatoEstadual(int numeroCandidato, String nomeUrna, Partido partido, LocalDate dataNascimento,
    Boolean candidatoEleito, int genero, String destVotos, int situacaoCandidato){
        super(numeroCandidato, nomeUrna, partido, dataNascimento, candidatoEleito, genero, destVotos, situacaoCandidato);
    }
}
