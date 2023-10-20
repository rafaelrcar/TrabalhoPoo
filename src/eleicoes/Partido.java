package eleicoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public static Partido verificaPartido(int numeroPartido, String siglaPartido, String nomePartido, Federacao f, HashMap<Integer,Partido> partidos){
        if(partidos.containsKey(numeroPartido)){
            return partidos.get(numeroPartido);
        }
        else{
            Partido p = new Partido(numeroPartido, siglaPartido, nomePartido, f);
            partidos.put(numeroPartido, p);
            if(f != null){
                f.adicionaPartido(p);
            }
            return p;
        }
    }

    public void adicionaCandidatos(Candidato c){
        candidatos.add(c);
    }

    public String getNomePartido() {
        return this.nomePartido;
    }

    public void setLegendaPartido(int legendaPartido) {
        this.legendaPartido += legendaPartido;

        if(federacao != null){
            federacao.setLegendaFederacao(legendaPartido);
        }
    }

    public void setVotosNominais(int votosNominais){
        this.votosNominais += votosNominais;
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
    
}
