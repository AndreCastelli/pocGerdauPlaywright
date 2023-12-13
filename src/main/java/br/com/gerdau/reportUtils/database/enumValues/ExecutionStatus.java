package br.com.gerdau.reportUtils.database.enumValues;

public enum ExecutionStatus {

    PASSED("passed"), IN_PROGRESS("in_progress"), FAILED("failed");

    String status;

    ExecutionStatus(String status){
        this.status = status;
    }

    public String get(){
        return this.status;
    }

}
