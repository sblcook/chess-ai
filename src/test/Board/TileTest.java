package Board;

import org.junit.Test;

import static org.junit.Assert.*;

public class TileTest {

    private Tile tile = new Tile(5, 5, null);

    @Test
    public void getColumn() throws Exception {
        assertEquals(5, tile.getColumn());
    }

    @Test
    public void getRow() throws Exception {
        assertEquals(5, tile.getRow());
    }

}