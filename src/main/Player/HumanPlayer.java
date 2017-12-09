package Player;


import Enums.Result;

import java.util.Map;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, Map<Result, Integer> performance){

        super(name, performance);
    }
}