package eleicoes;

import java.util.Date;

public class Candidato {
    private int numeroCandidato; //NR_CANDIDATO (16)
    private String nomeUrna; //NM_URNA_CANDIDATO (18)
    private Partido partido; //NR_PARTIDO (27) e SG_PARTIDO (28) e NM_PARTIDO (29) e NR_FEDERACAO (30) e NM_FEDERACAO (31)
    private Date dataNascimento; //DT_NASCIMENTO (42)
    private Boolean candidatoEleito; //CD_SIT_TOT_TURNO (56)
    private int genero; //CD_GENERO (45)
    private int destVotos; //NM_TIPO_DESTINACAO_VOTOS (66)

    public Candidato(int numeroCandidato, String nomeUrna, Partido partido, Date dataNascimento,
            Boolean candidatoEleito, int genero) {
        this.numeroCandidato = numeroCandidato;
        this.nomeUrna = nomeUrna;
        this.partido = partido;
        this.dataNascimento = dataNascimento;
        this.candidatoEleito = candidatoEleito;
        this.genero = genero;
        
    }

    public void setNumeroVotos(int destVotos) {
        this.destVotos = destVotos;
    }
    
}
