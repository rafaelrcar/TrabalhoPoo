package eleicoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.HashSet;

public class Federacao {
    private int numero;
    private String nome;
    private List<Partido> partidos = new ArrayList<>();


    public Federacao(int numero, String nome) {
        this.numero = numero;
        this.nome = nome;
    }

    public static Federacao verificaFederacao (int numFederacao, String nomeFederacao, HashMap<Integer, Federacao> federacoes){
        if(numFederacao == -1){
            return null;
        }
        else if(federacoes.containsKey(numFederacao)){
            return federacoes.get(numFederacao);
        }
        else{
            Federacao f = new Federacao(numFederacao, nomeFederacao);
            federacoes.put(numFederacao, f);
            return f;
        }
    }
}

