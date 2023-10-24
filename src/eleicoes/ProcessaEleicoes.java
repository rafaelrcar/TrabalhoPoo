package eleicoes;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import enums.*;

public class ProcessaEleicoes{    
    public static int processaQuantidadeVagas(HashMap<Integer, Candidato> candidatos, NumberFormat nf){
        int count = 0;

        for (HashMap.Entry<Integer, Candidato> candidato : candidatos.entrySet()){
            Candidato c = candidato.getValue();
            
            if(c.getCandidatoEleito() == CandidatoEleito.ELEITO){
                count++;
            }
        }
        System.out.println("Número de vagas: " + nf.format(count) + "\n");

        return count;
    }

    public static void processaEleitos(ArrayList<Candidato>listCandidatos, NumberFormat nf){
        int i = 1;

        for(Candidato c: listCandidatos){
            if(c.getCandidatoEleito() == CandidatoEleito.ELEITO){
                
                System.out.print(i + " - ");
                if(c.getPartido().getFederacao() != null){
                    System.out.print("*");
                }
                System.out.println(c.getNomeUrna() + " (" + c.getPartido().getSiglaPartido() + ", " + nf.format(c.getQuantidadeVotos()) + " votos)" + "\n");
                
                i++;
            }
        }
        System.out.println();
    }

    public static void processaMaisVotados(ArrayList<Candidato>listCandidatos, int quantidadeVagas, NumberFormat nf){
        int i = 1;

        System.out.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");

        for(Candidato c: listCandidatos){
            
            System.out.print(i + " - ");
            if(c.getPartido().getFederacao() != null){
                System.out.print("*");
            }
            System.out.println(c.getNomeUrna() + " (" + c.getPartido().getSiglaPartido() + ", " + nf.format(c.getQuantidadeVotos()) + " votos)" + "\n");

            if(i == quantidadeVagas){
                break;
            }
            i++;
        }
        System.out.println();
    }

    public static void processaNaoEleitosMajoritaria(ArrayList<Candidato>listCandidatos, int quantidadeVagas, NumberFormat nf){
        int i = 1;

        System.out.print("Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:");
        System.out.println("(com sua posição no ranking de mais votados)");

        for(Candidato c: listCandidatos){
            if(c.getCandidatoEleito() == CandidatoEleito.NAO_ELEITO){
                
                System.out.println(i + " - ");
                if(c.getPartido().getFederacao() != null){
                    System.out.print("*");
                }
                System.out.println(c.getNomeUrna() + " (" + c.getPartido().getSiglaPartido() + ", " + nf.format(c.getQuantidadeVotos()) + " votos)" + "\n");
            }

            if(i == quantidadeVagas){
                break;
            }

            i++;
        }
        System.out.println();
    }
    
    public static void processaEleitosSistemaProporcional(ArrayList<Candidato> listCandidatos, int quantidadeVagas, NumberFormat nf){
        int i = 1;
        
        System.out.print("Eleitos, que se beneficiaram do sistema proporcional:");
        System.out.println("(com sua posição no ranking de mais votados)");

        for(Candidato c: listCandidatos){
            if(c.getCandidatoEleito() == CandidatoEleito.ELEITO){
                if(i > quantidadeVagas){
                    System.out.print(i + " - ");
                    if(c.getPartido().getFederacao() != null){
                        System.out.print("*");
                    }
                    System.out.println(c.getNomeUrna() + " (" + c.getPartido().getSiglaPartido() + ", " + nf.format(c.getQuantidadeVotos()) + " votos)" + "\n");
                }
            }
            i++;
        }
        System.out.println();
    }

    public static void processaPartidos(ArrayList<Partido>listPartidos, NumberFormat nf){
        int i = 1;

        System.out.println("Votação dos partidos e número de candidatos eleitos:");

        for (Partido p: listPartidos) {
            int quantidadeEleitos = p.calculaEleitos();
            int quantidadeVotos = p.getLegendaPartido() + p.getVotosNominais();

            System.out.println(i + " - " + p.getSiglaPartido() + " - " + p.getNumeroPartido() + ", " + nf.format(quantidadeVotos));
            if(quantidadeVotos > 1){
                System.out.print(" votos (");
            }
            else{
                System.out.print(" voto (");
            }
            System.out.print(nf.format(p.getVotosNominais()));

            if(p.getVotosNominais() > 1){
                System.out.print(" nominais e ");
            }
            else{
                System.out.print(" nominal e ");
            }
            System.out.print(nf.format(p.getLegendaPartido())  + " de legenda), " + quantidadeEleitos);
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

    public static void processaPrimeiroUltimoColocados(ArrayList<Partido> listPartidos, NumberFormat nf){
        ArrayList<Candidato> maisVotados = new ArrayList<>();
        HashMap<Integer, Candidato> menosVotados = new HashMap<>();

        System.out.println("Primeiro e último colocados de cada partido:");

        for(Partido p: listPartidos){
            Candidato auxMenor = new Candidato(), auxMaior = new Candidato();
            int i = 0;
            
            if(p.getVotosNominais() > 0){
                for(Candidato c: p.getCandidatos()){
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
                                if(auxMaior.getDataNascimento().isBefore(c.getDataNascimento())){
                                    auxMaior = c;
                                }
                            }
                            if(auxMenor.getQuantidadeVotos() > c.getQuantidadeVotos()){
                                auxMenor = c;
                            }
                            else if(auxMenor.getQuantidadeVotos() == c.getQuantidadeVotos()){
                                if(auxMenor.getDataNascimento().isBefore(c.getDataNascimento())){
                                    auxMenor = c;
                                }
                            }
                            
                        }
                    }
                }
            
                maisVotados.add(auxMaior);
                menosVotados.put(p.getNumeroPartido(), auxMenor);
            }
        }

        Collections.sort(maisVotados, new Candidato.CandidatoComparator());

        int i = 1;
        for(Candidato c: maisVotados){
            System.out.print(i + " - " + c.getPartido().getSiglaPartido() + " - " + c.getPartido().getNumeroPartido() + ", " + c.getNomeUrna() + " (" + c.getNumero() + ", " + nf.format(c.getQuantidadeVotos()));

            if(c.getQuantidadeVotos() > 1){
                System.out.print(" votos) / ");
            }
            else{
                System.out.print(" voto) / ");
            }

            Candidato ultimoColocado = menosVotados.get(c.getPartido().getNumeroPartido());

            System.out.print(ultimoColocado.getNomeUrna() + " (" + ultimoColocado.getNumero() + ", " + nf.format(ultimoColocado.getQuantidadeVotos()));

            if(ultimoColocado.getQuantidadeVotos() > 1){
                System.out.println(" votos)");
            }
            else{
                System.out.println(" voto)");
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
        System.out.printf("Feminino:  %d (%.2f%%)\n", mulher, (((float)mulher*100)/(float)quantidadeEleitos));
        System.out.printf("Masculino: %d (%.2f%%)\n", homem, (((float)homem*100)/(float)quantidadeEleitos));
        System.out.println();
    }

    public static void processaVotosTotais(ArrayList<Partido> listPartidos, NumberFormat nf){
        int votosValidos = 0, votosNominais = 0, votosLegenda = 0;

        for(Partido p: listPartidos){
            votosLegenda += p.getLegendaPartido();
            votosNominais += p.getVotosNominais();
        }
        votosValidos = votosLegenda + votosNominais;

        System.out.println("Total de votos válidos:    " + nf.format(votosValidos));

        System.out.print("Total de votos nominais:   " + nf.format(votosNominais));
        System.out.printf(" (%.2f%%)\n", ((float)votosNominais*100)/(float)votosValidos);
        
        System.out.print("Total de votos de legenda: " + nf.format(votosLegenda));
        System.out.printf(" (%.2f%%)\n", ((float)votosLegenda*100)/(float)votosValidos);
    }
}