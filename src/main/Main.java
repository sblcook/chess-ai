import Enums.Result;
import Player.Player;
import Player.HumanPlayer;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args )
    {

        System.out.println("Hello World ");
        Map<Result, Integer> player1Performance = new HashMap<Result,Integer>();
        Map<Result, Integer> player2Performance = new HashMap<Result,Integer>();
        Player player1 = new HumanPlayer("Karl Morris",player1Performance);
        Player player2 = new HumanPlayer("Morris Destroyer",player2Performance);
        Game newGame = new Game(player1,player2);
    }
    public String testingMethod(){

        return "test string";
    }
}
