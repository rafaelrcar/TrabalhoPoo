package eleicoes;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import enums.*;

public class ProcessaEleicoes{
    public static int processaQuantidadeVagas(HashMap<Integer, Candidato> candidatos){
        int count = 0;

        for (HashMap.Entry<Integer, Candidato> candidato : candidatos.entrySet()){
            Candidato c = candidato.getValue();
            
            if(c.getCandidatoEleito() == CandidatoEleito.ELEITO){
                count++;
            }
        }
        System.out.println("Número de vagas: " + count + "\n");

        return count;
    }

    public static void processaEleitos(ArrayList<Candidato>listCandidatos){
        int i = 1;
        for(Candidato c: listCandidatos){
            if(c.getCandidatoEleito() == CandidatoEleito.ELEITO){
                
                System.out.print(i + " - ");
                if(c.getPartido().getFederacao() != null){
                    System.out.print("*");
                }
                System.out.print(c.getNomeUrna() + " (" + c.getPartido().getSiglaPartido() + ", " + c.getQuantidadeVotos() + ")" + "\n");
                
                i++;
            }
        }
        System.out.println();
    }

    public static void processaMaisVotados(ArrayList<Candidato>listCandidatos, int quantidadeVagas){
        int i = 1;
        for(Candidato c: listCandidatos){
            
            System.out.print(i + " - ");
            if(c.getPartido().getFederacao() != null){
                System.out.print("*");
            }
            System.out.print(c.getNomeUrna() + " (" + c.getPartido().getSiglaPartido() + ", " + c.getQuantidadeVotos() + ")" + "\n");

            if(i == quantidadeVagas){
                break;
            }
            i++;
        }
        System.out.println();
    }

    public static void processaNaoEleitosMajoritaria(ArrayList<Candidato>listCandidatos, int quantidadeVagas){
        int i = 1;

        for(Candidato c: listCandidatos){
            if(c.getCandidatoEleito() == CandidatoEleito.NAO_ELEITO){
                
                System.out.print(i + " - ");
                if(c.getPartido().getFederacao() != null){
                    System.out.print("*");
                }
                System.out.print(c.getNomeUrna() + " (" + c.getPartido().getSiglaPartido() + ", " + c.getQuantidadeVotos() + ")" + "\n");
            }

            if(i == quantidadeVagas){
                break;
            }

            i++;
        }
        System.out.println();
    }
    
    public static void processaEleitosSistemaProporcional(ArrayList<Candidato> listCandidatos, int quantidadeVagas){
        int i = 1;
        for(Candidato c: listCandidatos){
            if(c.getCandidatoEleito() == CandidatoEleito.ELEITO){
                if(i > quantidadeVagas){
                    System.out.print(i + " - ");
                    if(c.getPartido().getFederacao() != null){
                        System.out.print("*");
                    }
                    System.out.print(c.getNomeUrna() + " (" + c.getPartido().getSiglaPartido() + ", " + c.getQuantidadeVotos() + ")" + "\n");
                }
            }
            i++;
        }
        System.out.println();
    }

    public static void processaPartidos(ArrayList<Partido>listPartidos){
        int i = 1;
        for (Partido p: listPartidos) {
            Partido valor = p;
            int quantidadeEleitos = valor.calculaEleitos();
            int quantidadeVotos = valor.getLegendaPartido() + valor.getVotosNominais();

            System.out.print(i + " - " + valor.getSiglaPartido() + ", " + quantidadeVotos);
            if(quantidadeVotos > 1){
                System.out.print(" votos (");
            }
            else{
                System.out.print(" voto (");
            }
            System.out.print(valor.getVotosNominais());

            if(valor.getVotosNominais() > 1){
                System.out.print(" nominais e ");
            }
            else{
                System.out.print(" nominal e ");
            }
            System.out.print(valor.getLegendaPartido() + " de legenda), " + quantidadeEleitos);
            if(quantidadeEleitos > 1){
                System.out.println(" candidatos eleitos");
            }
            else{
                System.out.println(" candidato eleito");
            }    
            i++;
        }
        System.out.println();
    }

    public static void processaPrimeiroUltimoColocados(ArrayList<Partido> listPartidos){
        ArrayList<Candidato> maisVotados = new ArrayList<>();
        HashMap<Integer, Candidato> menosVotados = new HashMap<>();

        for(Partido p: listPartidos){
            Candidato auxMenor = new Candidato(), auxMaior = new Candidato();
            Partido valor = p;
            int i = 0;
            
            if(p.getVotosNominais() > 0){
                for(Candidato c: valor.getCandidatos()){
                    if(c.getSituacao() == SituacaoCandidato.DEFERIDO){
                        if(i == 0){
                            auxMenor = c;
                            auxMaior = c;
                            i++;
                        }
                        else{
                            if(auxMaior.getQuantidadeVotos() < c.getQuantidadeVotos()){
                                auxMaior = c;
                            }
                            else if(auxMaior.getQuantidadeVotos() == c.getQuantidadeVotos()){
                                if(auxMaior.getNumero() < c.getNumero()){
                                    auxMaior = c;
                                }
                            }
                            if(auxMenor.getQuantidadeVotos() > c.getQuantidadeVotos()){
                                auxMenor = c;
                            }
                            else if(auxMenor.getQuantidadeVotos() == c.getQuantidadeVotos()){
                                if(auxMenor.getNumero() < c.getNumero()){
                                    auxMenor = c;
                                }
                            }
                            
                        }
                    }
                }
            
                maisVotados.add(auxMaior);
                menosVotados.put(valor.getNumeroPartido(), auxMenor);
            }
        }

        Collections.sort(maisVotados, new Candidato.CandidatoComparator());

        int i = 1;
        for(Candidato c: maisVotados){
            System.out.print(i + " - " + c.getPartido().getSiglaPartido() + ", " + c.getNomeUrna() + " (" + c.getNumero() + ", " + c.getQuantidadeVotos());

            if(c.getQuantidadeVotos() > 1){
                System.out.print(" votos) / ");
            }
            else{
                System.out.print(" voto) / ");
            }

            Candidato ultimoColocado = menosVotados.get(c.getPartido().getNumeroPartido());

            System.out.print(ultimoColocado.getNomeUrna() + " (" + ultimoColocado.getNumero() + ", " + ultimoColocado.getQuantidadeVotos());

            if(ultimoColocado.getQuantidadeVotos() > 1){
                System.out.print(" votos)\n");
            }
            else{
                System.out.print(" voto)\n");
            }

            i++;
        }

        System.out.println();
    }
    
    public static void processaFaixaEtaria(ArrayList<Candidato> listCandidatos, int quantidadeEleitos, LocalDate dataEleicao){
        int menorQue30 = 0, entre30e40 = 0, entre40e50 = 0, entre50e60 = 0, maiorQue60 = 0;

        for (Candidato candidato : listCandidatos) {
            if(candidato.getCandidatoEleito() == CandidatoEleito.ELEITO){
                LocalDate dataNascimento = candidato.getDataNascimento();
                Period periodo = Period.between(dataNascimento, dataEleicao);
                int idade = periodo.getYears();

                if (idade < 30) {
                    menorQue30++;
                } else if (idade >= 30 && idade < 40) {
                    entre30e40++;
                } else if (idade >= 40 && idade < 50) {
                    entre40e50++;
                } else if (idade >= 50 && idade < 60) {
                    entre50e60++;
                } else {
                    maiorQue60++;
                }
            }
        }

        System.out.println("Eleitos, por faixa etária (na data da eleição):");
        System.out.printf("      Idade < 30: %d (%.2f%%)\n", menorQue30, (((float)menorQue30 * 100)/(float)quantidadeEleitos));
        System.out.printf("30 <= Idade < 40: %d (%.2f%%)\n", entre30e40, (((float)entre30e40 * 100)/(float)quantidadeEleitos));
        System.out.printf("40 <= Idade < 50: %d (%.2f%%)\n", entre40e50, (((float)entre40e50 * 100)/(float)quantidadeEleitos));
        System.out.printf("50 <= Idade < 60: %d (%.2f%%)\n", entre50e60, (((float)entre50e60 * 100)/(float)quantidadeEleitos));
        System.out.printf("60 <= Idade     : %d (%.2f%%)\n", maiorQue60, (((float)maiorQue60 * 100)/(float)quantidadeEleitos));

        System.out.println();
    }

    public static void processaGenero(ArrayList<Candidato> listCandidatos, int quantidadeEleitos){
        int homem = 0, mulher = 0;

        for(Candidato candidato: listCandidatos){
            if(candidato.getCandidatoEleito() == CandidatoEleito.ELEITO){

                if (candidato.getGenero() == Genero.FEMININO) {
                    mulher++;
                } else{
                    homem++;
                }
            }
        }

        System.out.println("Eleitos, por gênero:");
        System.out.printf("Feminino: %d (%.2f%%)\n", mulher, (((float)mulher*100)/(float)quantidadeEleitos));
        System.out.printf("Masculino: %d (%.2f%%)\n", homem, (((float)homem*100)/(float)quantidadeEleitos));

        System.out.println();
    }

    public static void processaVotosTotais(ArrayList<Partido> listPartidos){
        int votosValidos = 0, votosNominais = 0, votosLegenda = 0;

        for(Partido p: listPartidos){
            votosLegenda += p.getLegendaPartido();
            votosNominais += p.getVotosNominais();
        }
        votosValidos = votosLegenda + votosNominais;

        System.out.println("Total de votos válidos:    " + votosValidos);
        System.out.printf("Total de votos nominais:   %d (%.2f%%)\n", votosNominais, ((float)votosNominais*100)/(float)votosValidos);
        System.out.printf("Total de votos de legenda: %d (%.2f%%)\n", votosLegenda, ((float)votosLegenda*100)/(float)votosValidos);

        System.out.println();
    }
}