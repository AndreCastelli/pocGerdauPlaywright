package br.com.gerdau.enumValues;

public enum ExcelColumns
{
    
    NUMERO_REDESPACHO("Numero Transporte Redespacho"),
    NUMERO_TRANSPORTE("Numero Transporte"),
    TRANSPORTADORA("Transportadora"),
    TIPO_VEICULO("Tipo Veiculo"),
    PLACA("Placa"),
    ORIGEM("Origem"),
    DATA_SAIDA("Data Saida"),
    TIPO_OPERACAO("Tipo Operacao"),

    //dados entregas
    NUMERO_NFCe("Numero Nota Fiscal Exibicao"),
    STATUS("Entrega Status Label"),
    CLIENTE("Cliente"),
    OPERADOR("Nome Operador Logistico"),
    CIDADE("Municipio Cliente"),
    UF("UF Cliente"),
    DATA_RECEBIDA("Dt Chegada Operador"),
    DATA_PLANEJADA("Dt Agendamento Cliente"),
    PERMANENCIA("Numero Dias Em Estoque"),

    //Somente Analista
    PREVISAO_CHEGADA("Data Previsao Chegada"),
    DIAS_EM_TRANSITO("Dias Em Transito"),
    NOME_OPERADOR("Operador Nome");

    public String column;
    ExcelColumns(String columnName) {
        column = columnName;
    }

    public String getValue(){
        return column;
    }
}
