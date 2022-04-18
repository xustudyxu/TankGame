package study.tankgame4_;


import javax.swing.*;


//为了监听 键盘事件，实现KeyListener
public class TankGame04 extends JFrame  {
    //定义一个MyPanel
    MyPanel mp=null;
    public static void main(String[] args) {
        TankGame04 tankGame01 = new TankGame04();


    }
    public TankGame04(){
        mp=new MyPanel();
        //将mp放入到 Thread，并启动
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);//把面板（就是游戏绘图区域）
        this.setSize(1000,750);
        this.addKeyListener(mp);//让我们的JFrame监听键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
