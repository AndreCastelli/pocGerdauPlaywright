package br.com.gerdau.steps;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
		// Visualizar os erros ao executar
		plugin = { "pretty",
				"json:coverage/reports.json",
				"junit:coverage/reports.xml",
				"html:coverage/reports.html" },
		monochrome = true,
		dryRun = false,
		snippets = CucumberOptions.SnippetType.CAMELCASE,
		features = {"classpath:features" },
		glue = { "br.com.gerdau.steps" },
		tags = "@SC-8661")

public class RunnerTest extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
