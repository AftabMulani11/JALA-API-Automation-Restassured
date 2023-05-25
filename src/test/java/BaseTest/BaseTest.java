package BaseTest;

import org.junit.Test;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;
public class BaseTest {
    protected static Properties prop = new Properties();
    protected static Logger log = Logger.getLogger("devpinoyLogger");
    @Test
    public void setup() throws IOException {
        FileReader reader=new FileReader("src/test/java/resources/config.properties");
        prop.load(reader);
    }
}
