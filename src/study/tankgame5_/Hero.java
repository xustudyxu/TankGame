package study.tankgame5_;


import java.util.Vector;

public class Hero extends Tank {
    //定义一个shot对象 表示一个射击（线程）
    Shot shot=null;
    //可以发射多颗子弹
    Vector<Shot>shots=new Vector<>();
    public Hero(int x, int y) {
        super(x, y);
    }
    public void shotEnemyTank(){
        //面板上 最多 发射5颗
        if(shots.size()==500000){
            return;
        }
        //创建shot对象 根据当前对象hero对象的位置创建 Shot
        switch(getDirect()){//得到hero对象方向
            case 0://向上
                shot=new Shot(getX()+20,getY(),0);
                break;
            case 1://向右
                shot=new Shot(getX()+60,getY()+20,1);
                break;
            case 2://向下
                shot=new Shot(getX()+20,getY()+60,2);
                break;
            case 3://向左
                shot=new Shot(getX(),getY()+20,3);
                break;
        }
        //把新创建的shot放入到shots
        shots.add(shot);
        //启动Shot线程
        Thread thread = new Thread(shot);
        thread.start();
    }
}
