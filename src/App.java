import eleicoes.*;
import processaEntrada.LeCandidatos;
import processaEntrada.LeVotacao;
import java.util.HashMap;

public class App {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, Candidato> candidatos = new HashMap<>();
        HashMap<Integer, Partido> partidos = new HashMap<>();
        HashMap<Integer, Federacao> federacoes = new HashMap<>();

        //if(args[0] == "--federal"){
            //LeCandidatos.leitura(candidatos, partidos, federacoes, 6);
            //LeVotacao.leitura(candidatos, partidos, federacoes, 6);
       // }
       // else if(args[0] == "--estadual"){
            LeCandidatos.leitura(candidatos, partidos, federacoes, 7);
            LeVotacao.leitura(candidatos, partidos, federacoes, 7);
        //}
        
        for (HashMap.Entry<Integer, Partido> entry : partidos.entrySet()) {
            int chave = entry.getKey();
            Partido valor = entry.getValue();
            System.out.println(valor.getSiglaPartido() + ": " + (valor.getLegendaPartido()+valor.getVotosNominais()) + "(" + valor.getVotosNominais() + " nominal e " + valor.getLegendaPartido() + " de legenda)");
        }
    }
}