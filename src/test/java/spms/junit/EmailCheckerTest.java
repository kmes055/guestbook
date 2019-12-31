package spms.junit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class EmailCheckerTest {
	static EmailChecker emailChecker;
	
	@BeforeClass
	public static void init() {
		emailChecker = new EmailChecker();
	}
	
	@Test
	public void validEmailTest() {
		Assertions.assertTrue(emailChecker.emailTest("sample@nhn.com"));
		
	}
	
	@Test
	public void invalidEmailTest() {
		Assertions.assertFalse(emailChecker.emailTest("show me the money!!@nhn.com"));
	}
}
