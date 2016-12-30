package optionals;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class OptionalDemoTest {
    private Logger logger = Logger.getLogger(OptionalDemoTest.class.getName());
    private OptionalDemo demo = new OptionalDemo();

    @Before
    public void setUp() {
        demo.setStrings(Arrays.asList("five", "even", "length", "string", "values"));
    }

    @Test
    public void findFirstEven() throws Exception {
        Optional<String> first = demo.findFirst(s -> s.length() % 2 == 0);
        assertTrue(first.isPresent());
        assertEquals("five", first.isPresent() ? first.get() : "");
    }

    @Test
    public void findFirstEven_orElse() throws Exception {
        Optional<String> first = demo.findFirst(s -> s.length() % 2 == 0);
        assertTrue(first.isPresent());
        assertEquals("five", first.orElse("No even length strings"));
    }

    @Test(expected = NoSuchElementException.class)
    public void findFirstOdd_throwsException() throws Exception {
        Optional<String> first = demo.findFirst(s -> s.length() % 2 != 0);
        assertFalse(first.isPresent());
        first.get();
    }

    @Test
    public void findFirstOdd() throws Exception {
        Optional<String> first = demo.findFirst(s -> s.length() % 2 != 0);
        assertFalse(first.isPresent());
        assertEquals("", first.isPresent() ? first.get() : "");
    }

    @Test
    public void findFirstOdd_orElse() throws Exception {
        Optional<String> first = demo.findFirst(s -> s.length() % 2 != 0);
        assertFalse(first.isPresent());
        assertEquals("No odd length strings",
                first.orElse("No odd length strings"));
    }

    private String getDefault() {
        logger.info("inside getDefault()");
        return "No matching string found";
    }

    @Test
    public void findFirstEven_orElseWithMethodDefault() throws Exception {
        Optional<String> first = demo.findFirst(s -> s.length() % 2 == 0);
        assertEquals("five", first.orElse(getDefault()));
    }

    @Test
    public void findFirstEven_orElseGetWithMethodDefault() throws Exception {
        Optional<String> first = demo.findFirst(s -> s.length() % 2 == 0);
        assertEquals("five", first.orElseGet(this::getDefault));
    }

    @Test
    public void findFirstEven_orElseThrow() throws Exception {
        Optional<String> first = demo.findFirst(s -> s.length() % 2 == 0);
        assertEquals("five", first.orElseThrow(NoSuchElementException::new));
    }

    @Test
    public void findFirst_ifPresent() throws Exception {
        Optional<String> first = demo.findFirst(s -> s.length() % 2 == 0);
        first.ifPresent(val -> System.out.println("Found an even"));

        first = demo.findFirst(s -> s.length() % 2 != 0);
        first.ifPresent(val -> System.out.println("Found an odd"));
    }
}