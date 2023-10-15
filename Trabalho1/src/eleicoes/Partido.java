package eleicoes;

import java.util.ArrayList;
import java.util.List;

public class Partido {
    private int numero;
    private String sigla;
    private List<Candidato> candidatos = new ArrayList<>();
    private Federacao federacao;
    private int numeroVotos;
    
    public Partido(int numero, String sigla, Federacao federacao) {
        this.numero = numero;
        this.sigla = sigla;
        this.federacao = federacao;
    }

    
}
