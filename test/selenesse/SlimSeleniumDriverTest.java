package selenesse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.thoughtworks.selenium.Selenium;

@RunWith(MockitoJUnitRunner.class)
public class SlimSeleniumDriverTest {
	
	@Mock
	private Selenium mockSelenium;
	
	/*
	 * Override the factory method for the Selenium instance and override the getBaseURL method.
	 */
	class DummySlimSeleniumDriver extends SlimSeleniumDriver {

		private String baseURL;

		public DummySlimSeleniumDriver(String host, int port, String browser,
				String baseURL) {
			super(host, port, browser, baseURL);
		}

		@Override
		protected Selenium createSelenium(String host, int port,
				String browser, String baseURL) {
			this.baseURL= baseURL;
			return mockSelenium;
		}

		
		
	}

	@Test
	@Ignore
	public void testRealConnection() throws MalformedURLException {
		String baseURL = "<a href=\"http://www.google.com\">http://www.google.com</a>";
		String browser = "*firefox";
		int port = 4444;
		String host = "localhost";
		SlimSeleniumDriver driver = new SlimSeleniumDriver(host, port, browser, baseURL);
		assertEquals("http://localhost", driver.getBaseURL());
	}
	
	@Test
	public void testConstructorNoAnchor() {
		String baseURL = "http://www.google.com";
		String browser = "*firefox";
		int port = 4444;
		String host = "localhost";
		DummySlimSeleniumDriver driver = new DummySlimSeleniumDriver(host, port, browser, baseURL);
		assertEquals(baseURL, driver.baseURL);
	}
	
	@Test
	public void testRemoveAnchor() {
		String baseURL = "<a href=\"http://www.google.com\">http://www.google.com</a>";
		String expectedURL = "http://www.google.com";
		String browser = "*firefox";
		int port = 4444;
		String host = "localhost";
		DummySlimSeleniumDriver driver = new DummySlimSeleniumDriver(host, port, browser, baseURL);
		assertEquals(expectedURL, driver.removeAnchor(baseURL));
	}

	@Test
	public void testConstructorAnchor() {
		String baseURL = "<a href=\"http://www.google.com\">http://www.google.com</a>";
		String expectedURL = "http://www.google.com";
		String browser = "*firefox";
		int port = 4444;
		String host = "localhost";
		DummySlimSeleniumDriver driver = new DummySlimSeleniumDriver(host, port, browser, baseURL);
		assertEquals(expectedURL, driver.baseURL);
	}
	
	@Test
	public void testConstructorAnchorNoAnchor() {
		String baseURL = "http://www.google.com";
		String expectedURL = "http://www.google.com";
		String browser = "*firefox";
		int port = 4444;
		String host = "localhost";
		DummySlimSeleniumDriver driver = new DummySlimSeleniumDriver(host, port, browser, baseURL);
		assertEquals(expectedURL, driver.baseURL);
	}
}
