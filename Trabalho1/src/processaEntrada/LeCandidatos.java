package processaEntrada;
import java.io.FileInputStream;
import java.util.*;
import eleicoes.*;

public class LeCandidatos{

    public static void leitura(List<Candidato> candidatos, HashMap<Integer, Partido> partidos, HashMap<Integer, Federacao> federacoes) {
        
        try(FileInputStream fin = new FileInputStream("teste.csv");
            Scanner s = new Scanner(fin, "ISO-8859-1")){
            s.nextLine();
            
            while(s.hasNextLine()){
                String line = s.nextLine();

                try(Scanner lineScanner = new Scanner(line)){
                    lineScanner.useDelimiter(";");

                    setScanner(lineScanner, 16);
                    String numero = lineScanner.next();
                    setScanner(lineScanner, 1);
                    String nome = lineScanner.next();
                    setScanner(lineScanner, 8);
                    String numPartido = lineScanner.next();
                    String siglaPartido = lineScanner.next();
                    String nomePartido = lineScanner.next();
                    String numFederacao = lineScanner.next();
                    String nomeFederacao = lineScanner.next();
                    setScanner(lineScanner, 10);
                    String dataNascimento = lineScanner.next();
                    setScanner(lineScanner, 2);
                    String genero = lineScanner.next();
                    setScanner(lineScanner, 10);
                    String candidatoEleito = lineScanner.next();
                    setScanner(lineScanner, 10);
                    String destVotos = lineScanner.next();

                    Federacao f = Federacao.verificaFederacao(Integer.parseInt(numFederacao), nomeFederacao, federacoes);
                    Partido p = Partido.verificaPartido(Integer.parseInt(numPartido), siglaPartido, nomePartido, f, partidos);
                    Candidato c = new Candidato(Integer.parseInt(numero), nome, p, dataNascimento, Boolean.parseBoolean(candidatoEleito), genero);
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