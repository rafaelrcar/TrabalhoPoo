package enums;

public enum Federacao{
    TEM_FEDERACAO,
    NAO_TEM_FEDERACAO;

    public static Federacao getFederacao(int nFederacao){
        if(nFederacao == -1){
            return NAO_TEM_FEDERACAO;
        }
        else{
            return TEM_FEDERACAO;
        }
    }
}