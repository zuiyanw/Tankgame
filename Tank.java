package com.hspedu.tankgame2;

public class Tank {
    private int x;//坦克x坐标
    private int y;//坦克y坐标
    private int direction;//坦克的方向
    private int speed = 2;
    private int type = 1;
    private boolean isLive = true;//坦克的存活状态

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void up() {
        if (y > 0) {//加上isLive表示被击中的话 也不能移动
            y -= speed;
        }
    }

    public void down() {
        if (y + 60 < 750) {
            y += speed;
        }
    }

    public void right() {
        if (x + 60 < 1000) {
            x += speed;
        }
    }

    public void left() {
        if (x > 0) {
            x -= speed;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {

        return x;
    }

    public void setX(int x) {

        this.x = x;
    }

    public int getDirection() {

        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getY() {

        return y;
    }

    public void setY(int y) {

        this.y = y;
    }
}
