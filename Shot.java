package com.hspedu.tankgame2;

/**
 * @Author 醉眼
 * @Date 2023/6/4 20:12
 * @Version 1.0 （版本号）
 */
public class Shot implements Runnable {

    private int x;
    private int y;
    private int dirct = 0;//子弹的方向
    private int speed = 4;//速度
    private boolean isLive = true;//子弹的状态

    public Shot(int x, int y, int dirct) {
        this.x = x;
        this.y = y;
        this.dirct = dirct;
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

    public int getDirct() {
        return dirct;
    }

    public void setDirct(int dirct) {
        this.dirct = dirct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public void run() {//射击行为 生产一个子弹就启动一个线程
        while (true) {
            //当子弹碰到边界需要销毁
            //当子弹碰到敌人坦克需要销毁 4.0更新
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {
                isLive = false;//记录子弹的状态
                break;
            }
            switch (dirct) {//根据方向改变x，y坐标
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
           // System.out.println("x= " + x + " y= " + y);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
