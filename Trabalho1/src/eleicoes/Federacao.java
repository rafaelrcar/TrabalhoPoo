package eleicoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.HashSet;

public class Federacao {
    private int numeroFederacao;
    private String nomeFederacao;
    private int legendaFederacao;
    private List<Partido> partidosFederacao = new ArrayList<>();


    public Federacao(int numeroFederacao, String nomeFederacao) {
        this.numeroFederacao = numeroFederacao;
        this.nomeFederacao = nomeFederacao;
    }

    public static Federacao verificaFederacao (int numeroFederacao, String nomeFederacao, HashMap<Integer, Federacao> federacoes){
        if(numeroFederacao == -1){
            return null;
        }
        else if(federacoes.containsKey(numeroFederacao)){
            return federacoes.get(numeroFederacao);
        }
        else{
            Federacao f = new Federacao(numeroFederacao, nomeFederacao);
            federacoes.put(numeroFederacao, f);
            return f;
        }
    }

    public void adicionaPartido(Partido p){
        partidosFederacao.add(p);
    }
}
