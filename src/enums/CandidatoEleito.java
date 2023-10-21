package enums;

public enum CandidatoEleito {
    ELEITO,
    NAO_ELEITO;

    public static CandidatoEleito getCandidatoEleito(int codigo) {
        if (codigo == 2 || codigo == 3)
            return ELEITO;
        else
            return NAO_ELEITO;
    }
}
