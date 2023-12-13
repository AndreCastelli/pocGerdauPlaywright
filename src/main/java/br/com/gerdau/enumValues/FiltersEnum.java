package br.com.gerdau.enumValues;

public enum FiltersEnum
{

    APENAS_EM_ATRASO("APENAS EM ATRASO"),
    APENAS_EM_TRANSITO("APENAS EM TRANSITO"),
    ENTREGUES("ENTREGUES"),
    ATRASADOS("ATRASADOS");

    public String filter;
    FiltersEnum(String filterName) {
        filter = filterName;
    }

    public String getValue(){
        return filter;
    }
}
