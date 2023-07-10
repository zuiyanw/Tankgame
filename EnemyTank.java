package com.hspedu.tankgame2;

import java.util.Vector;

/**
 * @Author 醉眼
 * @Date 2023/6/1 21:19
 * @Version 1.0 （版本号）
 */
@SuppressWarnings({"all"})
public class EnemyTank extends Tank implements Runnable {
    Vector<Shot> vector = new Vector<>();//这里可以作成private属性
    public static Vector<EnemyTank> enemyTanks = new Vector<>();//为了比较坦克之间不重叠 遍历

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        EnemyTank.enemyTanks = enemyTanks;
    }


    public boolean isTouch() {
        switch (this.getDirection()) {//当前坦克的方向
            case 0:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //当敌方坦克的方向是上/下
                        //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+40)
                        //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+60)
                        //当前tank左上角 坐标(this.getX(),this.getY())
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return false;
                            }
                            //当前tank右上角 坐标(this.getX()+40,this.getY())
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return false;
                            }
                        }
                        //当敌方坦克的方向是左/右
                        //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+60)
                        //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+40)
                        //当前tank左上角 坐标(this.getX(),this.getY())
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return false;
                            }
                            //当前tank右上角 坐标(this.getX()+40,this.getY())
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return false;
                            }
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //当敌方坦克的方向是上/下
                        //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+40)
                        //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+60)
                        //当前tank右上角 坐标(this.getX()+60,this.getY())
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return false;
                            }
                            //当前tank右下角 坐标(this.getX()+60,this.getY()+40)
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return false;
                            }
                        }
                        //当敌方坦克的方向是左/右
                        //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+60)
                        //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+40)
                        //当前tank右上角 坐标(this.getX()+60,this.getY())
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return false;
                            }
                            //当前tank右下角 坐标(this.getX()+60,this.getY()+40)
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return false;
                            }
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //当敌方坦克的方向是上/下
                        //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+40)
                        //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+60)
                        //当前tank左下角 坐标(this.getX(),this.getY()+60)
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return false;
                            }
                            //当前tank右下角 坐标(this.getX()+40,this.getY()+60)
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return false;
                            }
                        }
                        //当敌方坦克的方向是左/右
                        //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+60)
                        //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+40)
                        //当前tank左下角 坐标(this.getX(),this.getY()+60)
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return false;
                            }
                            //当前tank右下角 坐标(this.getX()+40,this.getY()+60)
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return false;
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //当敌方坦克的方向是上/下
                        //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+40)
                        //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+60)
                        //当前tank左上角 坐标(this.getX(),this.getY())
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return false;
                            }
                            //当前tank左下角 坐标(this.getX(),this.getY()+40)
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return false;
                            }
                        }
                        //当敌方坦克的方向是左/右
                        //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+60)
                        //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+40)
                        //当前tank左上角 坐标(this.getX(),this.getY())
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return false;
                            }
                            //当前tank左下角 坐标(this.getX(),this.getY()+40)
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return false;
                            }
                        }
                    }
                }
                break;
        }
        return true;
    }

//    public boolean isTouchHero() {
//        switch (this.getDirection()) {
//            case 0:
//                if (MyPanel.myPanel.getHero().getDirection() == 0 || MyPanel.myPanel.getHero().getDirection() == 2) {
//                    if (this.getX() >= MyPanel.myPanel.getHero().getX()
//                            && this.getX() <= MyPanel.myPanel.getHero().getX() + 40
//                            && this.getY() >= MyPanel.myPanel.getHero().getY()
//                            && this.getY() <= MyPanel.myPanel.getHero().getY() + 60) {
//                        return false;
//                    }
//                    //当前tank右上角 坐标(this.getX()+40,this.getY())
//                    if (this.getX() + 40 >= MyPanel.myPanel.getHero().getX()
//                            && this.getX() + 40 <= MyPanel.myPanel.getHero().getX() + 40
//                            && this.getY() >= MyPanel.myPanel.getHero().getY()
//                            && this.getY() <= MyPanel.myPanel.getHero().getY() + 60) {
//                        return false;
//                    }
//                }
//                //当敌方坦克的方向是左/右
//                //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+60)
//                //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+40)
//                //当前tank右上角 坐标(this.getX()+60,this.getY())
//                if (MyPanel.myPanel.getHero().getDirection() == 1 ||MyPanel.myPanel.getHero().getDirection() == 3) {
//                    if (this.getX() + 60 >= MyPanel.myPanel.getHero().getX()
//                            && this.getX() + 60 <= MyPanel.myPanel.getHero().getX() + 60
//                            && this.getY() >= MyPanel.myPanel.getHero().getY()
//                            && this.getY() <= MyPanel.myPanel.getHero().getY() + 40) {
//                        return false;
//                    }
//                    //当前tank右下角 坐标(this.getX()+60,this.getY()+40)
//                    if (this.getX() + 60 >= MyPanel.myPanel.getHero().getX()
//                            && this.getX() + 60 <= MyPanel.myPanel.getHero().getX() + 60
//                            && this.getY() + 40 >= MyPanel.myPanel.getHero().getY()
//                            && this.getY() + 40 <= MyPanel.myPanel.getHero().getY() + 40) {
//                        return false;
//                    }
//                }
//                break;
//            case 1:
//                //当敌方坦克的方向是上/下
//                //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+40)
//                //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+60)
//                //当前tank右上角 坐标(this.getX()+60,this.getY())
//                if (MyPanel.myPanel.getHero().getDirection() == 0 || MyPanel.myPanel.getHero().getDirection() == 2) {
//                    if (this.getX() + 60 >= MyPanel.myPanel.getHero().getX()
//                            && this.getX() + 60 <= MyPanel.myPanel.getHero().getX() + 40
//                            && this.getY() >= MyPanel.myPanel.getHero().getY()
//                            && this.getY() <= MyPanel.myPanel.getHero().getY() + 60) {
//                        return false;
//                    }
//                    //当前tank右下角 坐标(this.getX()+60,this.getY()+40)
//                    if (this.getX() + 60 >= MyPanel.myPanel.getHero().getX()
//                            && this.getX() + 60 <= MyPanel.myPanel.getHero().getX() + 40
//                            && this.getY() + 40 >= MyPanel.myPanel.getHero().getY()
//                            && this.getY() + 40 <= MyPanel.myPanel.getHero().getY() + 60) {
//                        return false;
//                    }
//                }
//                //当敌方坦克的方向是左/右
//                //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+60)
//                //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+40)
//                //当前tank右上角 坐标(this.getX()+60,this.getY())
//                if (MyPanel.myPanel.getHero().getDirection() == 1 || MyPanel.myPanel.getHero().getDirection() == 3) {
//                    if (this.getX() + 60 >= MyPanel.myPanel.getHero().getX()
//                            && this.getX() + 60 <= MyPanel.myPanel.getHero().getX() + 60
//                            && this.getY() >= MyPanel.myPanel.getHero().getY()
//                            && this.getY() <= MyPanel.myPanel.getHero().getY() + 40) {
//                        return false;
//                    }
//                    //当前tank右下角 坐标(this.getX()+60,this.getY()+40)
//                    if (this.getX() + 60 >= MyPanel.myPanel.getHero().getX()
//                            && this.getX() + 60 <= MyPanel.myPanel.getHero().getX() + 60
//                            && this.getY() + 40 >= MyPanel.myPanel.getHero().getY()
//                            && this.getY() + 40 <=MyPanel.myPanel.getHero().getY() + 40) {
//                        return false;
//                    }
//                }
//                break;
//            case 2:
//                //当敌方坦克的方向是上/下
//                //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+40)
//                //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+60)
//                //当前tank左下角 坐标(this.getX(),this.getY()+60)
//                if (MyPanel.myPanel.getHero().getDirection() == 0 || MyPanel.myPanel.getHero().getDirection() == 2) {
//                    if (this.getX() + 60 >= MyPanel.myPanel.getHero().getX()
//                            && this.getX() + 60 <= MyPanel.myPanel.getHero().getX() + 40
//                            && this.getY() >= MyPanel.myPanel.getHero().getY()
//                            && this.getY() <= MyPanel.myPanel.getHero().getY() + 60) {
//                        return false;
//                    }
//                    //当前tank右下角 坐标(this.getX()+40,this.getY()+60)
//                    if (this.getX() + 40 >= MyPanel.myPanel.getHero().getX()
//                            && this.getX() + 40 <= MyPanel.myPanel.getHero().getX() + 40
//                            && this.getY() + 60 >= MyPanel.myPanel.getHero().getY()
//                            && this.getY() + 60 <= MyPanel.myPanel.getHero().getY() + 60) {
//                        return false;
//                    }
//                }
//                //当敌方坦克的方向是左/右
//                //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+60)
//                //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+40)
//                //当前tank左下角 坐标(this.getX(),this.getY()+60)
//                if (MyPanel.myPanel.getHero().getDirection() == 1 || MyPanel.myPanel.getHero().getDirection() == 3) {
//                    if (this.getX() + 60 >= MyPanel.myPanel.getHero().getX()
//                            && this.getX() + 60 <= MyPanel.myPanel.getHero().getX() + 60
//                            && this.getY() + 60 >= MyPanel.myPanel.getHero().getY()
//                            && this.getY() + 60 <= MyPanel.myPanel.getHero().getY() + 40) {
//                        return false;
//                    }
//                    //当前tank右下角 坐标(this.getX()+40,this.getY()+60)
//                    if (this.getX() + 40 >=MyPanel.myPanel.getHero().getX()
//                            && this.getX() + 40 <= MyPanel.myPanel.getHero().getX() + 60
//                            && this.getY() + 60 >= MyPanel.myPanel.getHero().getY()
//                            && this.getY() + 60 <=MyPanel.myPanel.getHero().getY() + 40) {
//                        return false;
//                    }
//                }
//                break;
//            case 3:
//                //当敌方坦克的方向是上/下
//                //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+40)
//                //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+60)
//                //当前tank左上角 坐标(this.getX(),this.getY())
//                if (MyPanel.myPanel.getHero().getDirection() == 0 || MyPanel.myPanel.getHero().getDirection() == 2) {
//                    if (this.getX() >= MyPanel.myPanel.getHero().getX()
//                            && this.getX() <= MyPanel.myPanel.getHero().getX() + 40
//                            && this.getY() >= MyPanel.myPanel.getHero().getY()
//                            && this.getY() <= MyPanel.myPanel.getHero().getY() + 60) {
//                        return false;
//                    }
//                    //当前tank左下角 坐标(this.getX(),this.getY()+40)
//                    if (this.getX() >= MyPanel.myPanel.getHero().getX()
//                            && this.getX() <= MyPanel.myPanel.getHero().getX() + 40
//                            && this.getY() + 40 >= MyPanel.myPanel.getHero().getY()
//                            && this.getY() + 40 <= MyPanel.myPanel.getHero().getY() + 60) {
//                        return false;
//                    }
//                }
//                //当敌方坦克的方向是左/右
//                //敌方坦克的x坐标范围(enemyTank.getX(),enemyTank.getX()+60)
//                //敌方坦克的y坐标范围(enemyTank.getY(),enemyTank.getY()+40)
//                //当前tank左上角 坐标(this.getX(),this.getY())
//                if (MyPanel.myPanel.getHero().getDirection() == 1 || MyPanel.myPanel.getHero().getDirection() == 3) {
//                    if (this.getX() >= MyPanel.myPanel.getHero().getX()
//                            && this.getX() <= MyPanel.myPanel.getHero().getX() + 60
//                            && this.getY() >= MyPanel.myPanel.getHero().getY()
//                            && this.getY() <= MyPanel.myPanel.getHero().getY() + 40) {
//                        return false;
//                    }
//                    //当前tank左下角 坐标(this.getX(),this.getY()+40)
//                    if (this.getX() >= MyPanel.myPanel.getHero().getX()
//                            && this.getX() <= MyPanel.myPanel.getHero().getX() + 60
//                            && this.getY() + 40 >= MyPanel.myPanel.getHero().getY()
//                            && this.getY() + 40 <= MyPanel.myPanel.getHero().getY() + 40) {
//                        return false;
//                    }
//                }
//                break;
//        }
//        return true;
//    }

    @Override
    public void run() {//自由移动放在run方法内 启动线程可以进行循环操作
        while (true) {
            if (isLive() && vector.size() < 1) {
                Shot s = null;
                switch (getDirection()) {//判断坦克的方向
                    case 0:
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        s = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        s = new Shot(getX(), getY() + 20, 3);
                        break;
                }
                vector.add(s);
                new Thread(s).start();//这里需要启动子弹线程 启动的是新创建的s的线程
            }
            switch (getDirection()) {//根据传入的坦克方向
                case 0://每个方向多走几步 不然会一直变换方向
                    for (int i = 0; i < 30; i++) {
                        if (isTouch()) {//有碰撞返回false 无碰撞返回true
                            up();
                        }
                        try {
                            Thread.sleep(50);//休眠50毫秒
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 60; i++) {
                        if (isTouch()) {
                            right();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if (isTouch()) {
                            down();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if (isTouch()) {
                            left();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }//Math.random()生成一个[0,1) Math.random()*4生成一个[0,4)
            setDirection((int) (Math.random() * 4));//生成一个不规则的的方向
            //涉及多线程 要考虑什么时候退出循环
            if (isLive() == false) {
                break;
            }
        }
    }
}
