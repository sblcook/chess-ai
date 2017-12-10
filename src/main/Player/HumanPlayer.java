package Player;


import Board.Tile;
import Enums.Result;

import java.util.Map;

public class HumanPlayer extends Player {

    String name;

    Map<Result,Integer> Performance;

    @Override
    public ChessPieceTurn getTurn(Tile p){
        return null;
    }

    public HumanPlayer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<Result, Integer> getPerformance() {
        return Performance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPerformance(Map<Result, Integer> performance) {
        Performance = performance;
    }
}