package br.com.gerdau.models;

public class ElementosQuimicosCotacao
{
    private String siglaElemento;
    private String minComposicao;
    private String maxComposicao;
    private String unidadeComposicao;

    public ElementosQuimicosCotacao(String siglaElemento, String minComposicao, String maxComposicao, String unidadeComposicao){
        this.siglaElemento = siglaElemento;
        this.minComposicao = minComposicao;
        this.maxComposicao = maxComposicao;
        this.unidadeComposicao = unidadeComposicao;
    }

    public String getSiglaElemento() {
        return siglaElemento;
    }

    public String getMinComposicao() {
        return minComposicao;
    }

    public String getMaxComposicao() {
        return maxComposicao;
    }

    public String getUnidadeComposicao() {
        return unidadeComposicao;
    }
}
