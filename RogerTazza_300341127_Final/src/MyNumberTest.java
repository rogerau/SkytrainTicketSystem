import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyNumberTest {

	@Test
	void testDigitAt() {
		MyNumber obj1 = new MyNumber(12345678);

		// Test if the position argument gives the correct number
		assertEquals(8, obj1.digitAt(0));

	}

}
