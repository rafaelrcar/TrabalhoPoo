import eleicoes.*;
import processaEntrada.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.stream.Collectors;


public class App {
    public static void main(String[] args) {
        try{
            HashMap<Integer, Candidato> hashCandidatos = new HashMap<>();
            HashMap<Integer, Partido> hashPartidos = new HashMap<>();
            
            if (args.length < 4) {
                throw new IllegalArgumentException("Argumentos insuficientes. Forneça os argumentos necessários.");
            }
            LocalDate data = LocalDate.parse(args[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            if(args[0].equals("--federal")){
                LeCandidatos.leitura(hashCandidatos, hashPartidos, 6, args[1]);
                LeVotacao.leitura(hashCandidatos, hashPartidos, 6, args[2]);
            }
            else if(args[0].equals("--estadual")){
                LeCandidatos.leitura(hashCandidatos, hashPartidos, 7, args[1]);
                LeVotacao.leitura(hashCandidatos, hashPartidos, 7, args[2]);
            }

            ArrayList<Candidato> listCandidatos = hashCandidatos.values().stream().collect(Collectors.toCollection(ArrayList::new)); 
            Collections.sort(listCandidatos, new Candidato.CandidatoComparator());

            ArrayList<Partido> listPartidos = hashPartidos.values().stream().collect(Collectors.toCollection(ArrayList::new)); 
            Collections.sort(listPartidos, new Partido.PartidoComparator());

            Locale brLocale = Locale.forLanguageTag("pt-BR");
            NumberFormat nf = NumberFormat.getInstance(brLocale);

            int quantidadeVagas = ProcessaEleicoes.processaQuantidadeVagas(hashCandidatos, nf);   
            if(args[0].equals("--federal")){
                System.out.println("Deputados federais eleitos:");
            }
            else if(args[0].equals("--estadual")){
            System.out.println("Deputados estaduais eleitos:");
            }
            ProcessaEleicoes.processaEleitos(listCandidatos, nf);
            ProcessaEleicoes.processaMaisVotados(listCandidatos, quantidadeVagas, nf);
            ProcessaEleicoes.processaNaoEleitosMajoritaria(listCandidatos, quantidadeVagas, nf);
            ProcessaEleicoes.processaEleitosSistemaProporcional(listCandidatos, quantidadeVagas, nf);
            ProcessaEleicoes.processaPartidos(listPartidos, nf);
            ProcessaEleicoes.processaPrimeiroUltimoColocados(listPartidos, nf);
            ProcessaEleicoes.processaFaixaEtaria(listCandidatos, quantidadeVagas, data);
            ProcessaEleicoes.processaGenero(listCandidatos, quantidadeVagas);
            ProcessaEleicoes.processaVotosTotais(listPartidos, nf);
        }
        catch(IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
        catch(DateTimeParseException  e){
            System.out.println("Fomato de data inválido");
        }
        catch(FileNotFoundException e){
            System.out.println("Erro ao abrir o arquivo: " + e.getMessage());
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}