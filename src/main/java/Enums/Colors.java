package java.Enums;

public enum Colors {
    BLACK("BLACK"),
    WHITE("WHITE");

    private final String color;

    Colors(String color){
        this.color = color;
    }

    public boolean equalsColor(String otherColor){
        return color.equals(otherColor);
    }

    public String toString(){
        return this.color;
    }
}
