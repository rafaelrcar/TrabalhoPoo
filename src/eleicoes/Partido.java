package eleicoes;
import enums.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import enums.CandidatoEleito;

public class Partido {
    private int numeroPartido;
    private String siglaPartido;
    private String nomePartido;
    private int legendaPartido;
    private int votosNominais;
    private Federacao federacao;
    private List<Candidato> candidatos = new ArrayList<>();

    public Partido(int numeroPartido, String siglaPartido, String nomePartido, Federacao federacao) {
        this.numeroPartido = numeroPartido;
        this.nomePartido = nomePartido;
        this.siglaPartido = siglaPartido;
        this.federacao = federacao;
    }

    public void setVotosNominais(int votosNominais){
        this.votosNominais += votosNominais;
    }

    public int getNumeroPartido() {
        return numeroPartido;
    }

    public String getNomePartido() {
        return this.nomePartido;
    }

    public int getLegendaPartido() {
        return legendaPartido;
    }
    
    public int getVotosNominais() {
        return votosNominais;
    }

    public String getSiglaPartido() {
        return siglaPartido;
    }
    
    public Federacao getFederacao() {
        return federacao;
    }

    public List<Candidato> getCandidatos() {
        return new ArrayList<>(candidatos);
    }

    public static Partido verificaPartido(int numeroPartido, String siglaPartido, String nomePartido, Federacao f, HashMap<Integer,Partido> partidos){
        if(partidos.containsKey(numeroPartido))
            return partidos.get(numeroPartido);
        else{
            Partido p = new Partido(numeroPartido, siglaPartido, nomePartido, f);
            partidos.put(numeroPartido, p);

            return p;
        }
    }

    public void adicionaCandidatos(Candidato c){
        candidatos.add(c);
    }

    public void setLegendaPartido(int legendaPartido) {
        this.legendaPartido += legendaPartido;

    }

    public int calculaEleitos(){
        int count = 0;

        for(Candidato c: candidatos){
            if(c.getCandidatoEleito() == CandidatoEleito.ELEITO)
                count++;
        }

        return count;
    }

    public static class PartidoComparator implements Comparator<Partido> {
        @Override
        public int compare( Partido entry1, Partido entry2) {
            int votos1 = entry1.legendaPartido + entry1.votosNominais;
            int votos2 = entry2.legendaPartido + entry2.votosNominais;

            if(votos1 != votos2)
                return Integer.compare(votos2, votos1);
            else
                return Integer.compare(entry1.getNumeroPartido(), entry2.getNumeroPartido());
        }
    }
}
