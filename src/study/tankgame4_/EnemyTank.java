package study.tankgame4_;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    //在敌人坦克类，使用Vector保存多个 Shot
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true) {
            //这里如果我们判断shots size()=0,创建一颗子弹
            //放入到shots集合 并启动
            if (isLive && shots.size() <100) {
                Shot s = null;
                //判断坦克的方向，创建对应的子弹
                switch (getDirect()) {
                    case 0:
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        s = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        s = new Shot(getX(), getY() + 20, 3);
                        break;
                }

                shots.add(s);
                //启动
                new Thread(s).start();
            }
            //休眠50毫秒
            //根据坦克的方向 来继续移动
            for (int i = 0; i < 30; i++) {
                switch (getDirect()) {
                    case 0://向上
                        if (getY() > 0) {
                            moveUp();
                        }
                        break;
                    case 1://向右
                        if (getX() + 80 < 980) {
                            moveRight();
                        }
                        break;
                    case 2://向下
                        if (getY() + 110 < 750) {
                            moveDown();
                        }
                        break;
                    case 3://向左
                        if (getX() > 0) {
                            moveLeft();
                        }
                        break;
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //然后随机改变坦克方向 0-3
            setDirect((int) (Math.random() * 4));
            if (!isLive) {
                break;//退出线程
            }

        }
    }
}
