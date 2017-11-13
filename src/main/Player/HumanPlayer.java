
public class HumanPlayer extends Player{

    String name;

    Map<Result,Int> Performance;

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

    public Map<Result, Int> getPerformance() {
        return Performance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPerformance(Map<Result, Int> performance) {
        Performance = performance;
    }
}
