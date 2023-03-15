import org.example.Logger;
import org.junit.Test;

import static org.example.Main.parseSettings;
import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test
    public void parseSettingsTest() {
        int actual = Integer.parseInt(String.valueOf(parseSettings("port")));
        assertEquals(5511, actual);
        Logger.log("Settings test - ok!");
    }
}
