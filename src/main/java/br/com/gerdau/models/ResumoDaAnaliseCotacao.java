package br.com.gerdau.models;

import java.util.ArrayList;
import java.util.HashMap;

public class ResumoDaAnaliseCotacao
{
    private String codigoGrupo;
    private String aco;
    private String normaEspecifica;
    private String dataDeNorma;
    private String revisaoDeNormaEspecifica;
    private String forma;
    private String tipoProduto;
    private String acabamento;
    private String tratamentoTermico;
    private ArrayList<ElementosQuimicosCotacao> composicaoQuimica;
    private String processoFabricacao;
    private String bitola;
    private String comprimento;
    private String processoDeCliente;
    private String aplicacaoFinal;
    private String cliente;
    private String mercado;
    private String consumo;
    private String dataInicioDoProjeto;

    public ResumoDaAnaliseCotacao(HashMap<String, String> resumoAnaliseCotacao, ArrayList<ElementosQuimicosCotacao> listaComposicaoQuimica){
        codigoGrupo = resumoAnaliseCotacao.get("Código Grupo");
        aco = resumoAnaliseCotacao.get("Aço");
        normaEspecifica = resumoAnaliseCotacao.get("Norma específica");
        dataDeNorma = resumoAnaliseCotacao.get("Data de norma");
        revisaoDeNormaEspecifica = resumoAnaliseCotacao.get("Revisão de norma específica");
        forma = resumoAnaliseCotacao.get("Forma");
        tipoProduto = resumoAnaliseCotacao.get("Tipo de produto");
        acabamento = resumoAnaliseCotacao.get("Acabamento");
        tratamentoTermico = resumoAnaliseCotacao.get("Tratamento térmico");
        composicaoQuimica = listaComposicaoQuimica;
        processoFabricacao = resumoAnaliseCotacao.get("Processo de fabricação");
        bitola = resumoAnaliseCotacao.get("Bitola");
        comprimento = resumoAnaliseCotacao.get("Comprimento");
        processoDeCliente = resumoAnaliseCotacao.get("Processo de cliente");
        aplicacaoFinal = resumoAnaliseCotacao.get("Aplicação final");
        cliente = resumoAnaliseCotacao.get("Cliente");
        mercado = resumoAnaliseCotacao.get("Mercado");
        consumo = resumoAnaliseCotacao.get("Consumo");
        dataInicioDoProjeto = resumoAnaliseCotacao.get("Data de início do projeto");
    }

    public String getCodigoGrupo() {
        return codigoGrupo;
    }

    public String getAco() {
        return aco;
    }

    public String getNormaEspecifica() {
        return normaEspecifica;
    }

    public String getDataDeNorma() {
        return dataDeNorma;
    }

    public String getRevisaoDeNormaEspecifica() {
        return revisaoDeNormaEspecifica;
    }

    public String getForma() {
        return forma;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public String getAcabamento() {
        return acabamento;
    }

    public String getTratamentoTermico() {
        return tratamentoTermico;
    }

    public ArrayList<ElementosQuimicosCotacao> getComposicaoQuimica() {
        return composicaoQuimica;
    }

    public String getProcessoFabricacao() {
        return processoFabricacao;
    }

    public String getBitola() {
        return bitola;
    }

    public String getComprimento() {
        return comprimento;
    }

    public String getProcessoDeCliente() {
        return processoDeCliente;
    }

    public String getAplicacaoFinal() {
        return aplicacaoFinal;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCodigoGrupo(String codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public void setAco(String aco) {
        this.aco = aco;
    }

    public void setNormaEspecifica(String normaEspecifica) {
        this.normaEspecifica = normaEspecifica;
    }

    public void setDataDeNorma(String dataDeNorma) {
        this.dataDeNorma = dataDeNorma;
    }

    public void setRevisaoDeNormaEspecifica(String revisaoDeNormaEspecifica) {
        this.revisaoDeNormaEspecifica = revisaoDeNormaEspecifica;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public void setAcabamento(String acabamento) {
        this.acabamento = acabamento;
    }

    public void setTratamentoTermico(String tratamentoTermico) {
        this.tratamentoTermico = tratamentoTermico;
    }

    public void setComposicaoQuimica(ArrayList<ElementosQuimicosCotacao> composicaoQuimica) {
        this.composicaoQuimica = composicaoQuimica;
    }

    public void setProcessoFabricacao(String processoFabricacao) {
        this.processoFabricacao = processoFabricacao;
    }

    public void setBitola(String bitola) {
        this.bitola = bitola;
    }

    public void setComprimento(String comprimento) {
        this.comprimento = comprimento;
    }

    public void setProcessoDeCliente(String processoDeCliente) {
        this.processoDeCliente = processoDeCliente;
    }

    public void setAplicacaoFinal(String aplicacaoFinal) {
        this.aplicacaoFinal = aplicacaoFinal;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMercado() {
        return mercado;
    }

    public void setMercado(String mercado) {
        this.mercado = mercado;
    }

    public String getConsumo() {
        return consumo;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public String getDataInicioDoProjeto() {
        return dataInicioDoProjeto;
    }

    public void setDataInicioDoProjeto(String dataInicioDoProjeto) {
        this.dataInicioDoProjeto = dataInicioDoProjeto;
    }
}
