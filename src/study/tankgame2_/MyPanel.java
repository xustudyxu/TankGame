package study.tankgame2_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener {
    //定义我的坦克
    Hero hero=null;
    //定义敌人的坦克 放入到Vector
    Vector<EnemyTank> enemyTanks=new Vector<>();
    int enemyTankSize=3;
    public MyPanel(){
        hero= new Hero(100,100);//初始化自己的坦克
        hero.setSpeed(10);
        //初始化敌人的坦克
        for (int i = 0; i < enemyTankSize; i++) {
            //创建一个敌人坦克
            EnemyTank enemyTank=new EnemyTank(100*(i+1),0);
            //设置方向
            enemyTank.setDirect(2);
            //加入
            enemyTanks.add(enemyTank);

        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形  默认黑色
        //画出自己的坦克
        drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),0);
        //画出敌人的坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出坦克
            EnemyTank enemyTank=enemyTanks.get(i);
            drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),1);

        }
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
            case 0://表示向下
                g.fill3DRect(x, y,10,60,false);//画出坦克左边轮子
                g.fill3DRect(x+30, y,10,60,false);//画出坦克右边轮子
                g.fill3DRect(x+10,y+10,20,40,false);//画出坦克的盖子
                g.fillOval(x+10,y+20,20,20);//画圆盖
                g.drawLine(x+20,y+30,x+20,y);//画炮筒
                break;
            case 1://表示向右
                g.fill3DRect(x, y,60,10,false);//画出坦克上 边轮子
                g.fill3DRect(x, y+30,60,10,false);//画出坦克下边轮子
                g.fill3DRect(x+10,y+10,40,20,false);//画出坦克的盖子
                g.fillOval(x+20,y+10,20,20);//画圆盖
                g.drawLine(x+30,y+20,x+60,y+20);//画炮筒
                break;
            case 2://表示向下
                g.fill3DRect(x, y,10,60,false);//画出坦克左边轮子
                g.fill3DRect(x+30, y,10,60,false);//画出坦克右边轮子
                g.fill3DRect(x+10,y+10,20,40,false);//画出坦克的盖子
                g.fillOval(x+10,y+20,20,20);//画圆盖
                g.drawLine(x+20,y+30,x+20,y+60);//画炮筒
                break;
            case 3://表示向左
                g.fill3DRect(x, y,60,10,false);//画出坦克上 边轮子
                g.fill3DRect(x, y+30,60,10,false);//画出坦克下边轮子
                g.fill3DRect(x+10,y+10,40,20,false);//画出坦克的盖子
                g.fillOval(x+20,y+10,20,20);//画圆盖
                g.drawLine(x+30,y+20,x,y+20);//画炮筒
                break;
            default:
                System.out.println("");
        }




    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
//处理wasd键
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){//按下W键
            //改变坦克方向 向上
            hero.setDirect(0);
            //修改坦克的坐标
             hero.moveUp();
        }else if(e.getKeyCode()==KeyEvent.VK_D){//按下D键
            //改变坦克方向 向右
            hero.setDirect(1);
            //修改坦克的坐标
            hero.moveRight();
        }else if(e.getKeyCode()==KeyEvent.VK_S){//按下S键
            //改变坦克方向向下
            hero.setDirect(2);
            //修改坦克的坐标
            hero.moveDown();
        }else if(e.getKeyCode()==KeyEvent.VK_A){//按下A键
            //改变坦克方向向左
            hero.setDirect(3);
            //修改坦克的坐标
            hero.moveLeft();

        }
        //让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
