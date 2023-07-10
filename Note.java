package com.hspedu.tankgame2;

/**
 * @Author 醉眼
 * @Date 2023/7/6 15:11
 * @Version 1.0 （版本号）
 */
public class Note {
    private int x;
    private int y;
    private int direct;
    public Note(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }
}

