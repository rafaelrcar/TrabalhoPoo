import eleicoes.*;
import processaEntrada.LeCandidatos;
import processaEntrada.LeVotacao;
import java.util.HashMap;

public class App {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, Candidato> candidatos = new HashMap<>();
        HashMap<Integer, Partido> partidos = new HashMap<>();
        HashMap<Integer, Federacao> federacoes = new HashMap<>();

        if(args[3] == "--federal"){
            LeCandidatos.leitura(candidatos, partidos, federacoes, 6);
            LeVotacao.leitura(candidatos, partidos, federacoes, 6);
        }
        else if(args[3] == "--estadual"){
            LeCandidatos.leitura(candidatos, partidos, federacoes, 7);
            LeVotacao.leitura(candidatos, partidos, federacoes, 7);
        }
    }
}