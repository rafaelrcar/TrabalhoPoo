package eleicoes;

import java.time.LocalDate;
import enums.*;
import java.util.Comparator;
//import java.util.Date;

public class Candidato {
    private int numero; //NR_CANDIDATO (16)
    private String nomeUrna; //NM_URNA_CANDIDATO (18)
    private Partido partido; //NR_PARTIDO (27) e SG_PARTIDO (28) e NM_PARTIDO (29) e NR_FEDERACAO (30) e NM_FEDERACAO (31)
    private LocalDate dataNascimento; //DT_NASCIMENTO (42)
    private CandidatoEleito candidatoEleito; //CD_SIT_TOT_TURNO (56)
    private Genero genero; //CD_GENERO (45)
    private String destinacaoVotos; //NM_TIPO_DESTINACAO_VOTOS (66)
    private SituacaoCandidato situacao; //CD_SITUACAO_CANDIDADO_TOT (67)
    private int quantidadeVotos; //QT_VOTOS (21)

    public Candidato(){

    }

    public Candidato(int numero, String nomeUrna, Partido partido, LocalDate dataNascimento,
            CandidatoEleito candidatoEleito, Genero genero, String destVotos, SituacaoCandidato situacao) {
        this.numero = numero;
        this.nomeUrna = nomeUrna;
        this.partido = partido;
        this.dataNascimento = dataNascimento;
        this.candidatoEleito = candidatoEleito;
        this.genero = genero;
        this.destinacaoVotos = destVotos;
        this.situacao = situacao;

        partido.adicionaCandidatos(this);
    }

    public void setQuantidadeVotos(int quantidadeVotos) {
        if(situacao == SituacaoCandidato.DEFERIDO){
            this.quantidadeVotos += quantidadeVotos;
            partido.setVotosNominais(quantidadeVotos);
        }
        else{
            if(destinacaoVotos == "Válido (legenda)")
                partido.setLegendaPartido(quantidadeVotos);
        }
    }

    public int getNumero() {
        return numero;
    }

    public String getNomeUrna() {
        return nomeUrna;
    }

    public Partido getPartido() {
        return partido;
    }

    public CandidatoEleito getCandidatoEleito() {
        return candidatoEleito;
    }

    public int getQuantidadeVotos() {
        return quantidadeVotos;
    }
    
    public LocalDate getDataNascimento(){
        return this.dataNascimento;
    }

    public SituacaoCandidato getSituacao() {
        return this.situacao;
    }
    
    public Genero getGenero(){
        return this.genero;
    }
    
    public static class CandidatoComparator implements Comparator<Candidato> {
        @Override
        public int compare(Candidato entry1, Candidato entry2) {
            if(entry1.quantidadeVotos != entry2.quantidadeVotos)
                return Integer.compare(entry2.quantidadeVotos, entry1.quantidadeVotos); 
            else
                return entry1.getDataNascimento().compareTo(entry2.getDataNascimento());
        }
    }
}