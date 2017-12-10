package Enums;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ColorsTest {

    @Test
    public void colorsTestBlack() throws Exception {
        Enum black = Colors.BLACK;
        assertEquals(Colors.BLACK, black);
    }

    @Test
    public void colorsTestWhite(){
        Enum white = Colors.WHITE;
        assertEquals(Colors.WHITE, white);
    }



}