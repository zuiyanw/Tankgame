package com.hspedu.tankgame2;

import sun.net.www.content.image.gif;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

@SuppressWarnings({"all"})
//为了让paint不停的重绘 这里把MyPanel实现Runable接口，当成一个线程使用
public class MyPanel extends JPanel implements KeyListener, Runnable {//画板
//    public static MyPanel myPanel = new MyPanel();

//    public MyPanel getMyPanel() {
//        return myPanel;
//    }
//
//    public void setMyPanel(MyPanel myPanel) {
//        this.myPanel = myPanel;
//    }

    //KeyListener是监听器 可以监听键盘事件
    private static Hero hero;
    //考虑到多线程安全问题 创建一个Vector集合，而不是ArrayList
    Vector<EnemyTank> eT = new Vector<>();
    //创建爆炸集合 因为考虑到爆炸效果是由3张图片拼接后完成的
    Vector<Bomb> bomeVector = new Vector<>();
    //Recorder.xyd()返回一个Note集合 也可以不写
    Vector<Note> notes = null;
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;//初始化放在构造器中完成
    private int enemyTanksSize = 10;//坦克数量
    EnemyTank etank = null;
    private int key = 0;

    public static Hero getHero() {
        return hero;
    }

    //为了让坦克移动 把坦克的左上角坐标设置为变量(x,y)
    public MyPanel(int key) {
        File file = new File(Recorder.getFilePath());
        if (file.exists()) {//如果文件存在
            notes = Recorder.xyd();
        } else if (key == 2) {
            System.out.println("没有找到文件,只能开始新游戏");
            key = 1;
        } else {
            ;//如果key==1什么也不输出
        }
        switch (key) {
            case 1:
                this.hero = new Hero(100, 400);
                this.hero.setSpeed(3);
                for (int i = 0; i < enemyTanksSize; i++) {//初始化 把敌人坦克初始化
                    etank = new EnemyTank(100 * (i + 1), 0);//每辆坦克间距100像素
                    etank.setDirection(2);//设置敌人坦克炮筒方向
                    new Thread(etank).start();//启动etank自动走路的线程

                    eT.add(etank);//添加敌人坦克到eT集合中
                    //给该etank添加子弹
                    Shot etankshot = new Shot(etank.getX() + 20, etank.getY() + 60, etank.getDirection());
                    //把该etank子弹封装在EnemyTank类的Vector子弹集合中
                    etank.vector.add(etankshot);
                    new Thread(etankshot).start();//启动子弹线程
                    //这里不启动子弹线程的话 子弹坐标是不会变化的  绘制的在paint方法绘制
                }
                Recorder.setEnemyTanks(eT);
                break;
            case 2:
                int j = 0;
                if (MyPanel.getHero() != null && MyPanel.getHero().isLive()) {
                    this.hero = new Hero(notes.get(0).getX(), notes.get(0).getY());
                    this.hero.setSpeed(3);
                    this.hero.setDirection(notes.get(0).getDirect());
                    j = 1;
                }
                for (int i = j; i < notes.size(); i++) {//初始化 把敌人坦克初始化
                    Note note = notes.get(i);
                    etank = new EnemyTank(note.getX(), note.getY());//每辆坦克间距100像素
                    etank.setDirection(note.getDirect());//设置敌人坦克炮筒方向
                    new Thread(etank).start();//启动etank自动走路的线程

                    eT.add(etank);//添加敌人坦克到eT集合中
                    //给该etank添加子弹
                    Shot etankshot = new Shot(etank.getX() + 20, etank.getY() + 60, etank.getDirection());
                    //把该etank子弹封装在EnemyTank类的Vector子弹集合中
                    etank.vector.add(etankshot);
                    new Thread(etankshot).start();//启动子弹线程
                    //这里不启动子弹线程的话 子弹坐标是不会变化的  绘制的在paint方法绘制
                }
                Recorder.setEnemyTanks(eT);
                break;
        }
        EnemyTank.setEnemyTanks(eT);
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
        AePlayWave aePlayWave = new AePlayWave("src\\\\111.wav");//添加音频文件
        aePlayWave.start();//启动音频线程
    }

    //编写方法 记录我方击毁坦克的数量
    public void showInfo(Graphics g) {
        //画出玩家的总成绩
        g.setColor(Color.BLACK);
        g.setFont(new Font("宋体", Font.BOLD, 25));//new Font("什么字体",是不是粗体,大小)
        g.drawString("玩家击毁坦克的数量: ", 1020, 30);
        drawTank(1020, 50, g, 0, 0);//画出一个敌人坦克
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getRecordNum() + "", 1080, 90);
    }

    @Override
    public void paint(Graphics g) {//graphics 绘图工具类
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充画板 大小1000*750 默认颜色是黑色
        showInfo(g);
        if (hero != null && hero.isLive()) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirection(), hero.getType());//画出自己的坦克

            for (int i = 0; i < hero.shotsVector.size(); i++) {//遍历集合中所有的子弹
                Shot shot = hero.shotsVector.get(i);
                if (shot != null && shot.isLive()) {//画出子弹
                    g.draw3DRect(shot.getX(), shot.getY(), 1, 1, false);
                    //System.out.println("子弹绘出");
                } else {
                    hero.shotsVector.remove(shot);//如果shot对象无效，就从shot集合中拿掉
                }
            }
        }
//        if (hero.getShot() != null && hero.getShot().isLive() == true) {//画出子弹
//            g.draw3DRect(hero.getShot().getX(), hero.getShot().getY(), 1, 1, false);
//            //System.out.println("子弹绘出");
//        } 只能单发的子弹代码

        //如果炸弹集合中有对象就开始画 因为创建炸弹对象的前提是已经有坦克被击中
        for (int i = 0; i < bomeVector.size(); i++) {
            Bomb bomb = bomeVector.get(i);//取出炸弹
//因为炸弹包含3张图片，画哪一张取决于炸弹的生命值 这里主要考虑到要把爆炸效果做成一个连贯的gif图效果
            if (bomb.getLife() > 6) {
                g.drawImage(image1, bomb.getX(), bomb.getY(), 60, 60, this);
            } else if (bomb.getLife() > 3) {
                g.drawImage(image2, bomb.getX(), bomb.getY(), 60, 60, this);
            } else {
                g.drawImage(image3, bomb.getX(), bomb.getY(), 60, 60, this);
            }
            bomb.bombLife();
            if (bomb.getLife() == 0) {
                bomeVector.remove(bomb);
            }
        }

        for (int i = 0; i < eT.size(); i++) {//遍历敌人坦克
            EnemyTank enemyTank = eT.get(i);
            if (enemyTank.isLive()) {//当敌方坦克存在的时候 才绘制
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 0);//画出敌人的坦克
                //开始画子弹
                for (int j = 0; j < enemyTank.vector.size(); j++) {
                    //取出子弹
                    Shot shot = enemyTank.vector.get(j);
                    //绘制之前判断子弹状态 如果不在画布内就不绘制
                    if (shot.isLive()) {
                        g.draw3DRect(shot.getX(), shot.getY(), 1, 1, false);
                    } else {
                        enemyTank.vector.remove(shot);
                    }
                }
            }
        }
    }

    /**
     * @param x         参照原点x坐标
     * @param y         参照原点y坐标
     * @param g         画笔
     * @param direction 坦克方向
     * @param type      坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type) {
        switch (type) {
            case 0:
                g.setColor(Color.CYAN);//敌人的坦克
                break;
            case 1:
                g.setColor(Color.yellow);//自己的坦克
                break;
        }
        //0向上 1向右 2向下 3向左 顺时针方向
        switch (direction) {
            case 0://向上
                g.fill3DRect(x, y, 10, 60, false);//坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克中间的方块
                g.fillOval(x + 10, y + 20, 20, 20);//坦克中间的圆盖
                g.drawLine(x + 20, y + 30, x + 20, y);//坦克炮筒
                break;
            case 1://向右
                g.fill3DRect(x, y, 60, 10, false);//坦克左边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//坦克中间的方块
                g.fillOval(x + 20, y + 10, 20, 20);//坦克中间的圆盖
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//坦克炮筒
                break;
            case 2://向下
                g.fill3DRect(x, y, 10, 60, false);//坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克中间的方块
                g.fillOval(x + 10, y + 20, 20, 20);//坦克中间的圆盖
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//坦克炮筒
                break;
            case 3://向左
                g.fill3DRect(x, y, 60, 10, false);//坦克左边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//坦克中间的方块
                g.fillOval(x + 20, y + 10, 20, 20);//坦克中间的圆盖
                g.drawLine(x + 30, y + 20, x, y + 20);//坦克炮筒
                break;
            default:
                System.out.println("输入有误");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //有字符输出时，该方法会触发
    }

    @Override
    public void keyPressed(KeyEvent e) {//处理WSAD的方法
        //当某个键按下，该方法会触发
        if (hero != null) {
            if (e.getKeyCode() == KeyEvent.VK_S) {
                hero.setDirection(2);
                hero.down();
            } else if (e.getKeyCode() == KeyEvent.VK_W) {
                hero.setDirection(0);
                hero.up();
            } else if (e.getKeyCode() == KeyEvent.VK_D) {
                hero.setDirection(1);
                hero.right();
            } else if (e.getKeyCode() == KeyEvent.VK_A) {
                hero.setDirection(3);
                hero.left();
            }
            if (e.getKeyCode() == KeyEvent.VK_J) {
                hero.shot();
            }
            this.repaint();//让面板重绘
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //当某个键释放时，该方法会触发
    }

    //当发射多颗子弹时,传入shot集合
    public void hitTank(Vector<Shot> shot, Tank tank) {
        for (int i = 0; i < shot.size(); i++) {
            Shot shot1 = shot.get(i);//遍历每颗子弹
            hitTank(shot1, tank);
            if (tank.isLive() == false) {
                break;
            }//删除子弹在paint()方法中进行
//            if (shot1.isLive() == false) {//如果击中 shot.isLive==false 然后直接在这里从集合删除
//                if (tank instanceof Hero) {
//                    hero.shotsVector.remove(shot1);
//                }
//                eT.remove(shot1);
//            }

        }
    }

    //编写方法判断shot是否击中tank
    //什么时候判断shot是否击中tank 放在run()方法 因为此线程每100秒刷新一次
    public void hitTank(Shot shot, Tank tank) {
        switch (tank.getDirection()) {
            case 0:
            case 2:
                if (shot.getX() > tank.getX() && shot.getX() < tank.getX() + 40
                        && shot.getY() > tank.getY() && shot.getY() < tank.getY() + 60) {
                    shot.setLive(false);//子弹的生命
                    tank.setLive(false);//敌方坦克的生命
                    if (tank instanceof EnemyTank) {
                        Recorder.addRecordNum();//如果击杀就记录
                        eT.remove(tank);
                    }
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());//创建bomb，加入到集合
                    bomeVector.add(bomb);

                }
            case 1:
            case 3:
                if (shot.getX() > tank.getX() && shot.getX() < tank.getX() + 60
                        && shot.getY() > tank.getY() && shot.getY() < tank.getY() + 40) {
                    shot.setLive(false);
                    tank.setLive(false);
                    if (tank instanceof EnemyTank) {
                        Recorder.addRecordNum();//如果击杀就记录
                        eT.remove(tank);
                    }
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bomeVector.add(bomb);
                }
        }
    }

    public void hitEnemy() {
        if (hero != null && hero.getShot() != null && hero.getShot().isLive()) {
            for (int i = 0; i < eT.size(); i++) {//遍历每一个敌方坦克
                EnemyTank enemyTank = eT.get(i);
                hitTank(hero.shotsVector, enemyTank);
            }
        }

    }

    public void hitHero() {
        for (int i = 0; i < eT.size(); i++) {//遍历每一个敌方坦克
            EnemyTank enemyTank = eT.get(i);
            hitTank(enemyTank.vector, hero);
        }
    }

    @Override
    public void run() {//每隔100毫秒重绘一次
        //不停重绘子弹 因为 键盘事件里面的repaint()在按下J键后只重绘了一次
        while (true) {//这里相当于有2个线程：线程1是shot对象里的设计方法 x，y不停的增加 线程2是不断重绘
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            hitEnemy();//判断hero是否击中enemytank
            if (hero != null && hero.isLive()) {//判断enemytank是否击中hero 如果已被击中 则不再判断
                hitHero();
            }

            repaint();
        }
    }
}
////因为自己发出的子弹需要按J才会创建 所以需要先判断hero.getShot()是否为null 这个条件要放在前面
//            if (hero.getShot() != null && hero.getShot().isLive()) {
//                //因为不确定自动打中了哪个坦克 则需要挨个遍历
//                for (int i = 0; i < eT.size(); i++) {
//                    EnemyTank enemyTank = eT.get(i);
//                    hitEnemy(hero.getShot(), enemyTank); 优化前代码 存在问题：hitEnemy


