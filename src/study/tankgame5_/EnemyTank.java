package study.tankgame5_;

import java.util.Vector;
@SuppressWarnings({"all"})
public class EnemyTank extends Tank implements Runnable {
    //在敌人坦克类，使用Vector保存多个 Shot
    Vector<Shot> shots = new Vector<>();
    //增加成员，EnemyTank 可以得到敌人坦克的Vector
    //1.
    Vector<EnemyTank> enemyTanks = new Vector<>();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    //这里提供一个方法 可以将MyPanel的  成员 Vector<EnemyTank> enemyTanks=new Vector<>();
    //设置到EnemyTank的成员 enemyTanks
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    //编写方法，判断当前的这个敌人坦克，是否和enemyTanks 中的其他坦克发生重叠或碰撞
    public boolean isTouchEnemyTank() {
        //判断当前敌人（this)
        switch (this.getDirect()) {
            case 0://上
                //让当前敌人坦克和其他所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从Vector中 取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克上 或下
                        //1.如果敌人坦克上 或下 x的范围[enemyTank.getX(),enemyTank.getX()+40]
                        //                  y的范围[enemyTank.getY(),enemyTank.getY()+60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2.当前坦克 左上角[this.getX(),this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3.当前坦克 右上角[this.getX()+40,this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克右 或左
                        //1.如果敌人坦克右 或左 x的范围[enemyTank.getX(),enemyTank.getX()+60]
                        //                  y的范围[enemyTank.getY(),enemyTank.getY()+40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2.当前坦克 左上角[this.getX(),this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3.当前坦克 右上角[this.getX()+40,this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }

                        }
                    }


                }
                break;
            case 1://右
                //让当前敌人坦克和其他所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从Vector中 取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克上 或下
                        //1.如果敌人坦克上 或下 x的范围[enemyTank.getX(),enemyTank.getX()+40]
                        //                  y的范围[enemyTank.getY(),enemyTank.getY()+60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2.当前坦克 右上角[this.getX()+60,this.getY()]
                            if (this.getX()+60 >= enemyTank.getX()
                                    && this.getX()+60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3.当前坦克 右下角[this.getX()+60,this.getY()+40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() +40 >= enemyTank.getY()
                                    && this.getY()+40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克右 或左
                        //1.如果敌人坦克右 或左 x的范围[enemyTank.getX(),enemyTank.getX()+60]
                        //                  y的范围[enemyTank.getY(),enemyTank.getY()+40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2.当前坦克 左上角[this.getX()+60,this.getY()]
                            if (this.getX() +60>= enemyTank.getX()
                                    && this.getX()+60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3.当前坦克 右下角[this.getX()+60,this.getY()+40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY()+40 >= enemyTank.getY()
                                    && this.getY() +40<= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2://下
                //让当前敌人坦克和其他所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从Vector中 取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克上 或下
                        //1.如果敌人坦克上 或下 x的范围[enemyTank.getX(),enemyTank.getX()+40]
                        //                  y的范围[enemyTank.getY(),enemyTank.getY()+60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2.当前坦克 左下角[this.getX(),this.getY()+60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX()<= enemyTank.getX() + 40
                                    && this.getY() +60>= enemyTank.getY()
                                    && this.getY() +60<= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3.当前坦克 右下角[this.getX()+40,this.getY()+60]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() +40 <= enemyTank.getX() + 40
                                    && this.getY() +60 >= enemyTank.getY()
                                    && this.getY()+60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克右 或左
                        //1.如果敌人坦克右 或左 x的范围[enemyTank.getX(),enemyTank.getX()+60]
                        //                  y的范围[enemyTank.getY(),enemyTank.getY()+40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2.当前坦克 左下角[this.getX,this.getY()+60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY()+60 >= enemyTank.getY()
                                    && this.getY()+60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //3.当前坦克 右下角[this.getX()+40,this.getY()+60]
                            if (this.getX() +40 >= enemyTank.getX()
                                    && this.getX()+40  <= enemyTank.getX() + 60
                                    && this.getY()+60 >= enemyTank.getY()
                                    && this.getY() +60<= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3://左
                //让当前敌人坦克和其他所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从Vector中 取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克上 或下
                        //1.如果敌人坦克上 或下 x的范围[enemyTank.getX(),enemyTank.getX()+40]
                        //                  y的范围[enemyTank.getY(),enemyTank.getY()+60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2.当前坦克 左上角[this.getX(),this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX()<= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3.当前坦克 左下角[this.getX(),this.getY()+40]
                            if (this.getX()  >= enemyTank.getX()
                                    && this.getX()  <= enemyTank.getX() + 40
                                    && this.getY() +40 >= enemyTank.getY()
                                    && this.getY()+40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克右 或左
                        //1.如果敌人坦克右 或左 x的范围[enemyTank.getX(),enemyTank.getX()+60]
                        //                  y的范围[enemyTank.getY(),enemyTank.getY()+40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2.当前坦克 左下角[this.getX,this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //3.当前坦克 右下角[this.getX,this.getY()+40]
                            if (this.getX()  >= enemyTank.getX()
                                    && this.getX()  <= enemyTank.getX() + 60
                                    && this.getY()+40 >= enemyTank.getY()
                                    && this.getY() +40<= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;

    }


    @Override
    public void run() {
        while (true) {
            //这里如果我们判断shots size()=0,创建一颗子弹
            //放入到shots集合 并启动
            if (isLive && shots.size() < 100) {
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
                        if (getY() > 0&& !isTouchEnemyTank()) {
                            moveUp();
                        }
                        break;
                    case 1://向右
                        if (getX() + 80 < 980&& !isTouchEnemyTank()) {
                            moveRight();
                        }
                        break;
                    case 2://向下
                        if (getY() + 110 < 750&& !isTouchEnemyTank()) {
                            moveDown();
                        }
                        break;
                    case 3://向左
                        if (getX() > 0&& !isTouchEnemyTank()) {
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
