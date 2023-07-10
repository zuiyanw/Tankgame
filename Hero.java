package com.hspedu.tankgame2;

import java.util.Vector;

/**
 * @Author 醉眼
 * @Date 2023/6/1 21:34
 * @Version 1.0 （版本号）
 * 自己的坦克
 */
@SuppressWarnings({"all"})
public class Hero extends Tank {
    private Shot shot;//定义一个Shot对象 表示一个射击行为(线程)
    Vector<Shot> shotsVector = new Vector<>();

    //如果是发射多颗子弹的话 需要创建一个集合来储存子弹 因为一开始只有一个shot
    // 再次按下j之后,上个shot线程结束了,但是shot对象并没有结束,只是变成垃圾了
    public Hero(int x, int y) {
        super(x, y);
    }

    public Shot getShot() {
        return shot;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
    }
//    public boolean isTouch() {
//        switch (this.getDirection()) {//当前坦克的方向
//            case 0:
//                for (int i = 0; i < EnemyTank.enemyTanks.size(); i++) {
//                    EnemyTank enemyTank = EnemyTank.enemyTanks.get(i);
//                        //当敌方坦克的方向是上/下
//                        //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+40)
//                        //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+60)
//                        //当前tank左上角 坐标(this.getX(),this.getY())
//                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
//                            if (this.getX() >= enemyTank.getX()
//                                    && this.getX() <= enemyTank.getX() + 40
//                                    && this.getY() >= enemyTank.getY()
//                                    && this.getY() <= enemyTank.getY() + 60) {
//                                return false;
//                            }
//                            //当前tank右上角 坐标(this.getX()+40,this.getY())
//                            if (this.getX() + 40 >= enemyTank.getX()
//                                    && this.getX() + 40 <= enemyTank.getX() + 40
//                                    && this.getY() >= enemyTank.getY()
//                                    && this.getY() <= enemyTank.getY() + 60) {
//                                return false;
//                            }
//                        }
//                        //当敌方坦克的方向是左/右
//                        //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+60)
//                        //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+40)
//                        //当前tank左上角 坐标(this.getX(),this.getY())
//                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
//                            if (this.getX() + 40 >= enemyTank.getX()
//                                    && this.getX() <= enemyTank.getX() + 60
//                                    && this.getY() >= enemyTank.getY()
//                                    && this.getY() <= enemyTank.getY() + 40) {
//                                return false;
//                            }
//                            //当前tank右上角 坐标(this.getX()+40,this.getY())
//                            if (this.getX() + 40 >= enemyTank.getX()
//                                    && this.getX() + 40 <= enemyTank.getX() + 60
//                                    && this.getY() >= enemyTank.getY()
//                                    && this.getY() <= enemyTank.getY() + 40) {
//                                return false;
//                            }
//                        }
//                }
//                break;
//            case 1:
//                for (int i = 0; i < EnemyTank.enemyTanks.size(); i++) {
//                    EnemyTank enemyTank =  EnemyTank.enemyTanks.get(i);
//                        //当敌方坦克的方向是上/下
//                        //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+40)
//                        //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+60)
//                        //当前tank右上角 坐标(this.getX()+60,this.getY())
//                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
//                            if (this.getX() + 60 >= enemyTank.getX()
//                                    && this.getX() + 60 <= enemyTank.getX() + 40
//                                    && this.getY() >= enemyTank.getY()
//                                    && this.getY() <= enemyTank.getY() + 60) {
//                                return false;
//                            }
//                            //当前tank右下角 坐标(this.getX()+60,this.getY()+40)
//                            if (this.getX() + 60 >= enemyTank.getX()
//                                    && this.getX() + 60 <= enemyTank.getX() + 40
//                                    && this.getY() + 40 >= enemyTank.getY()
//                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
//                                return false;
//                            }
//                        }
//                        //当敌方坦克的方向是左/右
//                        //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+60)
//                        //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+40)
//                        //当前tank右上角 坐标(this.getX()+60,this.getY())
//                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
//                            if (this.getX() + 60 >= enemyTank.getX()
//                                    && this.getX() + 60 <= enemyTank.getX() + 60
//                                    && this.getY() >= enemyTank.getY()
//                                    && this.getY() <= enemyTank.getY() + 40) {
//                                return false;
//                            }
//                            //当前tank右下角 坐标(this.getX()+60,this.getY()+40)
//                            if (this.getX() + 60 >= enemyTank.getX()
//                                    && this.getX() + 60 <= enemyTank.getX() + 60
//                                    && this.getY() + 40 >= enemyTank.getY()
//                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
//                                return false;
//                            }
//                        }
//                }
//                break;
//            case 2:
//                for (int i = 0; i < EnemyTank.enemyTanks.size(); i++) {
//                    EnemyTank enemyTank = EnemyTank.enemyTanks.get(i);
//                        //当敌方坦克的方向是上/下
//                        //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+40)
//                        //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+60)
//                        //当前tank左下角 坐标(this.getX(),this.getY()+60)
//                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
//                            if (this.getX() + 60 >= enemyTank.getX()
//                                    && this.getX() + 60 <= enemyTank.getX() + 40
//                                    && this.getY() >= enemyTank.getY()
//                                    && this.getY() <= enemyTank.getY() + 60) {
//                                return false;
//                            }
//                            //当前tank右下角 坐标(this.getX()+40,this.getY()+60)
//                            if (this.getX() + 40 >= enemyTank.getX()
//                                    && this.getX() + 40 <= enemyTank.getX() + 40
//                                    && this.getY() + 60 >= enemyTank.getY()
//                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
//                                return false;
//                            }
//                        }
//                        //当敌方坦克的方向是左/右
//                        //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+60)
//                        //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+40)
//                        //当前tank左下角 坐标(this.getX(),this.getY()+60)
//                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
//                            if (this.getX() + 60 >= enemyTank.getX()
//                                    && this.getX() + 60 <= enemyTank.getX() + 60
//                                    && this.getY() + 60 >= enemyTank.getY()
//                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
//                                return false;
//                            }
//                            //当前tank右下角 坐标(this.getX()+40,this.getY()+60)
//                            if (this.getX() + 40 >= enemyTank.getX()
//                                    && this.getX() + 40 <= enemyTank.getX() + 60
//                                    && this.getY() + 60 >= enemyTank.getY()
//                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
//                                return false;
//                            }
//                        }
//                }
//                break;
//            case 3:
//                for (int i = 0; i < EnemyTank.enemyTanks.size(); i++) {
//                    EnemyTank enemyTank = EnemyTank.enemyTanks.get(i);
//                        //当敌方坦克的方向是上/下
//                        //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+40)
//                        //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+60)
//                        //当前tank左上角 坐标(this.getX(),this.getY())
//                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
//                            if (this.getX() >= enemyTank.getX()
//                                    && this.getX() <= enemyTank.getX() + 40
//                                    && this.getY() >= enemyTank.getY()
//                                    && this.getY() <= enemyTank.getY() + 60) {
//                                return false;
//                            }
//                            //当前tank左下角 坐标(this.getX(),this.getY()+40)
//                            if (this.getX() >= enemyTank.getX()
//                                    && this.getX() <= enemyTank.getX() + 40
//                                    && this.getY() + 40 >= enemyTank.getY()
//                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
//                                return false;
//                            }
//                        }
//                        //当敌方坦克的方向是左/右
//                        //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+60)
//                        //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+40)
//                        //当前tank左上角 坐标(this.getX(),this.getY())
//                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
//                            if (this.getX() >= enemyTank.getX()
//                                    && this.getX() <= enemyTank.getX() + 60
//                                    && this.getY() >= enemyTank.getY()
//                                    && this.getY() <= enemyTank.getY() + 40) {
//                                return false;
//                            }
//                            //当前tank左下角 坐标(this.getX(),this.getY()+40)
//                            if (this.getX() >= enemyTank.getX()
//                                    && this.getX() <= enemyTank.getX() + 60
//                                    && this.getY() + 40 >= enemyTank.getY()
//                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
//                                return false;
//                            }
//                        }
//                }
//                break;
//        }
//        return true;
//    }
    public void shot() {//射击方法 创建Shot对象 根据坦克坐标
        if (shotsVector.size() == 5) {
            return;
        }
        switch (getDirection()) {//得到Hero对象的方向
            case 0:
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1:
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }
        shotsVector.add(shot);//每发射一颗存储一颗
        Thread thread = new Thread(shot);//启动Shot线程
        thread.start();
    }
}
