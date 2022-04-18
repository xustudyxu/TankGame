package study.tankgame5_;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;


//为了监听 键盘事件，实现KeyListener
public class TankGame05 extends JFrame  {
    //定义一个MyPanel
    MyPanel mp=null;
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {

        TankGame05 tankGame01 = new TankGame05();


    }
    public TankGame05(){
        System.out.println("请输入选择 1：新游戏 2：继续上局");
        String key=scanner.next();
        mp=new MyPanel(key);
        //将mp放入到 Thread，并启动
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);//把面板（就是游戏绘图区域）D
        this.setSize(1300,950);
        this.addKeyListener(mp);//让我们的JFrame监听键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
   //在JFrame 中增加相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
