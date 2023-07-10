package com.hspedu.tankgame2;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class AePlayWave extends Thread {
    private String filename;

    public AePlayWave(String wavfile) {//接受一个文件
        filename = wavfile;

    }

    public void run() {

        File soundFile = new File(filename);
        AudioInputStream audioInputStream = null;
        try {
            // 获取音频输入流
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e1) {
            e1.printStackTrace();
            return;
        }
        //// 获取音频编码对象
        AudioFormat format = audioInputStream.getFormat();
//SourceDataLine是一个可写入音频信号数字流的设备，
// 例如，我们可以从一个WAV文件读取内容写入到SourceDataLine，然后再通过扬声器输出。
// 我们可以认为SourceDataLine是一个虚拟的播放器，只要电脑中连接的扬声器、耳机可用，
// 音频信号就会经SourceDataLine进行播放。
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        auline.start();
        int nBytesRead = 0;
        //这是缓冲
        byte[] abData = new byte[512];

        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0)
                    auline.write(abData, 0, nBytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            auline.drain();
            auline.close();
        }

    }
}
