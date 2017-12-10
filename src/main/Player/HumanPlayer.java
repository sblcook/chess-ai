package Player;


import Board.Tile;
import Enums.Result;

import java.util.Map;

public class HumanPlayer extends Player {

    String name;
    Map<Result,Integer> performance;

    public HumanPlayer(String name, Map<Result, Integer> performance) {
        super(name, performance);
    }

    public String getName() {
        return name;
    }

    public Map<Result, Integer> getPerformance() {
        return performance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPerformance(Map<Result, Integer> performance) {
        performance = performance;
    }
}