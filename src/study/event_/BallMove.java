package study.event_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//演示小球通过键盘上下左右移动
//KeyListener监听器 监听键盘时间
public class BallMove extends JFrame {//窗口
    MyPanel mp=null;
    public static void main(String[] args) {
        BallMove ballMove = new BallMove();


    }
    public BallMove(){
        mp=new MyPanel();
        this.add(mp);
        this.setSize(400,300);
        //窗口JFrame可以监听到键盘事件  及可以监听到面板上面板上面发生的键盘事件
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
//面板 可以画出小球
class MyPanel extends JPanel implements KeyListener{
    //为了让小球可以移动，把他的左上角的坐标设置变量
    int x=10;
    int y=10;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x,y,20,20);//默认黑色
    }
    //有字符输出时 该方法就会触发
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
       System.out.println((char)e.getKeyCode()+"键被按下..");
      //当按下某个键 该方法会触发
        //根据用户按下的不同键，来处理小球的移动（上下左右移动）
        if(e.getKeyCode()== KeyEvent.VK_DOWN){
            y++;
        }else if(e.getKeyCode()==KeyEvent.VK_UP) {
            y--;
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            x--;
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            x++;
        }
        //让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
//当某个键松开了 该方法会触发
    }
}