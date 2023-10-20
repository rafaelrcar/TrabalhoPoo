package processaEntrada;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

import eleicoes.*;

public class LeCandidatos{

    public static void leitura(List<Candidato> candidatos, HashMap<Integer, Partido> partidos, HashMap<Integer, Federacao> federacoes, int tipoCandidato) {
        
        try(FileInputStream fin = new FileInputStream("teste.csv");
            Scanner s = new Scanner(fin, "ISO-8859-1")){
            s.nextLine();
            
            while(s.hasNextLine()){
                String line = s.nextLine();

                try(Scanner lineScanner = new Scanner(line)){
                    lineScanner.useDelimiter("\";\"");

                    setScanner(lineScanner, 13);
                    String cargo = lineScanner.next();

                    if(Integer.parseInt(cargo) == tipoCandidato){
                        setScanner(lineScanner, 2);
                        String numero = lineScanner.next();
                        setScanner(lineScanner, 1);
                        String nome = lineScanner.next();
                        setScanner(lineScanner, 8);
                        String numeroPartido = lineScanner.next();
                        String siglaPartido = lineScanner.next();
                        String nomePartido = lineScanner.next();
                        String numeroFederacao = lineScanner.next();
                        String nomeFederacao = lineScanner.next();
                        setScanner(lineScanner, 10);
                        String dataNascimento = lineScanner.next();
                        setScanner(lineScanner, 2);
                        String genero = lineScanner.next();
                        setScanner(lineScanner, 10);
                        String candidatoEleito = lineScanner.next();
                        setScanner(lineScanner, 10);
                        String destinacaoVotos = lineScanner.next();
                        String situacaoCandidato = lineScanner.next();


                        Federacao f = Federacao.verificaFederacao(Integer.parseInt(numeroFederacao), nomeFederacao, federacoes);
                        Partido p = Partido.verificaPartido(Integer.parseInt(numeroPartido), siglaPartido, nomePartido, f, partidos);

                        Candidato cf = new Candidato(Integer.parseInt(numero), nome, p, 
                        LocalDate.parse(dataNascimento,DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
                        Boolean.parseBoolean(candidatoEleito), Integer.parseInt(genero), destinacaoVotos, Integer.parseInt(situacaoCandidato));
                        candidatos.add(cf);
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println("arquivo nao encontrado");
            e.printStackTrace();
        }
    }

    public static void setScanner(Scanner lineScanner, int q){
        while(q != 0){
            lineScanner.next();
            q--;
        }
    }
}