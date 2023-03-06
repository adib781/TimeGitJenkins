package time;

import static org.junit.Assert.assertThrows;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	@Test
	void testGetTotalSecondsGood() {
		int seconds = Time.getTotalSeconds("05:05:05");
		assertTrue("The seconds were not calculated properly", seconds==18305);
		}

	@Test
	void testGetTotalSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class, () -> {Time.getTotalSeconds("10:00");});
	}
	
	@Test
	void testGetTotalSecondsBoundary() {
		int seconds = Time.getTotalSeconds("05:59:59");
		assertTrue("The boundary was not calculated properly", seconds==21599);
	}

	@Test
	void testGetTotalMinutesGood() {
		int min = Time.getTotalMinutes("05:05:00");
		assertTrue("The minutes were not calculated properly", min==5);
	}
	
	@Test
	void testGetTotalMinutesBad() {
		assertThrows(NumberFormatException.class, () -> {Time.getTotalMinutes("59:59:59:59:59");});
	}
	
	@Test
	void testGetTotalMinutesBoundary() {
		int min = Time.getTotalMinutes("05:59:00");
		assertTrue("The boundary was not calculated properly", min==59);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"05:00:00", "05:15:00", "05:59:59"})
	void testGetTotalHours(String candidate) {
		int hours = Time.getTotalHours(candidate);
		assertTrue("The hours were not calculated properly", hours==5);
	}
	
	@Test
	void testGetMilliseconds() {
		int milliseconds = Time.getMilliseconds("12:05:05:005");
		assertTrue("The milliseconds were not calculated properly", milliseconds == 5);
	}
	
	@Test
	void testTotalMilliseconds() {
		int milliseconds = Time.getTotalMilliseconds("05:05:05:005");
		assertTrue("The milliseconds weren't calculated properly", milliseconds == 18305005);
	}

}
