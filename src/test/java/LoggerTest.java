import org.example.Logger;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LoggerTest {
    String message = "Test Logger message - ok!";

    @Test
    public void log() {
        assertTrue(Logger.log(message));
    }

}
