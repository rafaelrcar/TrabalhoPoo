import eleicoes.*;
import processaEntrada.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.stream.Collectors;


public class App {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, Candidato> hashCandidatos = new HashMap<>();
        HashMap<Integer, Partido> hashPartidos = new HashMap<>();
        HashMap<Integer, Federacao> federacoes = new HashMap<>();
        
        String nomeArquivo = null;
        PrintWriter writer = null;

        if(args[0].equals("--federal")){
            LeCandidatos.leitura(hashCandidatos, hashPartidos, federacoes, 6, args[1]);
            LeVotacao.leitura(hashCandidatos, hashPartidos, federacoes, 6, args[2]);
            nomeArquivo = "output-federal.txt";
        }
        else if(args[0].equals("--estadual")){
            LeCandidatos.leitura(hashCandidatos, hashPartidos, federacoes, 7, args[1]);
            LeVotacao.leitura(hashCandidatos, hashPartidos, federacoes, 7, args[2]);
            nomeArquivo = "output-estadual.txt";
        }

        try {
            if (nomeArquivo != null)
                writer = new PrintWriter(nomeArquivo, "ISO-8859-1");
            else{
                System.err.println("Não foi possível selecionar o tipo de candidato. A entrada deve seguir o padrão:");
                System.err.println("java -jar deputados.jar <opção_de_cargo> <caminho_arquivo_candidatos> <caminho_arquivo_votacao> <data>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Candidato> listCandidatos = hashCandidatos.values().stream().collect(Collectors.toCollection(ArrayList::new)); 
        Collections.sort(listCandidatos, new Candidato.CandidatoComparator());

        ArrayList<Partido> listPartidos = hashPartidos.values().stream().collect(Collectors.toCollection(ArrayList::new)); 
        Collections.sort(listPartidos, new Partido.PartidoComparator());

        Locale brLocale = Locale.forLanguageTag("pt-BR");
		NumberFormat nf = NumberFormat.getInstance(brLocale);

        int quantidadeVagas = ProcessaEleicoes.processaQuantidadeVagas(hashCandidatos, writer, nf);   
        if(args[0].equals("--federal")){
            writer.println("Deputados federais eleitos:");
        }
        else if(args[0].equals("--estadual")){
            writer.println("Deputados estaduais eleitos:");
        }
        ProcessaEleicoes.processaEleitos(listCandidatos, writer, nf);
        ProcessaEleicoes.processaMaisVotados(listCandidatos, quantidadeVagas, writer, nf);
        ProcessaEleicoes.processaNaoEleitosMajoritaria(listCandidatos, quantidadeVagas, writer, nf);
        ProcessaEleicoes.processaEleitosSistemaProporcional(listCandidatos, quantidadeVagas, writer, nf);
        ProcessaEleicoes.processaPartidos(listPartidos, writer, nf);
        ProcessaEleicoes.processaPrimeiroUltimoColocados(listPartidos, writer, nf);
        ProcessaEleicoes.processaFaixaEtaria(listCandidatos, quantidadeVagas, LocalDate.parse(args[3], DateTimeFormatter.ofPattern("dd/MM/yyyy")), writer);
        ProcessaEleicoes.processaGenero(listCandidatos, quantidadeVagas, writer);
        ProcessaEleicoes.processaVotosTotais(listPartidos, writer, nf);

        writer.close();
    }
}