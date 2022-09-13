package com.example;
import java.lang.annotation.Target;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;
import org.junit.Test;
import org.junit.Before;
import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class TestGreeting {
    @Mock
    private Greeting greeting;

    @Before
    public void setUp() {
        when(greeting.call()).thenReturn("new greeting");
    }

    @Test
    public void testRunner() {
        assertEquals(1, 1);
    }
}
