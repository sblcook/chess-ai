package Player;

import Enums.Result;

import java.util.Map;

public abstract class Player {

    private String name;
    private Map<Result, Integer> Performance;

    public Player(String name, Map<Result, Integer> performance) {
        this.name = name;
        Performance = performance;
    }

    public String getName() {
        return name;
    }


    public Map<Result, Integer> getPerformance() {
        return Performance;
    }

    public void setPerformance(Map<Result, Integer> performance) {
        Performance = performance;
    }
}