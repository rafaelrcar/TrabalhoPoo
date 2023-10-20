package processaEntrada;
import eleicoes.*;
import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.HashMap;

public class LeVotacao{

    public static void leitura(List<Candidato> candidatos, HashMap<Integer, Partido> partidos, HashMap<Integer, Federacao> federacoes, int tipoCandidato){
        
        try(FileInputStream fin = new FileInputStream("teste.csv");
            Scanner s = new Scanner(fin, "ISO-8859-1")){
            s.nextLine();
            
            while(s.hasNextLine()){
                String line = s.nextLine();
                
                try(Scanner lineScanner = new Scanner(line)){
                    setScanner(lineScanner, 17);
                    String cargo = lineScanner.next();

                    if(Integer.parseInt(cargo) == tipoCandidato){
                        setScanner(lineScanner, 2);
                        String destinoVotos = lineScanner.next();
                        int destVotos = Integer.parseInt(destinoVotos);

                        if(destVotos >=95 && destVotos <= 98){
                            setScanner(lineScanner, 2);
                            String qtdVotos = lineScanner.next();

                            
                        }
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
