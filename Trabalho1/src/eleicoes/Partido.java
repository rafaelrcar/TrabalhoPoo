package eleicoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Partido {
    private int numero;
    private String sigla;
    private List<Candidato> candidatos = new ArrayList<>();
    private Federacao federacao;
    private int numeroVotos;
    
    public Partido(int numero, String sigla, Federacao federacao) {
        this.numero = numero;
        this.sigla = sigla;
        this.federacao = federacao;
    }

    public static Partido verificaPartido(int numPartido, String siglaPartido, String nomePartido, Federacao f, HashMap<Integer,Partido> partidos){
        if(partidos.containsKey(numPartido)){
            return partidos.get(numPartido);
        }
        else{
            Partido p = new Partido(numPartido, siglaPartido, f);
            partidos.put(numPartido, p);
            return p;
        }
    }
}
