package br.com.gerdau.reportUtils.screenshot;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import br.com.gerdau.reportUtils.screenshot.interfaces.IFunctionReport;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ReportScreenshot {

	public static IFunctionReport<byte[]> web(final WebDriver webdriver, final WebElement... webElement) {

		return new IFunctionReport<byte[]>() {
			private final String JAVA_SCRIPT_HIGHLITH = "arguments[%d].setAttribute('style', 'border: 3px solid red!important');arguments[%d].focus();arguments[%d].scrollIntoView(true);";
			private final String JAVA_SCRIPT_UNDO = "arguments[%d].setAttribute('style', '%2$s');";
			private JavascriptExecutor jse;

			@Override
			public byte[] apply(Object input) {
				String unHighlight = highlightElement(webdriver, webElement);
				byte[] screenshot = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.BYTES);
				unHighlightElement(webdriver, unHighlight, webElement);
				return screenshot;
			}

			private String highlightElement(WebDriver webdriver, WebElement... webElement) {
				jse = ((JavascriptExecutor) webdriver);
				String javaScript = "";
				String initialStyle = "";

				for (int i = 0; i < webElement.length; i++) {
					initialStyle = initialStyle
							+ String.format(JAVA_SCRIPT_UNDO, i, webElement[i].getAttribute("style"));
					javaScript = javaScript + String.format(JAVA_SCRIPT_HIGHLITH, i, i, i);
				}
				waitVisibilityOfElement(webdriver, webElement);
				jse.executeScript(javaScript, (Object[]) webElement);
				return initialStyle;

			}

			private void waitVisibilityOfElement(WebDriver webdriver, WebElement... elements) {
				WebDriverWait webDriverWait = new WebDriverWait(webdriver, 30);
				for (int i = 0; i < elements.length; i++) {
					webDriverWait.until(ExpectedConditions.visibilityOf(elements[i]));
				}
			}

			private void unHighlightElement(WebDriver webdriver, String string, WebElement... webElement) {
				jse = ((JavascriptExecutor) webdriver);
				jse.executeScript(string, (Object[]) webElement);
			}
		};
	}

	public static IFunctionReport<byte[]> webAlert(final WebDriver webdriver) {

		return new IFunctionReport<byte[]>() {

			public byte[] apply(Object input) {
				BufferedImage screenShot = null;
				System.setProperty("java.awt.headless", "false");
				int w = webdriver.manage().window().getSize().width;
				int h = webdriver.manage().window().getSize().height;
				Robot robot = null;
				try {
					robot = new Robot();
				} catch (AWTException e) {
					e.printStackTrace();
				}
				robot.mouseMove(w, h);
				robot.mouseMove(h, w);
				screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				try {
					ImageIO.write(screenShot, "png", baos);
					baos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.setProperty("java.awt.headless", "true");
				webdriver.switchTo().alert().accept();
				return baos.toByteArray();
			}
		};
	}

}
