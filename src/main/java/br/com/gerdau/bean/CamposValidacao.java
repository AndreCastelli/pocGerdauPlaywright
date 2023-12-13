package br.com.gerdau.bean;

import br.com.gerdau.enumValues.UsuariosPadroes;
import br.com.gerdau.models.ResumoDaAnaliseCotacao;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CamposValidacao {
    private String envTarget;
    private String downloadFilePath;
    private HashMap<String, String> infosEscolhidasParaNovaCotacao;
    private UsuariosPadroes usuarioEscolhido;
    private ResumoDaAnaliseCotacao resumoDaAnaliseCotacao;
    private List<String> listaMateriaisCarteira = new ArrayList<>();
    private List<String> listaPartNumberFiltrada = new ArrayList<>();

    public String getExcelPathGerenciadorUsarios() {
        return excelPathGerenciadorUsarios;
    }

    public void setExcelPathGerenciadorUsarios(String excelPathGerenciadorUsarios) {
        this.excelPathGerenciadorUsarios = excelPathGerenciadorUsarios;
    }

    private String excelPathGerenciadorUsarios;

    public CamposValidacao() {
    }


    public String getDownloadFilePath() {
        return downloadFilePath;
    }

    public void setDownloadFilePath(String downloadFilePath) {
        this.downloadFilePath = downloadFilePath;
    }

    public HashMap<String, String> getInfosEscolhidasParaNovaCotacao() {
        return infosEscolhidasParaNovaCotacao;
    }

    public void setInfosEscolhidasParaNovaCotacao(HashMap<String, String> infosEscolhidasParaNovaCotacao) {
        this.infosEscolhidasParaNovaCotacao = infosEscolhidasParaNovaCotacao;
    }

    public void addInfosEscolhidasParaNovaCotacao(String chave, String valor) {
        HashMap<String, String> infosCotacao = getInfosEscolhidasParaNovaCotacao();
        if(getInfosEscolhidasParaNovaCotacao() == null){
            infosCotacao = new HashMap<String, String>();
        }

        infosCotacao.put(chave, valor);
        setInfosEscolhidasParaNovaCotacao(infosCotacao);
    }

    public UsuariosPadroes getUsuarioEscolhido() {
        return usuarioEscolhido;
    }

    public void setUsuarioEscolhido(UsuariosPadroes usuarioEscolhido) {
        this.usuarioEscolhido = usuarioEscolhido;
    }

    public ResumoDaAnaliseCotacao getResumoDaAnaliseCotacao() {
        return resumoDaAnaliseCotacao;
    }

    public void setResumoDaAnaliseCotacao(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao) {
        this.resumoDaAnaliseCotacao = resumoDaAnaliseCotacao;
    }

    public String getEnvTarget() {
        return envTarget;
    }

    public void setEnvTarget(String envTarget) {
        this.envTarget = envTarget;
    }

    public List<String> getListaMateriaisCarteira() {
        return listaMateriaisCarteira;
    }

    public void setListaMateriaisCarteira(List<String> listaMateriaisCarteira) {
        this.listaMateriaisCarteira = listaMateriaisCarteira;
    }

    public List<String> getListaPartNumberFiltrada() {
        return listaPartNumberFiltrada;
    }

    public void setListaPartNumberFiltrada(List<String> listaPartNumberFiltrada) {
        this.listaPartNumberFiltrada = listaPartNumberFiltrada;
    }

    //-----------------------------------------------------------------------------------------------------------------
    //MÉTODOS/PROPRIEDADES CRIADAS PARA TESTES DE BACK-END

    private String ambiente;
    private Response lastResponse;
    private Response listAllResponse;
    private Response loginResponse;
    private Response listagemProfileResponse;

    private Response validateResponse;

    private Response listagemCarteiraMIEmAbertoResponse;

    private Response listagemCarteiraMIEmEstoqueResponse;

    private Response listagemCarteiraFaturadoResponse;

    private Response listagemFinancasVerLimitesResponse;

    private Response consultasPorClienteResponse;

    public Response getConsultasPorClienteResponse() {
        return consultasPorClienteResponse;
    }

    public void setConsultasPorClienteResponse(Response consultasPorClienteResponse) {
        this.consultasPorClienteResponse = consultasPorClienteResponse;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }


    public Response getLastResponse() {
        return lastResponse;
    }

    public void setLastResponse(Response response) {
        this.lastResponse = response;
    }


    public Response getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(Response loginResponse) {
        this.loginResponse = loginResponse;
    }

    public Response getListAllResponse() {
        return listAllResponse;
    }

    public void setListAllResponse(Response listAllResponse) {
        this.listAllResponse = listAllResponse;
    }

    public Response getValidateResponse() {
        return validateResponse;
    }

    public void setValidateResponse(Response validateResponse) {
        this.validateResponse = validateResponse;
    }

    public Response getListagemProfileResponse() {
        return listagemProfileResponse;
    }

    public void setListagemProfileResponse(Response listagemProfileResponse) {
        this.listagemProfileResponse = listagemProfileResponse;
    }

    public Response getListagemCarteiraMIEmAbertoResponse() {
        return listagemCarteiraMIEmAbertoResponse;
    }

    public void setListagemCarteiraMIEmAbertoResponse(Response listagemCarteiraMIEmAbertoResponse) {
        this.listagemCarteiraMIEmAbertoResponse = listagemCarteiraMIEmAbertoResponse;
    }

    public Response getListagemCarteiraMIEmEstoqueResponse() {
        return listagemCarteiraMIEmEstoqueResponse;
    }

    public void setListagemCarteiraMIEmEstoqueResponse(Response listagemCarteiraMIEmEstoqueResponse) {
        this.listagemCarteiraMIEmEstoqueResponse = listagemCarteiraMIEmEstoqueResponse;
    }

    public Response getListagemCarteiraFaturadoResponse() {
        return listagemCarteiraFaturadoResponse;
    }

    public void setListagemCarteiraFaturadoResponse(Response listagemCarteiraFaturadoResponse) {
        this.listagemCarteiraFaturadoResponse = listagemCarteiraFaturadoResponse;
    }


    public Response getListagemFinancasVerLimitesResponse() {
        return listagemFinancasVerLimitesResponse;
    }

    public void setListagemFinancasVerLimitesResponse(Response listagemFinancasVerLimitesResponse) {
        this.listagemFinancasVerLimitesResponse = listagemFinancasVerLimitesResponse;
    }


}
