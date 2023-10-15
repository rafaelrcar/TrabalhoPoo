package eleicoes;

import java.util.Date;

public class CandidatoFederal extends Candidato{
    public CandidatoFederal(int numeroCandidato, String nomeUrna, Partido partido, Date dataNascimento, 
    Boolean candidatoEleito, int genero){
        super(numeroCandidato, nomeUrna, partido, dataNascimento, candidatoEleito, genero);
    }
}
