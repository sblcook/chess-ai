package Board;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class StandardBoardTest {

    private Board board = new StandardBoard();

    @Test
    public void createTiles() throws Exception {
        Tile[][] tileGrid = board.createTiles();
        for(int i = 0; i < 8; i++){
            Tile[] expected = new Tile[8];
            for (int j = 0; j < 8; j++){
                expected[j] = new Tile(i+1, j+1);
            }
            assertArrayEquals(expected, tileGrid[i]);
        }
    }

    @Test
    public void createBlackPieceList() throws Exception {
    }

    @Test
    public void createWhitePieceList() throws Exception {
    }

}