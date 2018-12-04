import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MainTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void main() {
        Main.main(new String[]{});
    }

    @Test
    public void mainNull() {
        Main.main(new String[]{null});
    }

    @Test
    public void mainArgs() {
        Main.main(new String[]{"foo", "bar"});
    }

}