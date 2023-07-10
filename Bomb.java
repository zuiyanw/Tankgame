package com.hspedu.tankgame2;

/**
 * @Author 醉眼
 * @Date 2023/6/7 16:27
 * @Version 1.0 （版本号）
 */
public class Bomb {
    private int x;
    private int y;
    private boolean isLive = true;
    private int life = 9;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setY(int y) {

        this.y = y;
    }
    public void bombLife(){
        if (life < 0 ) {
            isLive =false;
        }
        life--;
    }
}
