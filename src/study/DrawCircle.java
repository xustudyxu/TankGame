package study;

import javax.swing.*;
import java.awt.*;

public class DrawCircle extends JFrame{//JFrame对应窗口，可以理解为画框
    private MyPanel mp=null;

    public static void main(String[] args) {
        new DrawCircle();
    }
    public DrawCircle() {
        //初始化面板
        mp = new MyPanel();
     //把面板放入到窗口(画框)
        this.add(mp);
        //设置窗口的大小
        this.setSize(400,300);
        //当点击窗口的小× 程序退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出程序
        this.setVisible(true);//可以显示
    }
}
//1.先定义 一个MyPanel,继承JPanel类，画图形，就在面板上花
class MyPanel extends JPanel{
    //1.MyPanel就是一个面板
    //2.Graphics g  吧 g理解成画笔
    @Override
    public void paint(Graphics g) {//绘图方法
        super.paint(g);//调用父类的方法完成初始化
        //1.当组件第一次在屏幕上显示的时候，程序会自动调用paint()方法
        //2.窗口大小发生变化 （包括 最大 最小化）
        //3.repaint函数被调用


        //画出一个圆形
        g.drawOval(10,10,100,100);




    }
    private MyPanel mp=null;



}