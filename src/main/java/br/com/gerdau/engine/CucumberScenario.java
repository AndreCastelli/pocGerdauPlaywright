package br.com.gerdau.engine;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CucumberScenario
{
    private int executionItemID;
    private String id;
    public String nomeCenario;
    private int numeroCenario;
    private ArrayList<String> steps;
    private String tags;
    private String featureName;
    private boolean isPassed;

    public CucumberScenario(String id, String nomeCenario, int numeroCenario){
        this.id = id;
        this.nomeCenario = nomeCenario;
        this.numeroCenario = numeroCenario;
        steps = new ArrayList<String>();
        isPassed = false;
    }

    public CucumberScenario(String id, String nomeCenario, int numeroCenario, String tags, String featureName){
        this.executionItemID = -1;
        this.id = id;
        this.nomeCenario = nomeCenario;
        this.numeroCenario = numeroCenario;
        this.tags = tags;
        this.featureName = featureName;
        steps = new ArrayList<String>();
        isPassed = false;
    }

    public CucumberScenario(int executionID, int executionItemID, String id, String nomeCenario, int numeroCenario){
        this.executionItemID = executionItemID;
        this.id = id;
        this.nomeCenario = nomeCenario;
        this.numeroCenario = numeroCenario;
        steps = new ArrayList<String>();
        isPassed = false;
    }

    public int getExecutionItemID() {
        return executionItemID;
    }

    public void setExecutionItemID(int executionItemID) {
        this.executionItemID = executionItemID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeCenario() {
        return removeAcentos(nomeCenario);
    }

    public void setNomeCenario(String nomeCenario) {
        this.nomeCenario = nomeCenario;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    public void addStep(String step){
        steps.add(step);
    }

    public int getNumeroCenario() {
        return numeroCenario;
    }

    public void setNumeroCenario(int numeroCenario) {
        this.numeroCenario = numeroCenario;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public static String removeAcentos(String input) {
        // Remove a acentuação usando Normalizer e uma expressão regular
        String decomposed = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(decomposed).replaceAll("");
    }
}
