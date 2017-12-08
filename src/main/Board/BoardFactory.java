package Board;

public class BoardFactory {

    public Board getBoard(String boardType){
        if(boardType == null){
            return null;
        }
        if(boardType.equalsIgnoreCase("StandardBoard")){
            return new StandardBoard();
        }
        return null;
    }
}
