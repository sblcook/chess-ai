package Board;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BoardFactoryTest {

    private BoardFactory boardFactory = new BoardFactory();
    private Board board = new StandardBoard();

    @Test
    public void getBoard() throws Exception {
        assertEquals(board, boardFactory.getBoard("StandardBoard"));
    }

}