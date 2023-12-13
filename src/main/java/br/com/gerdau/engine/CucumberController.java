package br.com.gerdau.engine;

import br.com.gerdau.utils.ValueUtils;

import java.util.ArrayList;
import java.util.Date;

public class CucumberController
{
    public int executionID;
    public CucumberScenario cenario;
    private int numeroCenarioCorrente;
    private String currentExecutionDate;
    private ArrayList<CucumberScenario> listaCenariosExecutados;

    public void setExecutionID(int executionID) {
        this.executionID = executionID;
    }

    public int getExecutionID() {
        return executionID;
    }

    public CucumberScenario getCenario() {
        return cenario;
    }

    public void setCenario(CucumberScenario cenario) {
        this.cenario = cenario;
    }

    public int getNumeroCenarioCorrente() {
        return numeroCenarioCorrente;
    }

    public void setNumeroCenarioCorrente(int numeroCenarioCorrente) {
        this.numeroCenarioCorrente = numeroCenarioCorrente;
    }

    public ArrayList<CucumberScenario> getListaCenariosExecutados() {
        return listaCenariosExecutados;
    }

    public void setListaCenariosExecutados(ArrayList<CucumberScenario> listaCenariosExecutados) {
        this.listaCenariosExecutados = listaCenariosExecutados;
    }

    public void addCenarioExecutado(CucumberScenario cenario){
        if(listaCenariosExecutados == null)
            listaCenariosExecutados = new ArrayList<CucumberScenario>();

        listaCenariosExecutados.add(cenario);
    }

    public void setCurrentExecutionDate(String currentExecutionDate) {
        this.currentExecutionDate = currentExecutionDate;
    }
    public void setCurrentExecutionDate() {
        this.currentExecutionDate = ValueUtils.formatDate("dd-MM-yyyy_HH-mm-ss-SSSS", new Date());
    }

    public String getCurrentExecutionDate() {
        return currentExecutionDate;
    }
}
