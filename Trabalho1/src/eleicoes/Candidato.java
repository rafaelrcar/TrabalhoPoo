package eleicoes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.util.Date;

public class Candidato {
    private int numeroCandidato; //NR_CANDIDATO (16)
    private String nomeUrna; //NM_URNA_CANDIDATO (18)
    private Partido partido; //NR_PARTIDO (27) e SG_PARTIDO (28) e NM_PARTIDO (29) e NR_FEDERACAO (30) e NM_FEDERACAO (31)
    private LocalDate dataNascimento; //DT_NASCIMENTO (42)
    private Boolean candidatoEleito; //CD_SIT_TOT_TURNO (56)
    private int genero; //CD_GENERO (45)
    private String destinacaoVotos; //NM_TIPO_DESTINACAO_VOTOS (66)
    private int situacaoCandidato; //CD_SITUACAO_CANDIDADO_TOT (67)
    private int quantidadeVotos; //QT_VOTOS (21)

    public Candidato(int numeroCandidato, String nomeUrna, Partido partido, LocalDate dataNascimento,
            Boolean candidatoEleito, int genero, String destVotos, int situacaoCandidato) {
        this.numeroCandidato = numeroCandidato;
        this.nomeUrna = nomeUrna;
        this.partido = partido;
        this.dataNascimento = dataNascimento;
        this.candidatoEleito = candidatoEleito;
        this.genero = genero;
        this.destinacaoVotos = destVotos;
        this.situacaoCandidato = situacaoCandidato;

        partido.adicionaCandidatos(this);
    }

    public void imprimeCandidato(){
        System.out.println("Numero do candidato: " + this.numeroCandidato);
        System.out.println("Nome do candidato: " + this.nomeUrna);
        System.out.println("Partido: " + this.partido.getNomePartido());
        DateTimeFormatter formatoSaida = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        System.out.println("Data de nascimento: " + formatoSaida.format(dataNascimento));
        System.out.println("Candidato eleito: " + this.candidatoEleito);
        System.out.println("Genero: " + this.genero);
        System.out.println("Destino dos votos: " + this.destinacaoVotos);
        System.out.println("Situacao candidato: " + this.situacaoCandidato);
    }
}