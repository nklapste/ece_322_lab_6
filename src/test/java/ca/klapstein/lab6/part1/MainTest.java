package ca.klapstein.lab6.part1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
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