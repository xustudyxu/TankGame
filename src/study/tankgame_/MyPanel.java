package study.tankgame_;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    //定义我的坦克
    Hero hero=null;
    public MyPanel(){
        hero=new Hero(100,100);//初始化自己的坦克

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形  默认黑色


        drawTank(hero.getX(),hero.getY(),g,0,0);
        drawTank(hero.getX()+60,hero.getY(),g,0,1);
    }
    //画出坦克 编写方法


    /**
     *
     * @param x  坦克的左上角x坐标
     * @param y  坦克的左上角y坐标
     * @param g  画笔
     * @param direct  坦克方向（上下左右）
     * @param type  坦克类型
     */


    public void drawTank(int x,int y,Graphics g,int direct,int type){
        switch (type){
            case 0://我们的坦克
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;

        }
        //根据坦克的方向 来绘制坦克
        switch (direct){
            case 0:
                g.fill3DRect(x, y,10,60,false);//画出坦克左边轮子
                g.fill3DRect(x+30, y,10,60,false);//画出坦克右边轮子
                g.fill3DRect(x+10,y+10,20,40,false);//画出坦克的盖子
                g.fillOval(x+10,y+20,20,20);//画圆盖
                g.drawLine(x+20,y+30,x+20,y);//画炮筒
            default:
                System.out.println("");
        }




    }
}
