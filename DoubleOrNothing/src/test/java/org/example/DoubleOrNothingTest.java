package org.example;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class DoubleOrNothingTest {

    @Test
    public void newUser10Points() {
        User user = new User();
        assertEquals(10, user.getPoints());
    }

    @Test
    public void doublePointsWin(){
        DoubleOrNothing doubleOrNothing = mock(DoubleOrNothing.class);
        when(doubleOrNothing.shouldDouble()).thenReturn(true);
        User user = new User();
        System.out.println(user.getPoints());
        doubleOrNothing.doublePoints();
        assertEquals(20, user.getPoints());
    }
}

