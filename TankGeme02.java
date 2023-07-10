package com.hspedu.tankgame2;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;
import java.util.Vector;

@SuppressWarnings({"all"})
public class TankGeme02 extends JFrame {//JFrame是一个框架 可以理解为绘图框架
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        TankGeme02 tankGeme1 = new TankGeme02();
    }

    public TankGeme02() {
        System.out.println("请选择：1.新游戏 2.继续上局游戏");
        int i = scanner.nextInt();
        MyPanel myPanel = new MyPanel(i);
        this.add(myPanel);//把游戏的面板(MyPanel extends Jpanel)添加到框架(JFrame)内
        this.setSize(1000, 750);
        Thread thread = new Thread(myPanel);//启动myPanel中的不断重绘线程
        thread.start();
        //窗口JFrame 对象可以监听键盘事件，即可以监听到面板发生的键盘事件
        this.addKeyListener(myPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);//可以显示
        //在Jrame增加想要关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               Recorder.record();
            }
        });//WindowAdapter是一个适配器

    }
}
