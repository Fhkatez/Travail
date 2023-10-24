package model;

public class Position {
    private int x;
    private int y;

    public Position(int x,int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Position p){
        return (p.getX() == this.x && p.getY() == this.y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
