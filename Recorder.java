package com.hspedu.tankgame2;

import java.io.*;
import java.util.Vector;

/**
 * @Author 醉眼
 * @Date 2023/7/5 19:17
 * @Version 1.0 （版本号）
 */
@SuppressWarnings({"all"})
public class Recorder {
    //定义变量，记录击毁坦克数量
    private static int recordNum = 0;
    private static Vector<EnemyTank> enemyTanks = null;
    private static Vector<Note> notes = new Vector<>();
    //定义IO对象,准备写数据到文件中
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String filePath = "src\\myRecod.txt";

    public static void main(String[] args) {

    }

    public static String getFilePath() {
        return filePath;
    }

    public static Vector<Note> getNotes() {
        return notes;
    }

    public static Vector<Note> xyd() {
        try {
            String s = "";
            br = new BufferedReader(new FileReader(filePath));
            recordNum = Integer.parseInt(br.readLine());
            while ((s = br.readLine()) != null) {
                String[] s1 = s.split(" ");
                Note note = new Note(Integer.parseInt(s1[0])
                        , Integer.parseInt(s1[1]), Integer.parseInt(s1[2]));
                notes.add(note);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return notes;
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static int getRecordNum() {
        return recordNum;
    }

    public static void setRecordNum(int recordNum) {
        Recorder.recordNum = recordNum;
    }

    public static void record() {
        try {
            bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(recordNum + "");
            bw.newLine();
            if (MyPanel.getHero()!=null&&MyPanel.getHero().isLive()) {
                bw.write(MyPanel.getHero().getX() + " " + MyPanel.getHero().getY() + " " + MyPanel.getHero().getDirection());
                bw.newLine();
            }
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive()) {
                    bw.write(enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirection() + " ");
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //当击毁一辆坦克 就++
    public static void addRecordNum() {
        recordNum++;
    }

}
