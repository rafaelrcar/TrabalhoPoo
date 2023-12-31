package processaEntrada;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import enums.*;
import eleicoes.*;

public class LeCandidatos{

    public static void leitura(HashMap<Integer, Candidato> candidatos, HashMap<Integer, Partido> partidos, int tipoCandidato, String arquivo) throws FileNotFoundException, IOException{
        
        try(FileInputStream fin = new FileInputStream(arquivo); Scanner s = new Scanner(fin, "ISO-8859-1")){
            s.nextLine();
            
            while(s.hasNextLine()){
                String line = s.nextLine();

                try(Scanner lineScanner = new Scanner(line)){
                    lineScanner.useDelimiter("\";\"");

                    setScanner(lineScanner, 13);
                    String cargo = lineScanner.next();
                    setScanner(lineScanner, 2);
                    String numero = lineScanner.next();
                    setScanner(lineScanner, 1);
                    String nome = lineScanner.next();
                    setScanner(lineScanner, 8);
                    String numeroPartido = lineScanner.next();
                    String siglaPartido = lineScanner.next();
                    String nomePartido = lineScanner.next();
                    String numeroFederacao = lineScanner.next();

                    Partido p = Partido.verificaPartido(Integer.parseInt(numeroPartido), siglaPartido, nomePartido, Federacao.getFederacao(Integer.parseInt(numeroFederacao)), partidos);

                    if(Integer.parseInt(cargo) == tipoCandidato){
                        
                        setScanner(lineScanner, 11);
                        String dataNascimento = lineScanner.next();
                        setScanner(lineScanner, 2);
                        String genero = lineScanner.next();
                        setScanner(lineScanner, 10);
                        String candidatoEleito = lineScanner.next();
                        setScanner(lineScanner, 10);
                        String destinacaoVotos = lineScanner.next();
                        String situacaoCandidato = lineScanner.next();

                        if(destinacaoVotos.equals("Válido (legenda)") || destinacaoVotos.equals("Válido")){
                            
                            Candidato c = new Candidato(Integer.parseInt(numero), nome, p, LocalDate.parse(dataNascimento,DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
                            CandidatoEleito.getCandidatoEleito(Integer.parseInt(candidatoEleito)), Genero.getGenero(Integer.parseInt(genero)), destinacaoVotos,
                            SituacaoCandidato.getSituacaoCandidato(Integer.parseInt(situacaoCandidato)));

                            candidatos.put(Integer.parseInt(numero), c);
                        }
                    }
                }
            }
        }
    }

    public static void setScanner(Scanner lineScanner, int q){
        while(q != 0){
            lineScanner.next();
            q--;
        }
    }
}