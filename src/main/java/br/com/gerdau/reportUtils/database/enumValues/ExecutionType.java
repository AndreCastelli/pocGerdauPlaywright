package br.com.gerdau.reportUtils.database.enumValues;

public enum ExecutionType {

    DEV("dev"), REGRESSION("regression");

    String executionType;

    ExecutionType(String executionType){
        this.executionType = executionType;
    }

    public String get(){
        return this.executionType;
    }

}
