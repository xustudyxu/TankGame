package study.tankgame2_;

import javax.swing.*;
import java.awt.event.KeyEvent;


//为了监听 键盘事件，实现KeyListener
public class TankGame02 extends JFrame  {
    //定义一个MyPanel
    MyPanel mp=null;
    public static void main(String[] args) {
        TankGame02 tankGame01 = new TankGame02();

    }
    public TankGame02(){
        mp=new MyPanel();
        this.add(mp);//把面板（就是游戏绘图区域）
        this.setSize(1000,750);
        this.addKeyListener(mp);//让我们的JFrame监听键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }


}
