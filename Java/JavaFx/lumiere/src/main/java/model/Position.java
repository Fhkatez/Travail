package model;

public class Position {
    private boolean allume;

    private int x;
    private int y;

    public int[] getPosition(){
        return new int[]{x,y};
    }

    public Position(int x,int y){
        this.x = x;
        this.y = y;
        this.allume = false;
    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setAllume(boolean allume) {
        this.allume = allume;
    }

    public boolean isAllume() {
        return allume;
    }
}
