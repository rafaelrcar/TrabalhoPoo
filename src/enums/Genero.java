package enums;

public enum Genero {
    MASCULINO(2),
    FEMININO(4);

    private int codigoTSE;

    private Genero(int codigo) {
        this.codigoTSE = codigo;
    }

    public int getCodigoTSE() {
        return codigoTSE;
    }

    public boolean codigoIgual(int codigo) {
        return codigo == this.codigoTSE;
    }

    public static Genero getGenero(int codigo) {
        if (codigo == MASCULINO.codigoTSE)
            return MASCULINO;
        else
            return FEMININO;
    }
}