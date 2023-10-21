package enums;

public enum SituacaoCandidato {
    DEFERIDO,
    INDEFERIDO;

    public static SituacaoCandidato getSituacaoCandidato(int codigo) {
        if (codigo == 2 || codigo == 16)
            return DEFERIDO;
        else
            return INDEFERIDO;
    }
}
