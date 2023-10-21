import eleicoes.*;
import processaEntrada.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;


public class App {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, Candidato> hashCandidatos = new HashMap<>();
        HashMap<Integer, Partido> hashPartidos = new HashMap<>();
        HashMap<Integer, Federacao> federacoes = new HashMap<>();

        if(args[0].equals("--federal")){
            LeCandidatos.leitura(hashCandidatos, hashPartidos, federacoes, 6, args[1]);
            LeVotacao.leitura(hashCandidatos, hashPartidos, federacoes, 6, args[2]);
        }
        else if(args[0].equals("--estadual")){
            LeCandidatos.leitura(hashCandidatos, hashPartidos, federacoes, 7, args[1]);
            LeVotacao.leitura(hashCandidatos, hashPartidos, federacoes, 7, args[2]);
        }

        int quantidadeVagas = ProcessaEleicoes.processaQuantidadeVagas(hashCandidatos);   


        ArrayList<Candidato> listCandidatos = hashCandidatos.values().stream().collect(Collectors.toCollection(ArrayList::new)); 
        Collections.sort(listCandidatos, new Candidato.CandidatoComparator());

        ArrayList<Partido> listPartidos = hashPartidos.values().stream().collect(Collectors.toCollection(ArrayList::new)); 
        Collections.sort(listPartidos, new Partido.PartidoComparator());

        ProcessaEleicoes.processaEleitos(listCandidatos);
        ProcessaEleicoes.processaMaisVotados(listCandidatos, quantidadeVagas);
        ProcessaEleicoes.processaNaoEleitosMajoritaria(listCandidatos, quantidadeVagas);
        ProcessaEleicoes.processaEleitosSistemaProporcional(listCandidatos, quantidadeVagas);
        ProcessaEleicoes.processaPartidos(listPartidos);
        ProcessaEleicoes.processaPrimeiroUltimoColocados(listPartidos);
        ProcessaEleicoes.processaFaixaEtaria(listCandidatos, quantidadeVagas, LocalDate.parse(args[3], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        ProcessaEleicoes.processaGenero(listCandidatos, quantidadeVagas);
        ProcessaEleicoes.processaVotosTotais(listPartidos);
    }
}