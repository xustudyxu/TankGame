package study.tankgame5_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//为了监听 键盘事件 实现 KeyListener
//为了让Panel 不停的重绘 需要将MyPanel做成线程 实现Runnable 但做一个线程使用
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义我的坦克
    Hero hero = null;
    //定义敌人的坦克 放入到Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //定义一个存放Node对象 的Vector 用于恢复敌人坦克坐标
    Vector<Node> nodes=new Vector<>();
    //定义一个Vector，用来存放炸弹
    //当子弹击中坦克时，加入一个bomb对象 到bombs
    Vector<Bomb> bombs = new Vector<>();
    int enemyTankSize = 6;
    //定义三张图片 显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String key) {
        nodes=Recorder.getNodesAndEnemyTankRec();
        //将MyPanel对象的 enemyTanks设置给Recorder的enemyTanks
        Recorder.setEnemyTanks(enemyTanks);
        hero = new Hero(500, 400);//初始化自己的坦克
        hero.setSpeed(10);
        //初始化敌人的坦克
        switch (key){
            case "1":
                for (int i = 0; i < enemyTankSize; i++) {
                    //创建一个敌人坦克
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
                    //将enemyTanks 设置给 enemyTank
                    enemyTank.setEnemyTanks(enemyTanks);
                    //设置方向
                    enemyTank.setDirect(2);
                    //启动敌人坦克线程 让它动起来
                    Thread thread = new Thread(enemyTank);
                    thread.start();
                    //给该enemyTank加入一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    //加入enemyTank的Vector 成员
                    enemyTank.shots.add(shot);
                    //启动Shot
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2"://继续上局游戏
                for (int i = 0; i < nodes.size(); i++) {
                    Node node= nodes.get(i);

                    //创建一个敌人坦克
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    //将enemyTanks 设置给 enemyTank
                    enemyTank.setEnemyTanks(enemyTanks);
                    //设置方向
                    enemyTank.setDirect(node.getDirect());
                    //启动敌人坦克线程 让它动起来
                    Thread thread = new Thread(enemyTank);
                    thread.start();
                    //给该enemyTank加入一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    //加入enemyTank的Vector 成员
                    enemyTank.shots.add(shot);
                    //启动Shot
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            default:
                System.out.println("你的输入有误...");
        }

        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
    }
   //编写方法，显示我方击毁坦克的数量
    public void showInfo(Graphics g){
        //画出玩家的总成绩
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("您累计击毁敌方坦克",1020,30);
        drawTank(1020,60,g,0,1);//画出一个敌方坦克
        g.setColor(Color.BLACK);//这里需要重新设置成黑色
        g.drawString(Recorder.getAllEnemyTankNum()+"",1080,100);

    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形  默认黑色
        showInfo(g);
        //画出自己的坦克
        if(hero!=null&&hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        }
        //画出hero射击的子弹
//        if(hero.shot!=null&&hero.shot.isLive==true){
//            g.fill3DRect(hero.shot.x,hero.shot.y,2,2,false);
//        }
        //将hero的子弹集合shots 遍历取出绘制
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive == true) {
                g.fill3DRect(shot.x, shot.y, 4, 4, false);
            } else {//如果该shot对象已经无效，就从shots集合中 拿掉
                hero.shots.remove(shot);

            }

        }

        //如果bombs集合中有炸弹 就画出
        for (int i = 0; i < bombs.size(); i++) {
            //取出炸弹
            Bomb bomb = bombs.get(i);
            //根据当前这个bomb的对象的life去画出对应的图片
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, null);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            //让炸弹的生命值减少
            bomb.lifeDown();
            //如果bomb life为0 就从bombs 的集合中删除
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }

        }


        //画出敌人的坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //判断当前坦克是否存活
            if (enemyTank.isLive) {//当敌人坦克 还活着 活出敌人坦克
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                //画出 enemyTank 所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出子弹
                    Shot shot = enemyTank.shots.get(j);
                    //绘制
                    if (shot.isLive == true) {
                        g.fill3DRect(shot.x, shot.y, 4, 4, false);
                    } else {
                        //从 Vector移出
                        enemyTank.shots.remove(shot);
                    }

                }
            }
        }
    }
    //画出坦克 编写方法


    /**
     * @param x      坦克的左上角x坐标
     * @param y      坦克的左上角y坐标
     * @param g      画笔
     * @param direct 坦克方向（上下左右）
     * @param type   坦克类型
     */


    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        switch (type) {
            case 0://我们的坦克
                g.setColor(Color.GREEN);
                break;
            case 1:
                g.setColor(Color.MAGENTA);
                break;

        }
        //根据坦克的方向 来绘制坦克
        switch (direct) {
            case 0://表示向下
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克的盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画圆盖
                g.drawLine(x + 20, y + 30, x + 20, y);//画炮筒
                break;
            case 1://表示向右
                g.fill3DRect(x, y, 60, 10, false);//画出坦克上 边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克的盖子
                g.fillOval(x + 20, y + 10, 20, 20);//画圆盖
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//画炮筒
                break;
            case 2://表示向下
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克的盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画圆盖
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//画炮筒
                break;
            case 3://表示向左
                g.fill3DRect(x, y, 60, 10, false);//画出坦克上 边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克的盖子
                g.fillOval(x + 20, y + 10, 20, 20);//画圆盖
                g.drawLine(x + 30, y + 20, x, y + 20);//画炮筒
                break;
            default:
                System.out.println("");
        }
    }

    //如果我们坦克可以发射多颗子弹
    //在判断我方子弹 是否击中 敌人坦克时，就需要我们的子弹集合中
    //所有的子弹，都取出 和敌人的所有坦克 进行判断
    public void hitEnemyTank() {
        //遍历我们的子弹
        for (int j = 0; j < hero.shots.size(); j++) {
            Shot shot = hero.shots.get(j);
            //判断 是否击中敌人坦克
            if (shot != null && shot.isLive) {//当hero的子弹 还存活
                //遍历敌人 所有的坦克
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(shot, enemyTank);
                }
            }
        }
    }

    //编写方法 判断敌人坦克 是否击中我的坦克
    public void hitHero() {
        //遍历所有的敌人坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出敌人坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //遍历 enemyTank对象的所有子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                //取出子弹
                Shot shot = enemyTank.shots.get(j);
                //判断shot 是否击中我的坦克
                if (hero.isLive&&shot.isLive){
                    hitTank(shot,hero);
                }

            }

        }
    }


//编写方法 判断我方的子弹是否击中敌人坦克
    //在重绘的时候判断

    public void hitTank(Shot s, Tank enemyTank) {
        {
            //判断s 击中坦克
            switch (enemyTank.getDirect()) {
                case 0: //向上
                case 2://向下
                    if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 40
                            && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 60) {
                        s.isLive = false;
                        enemyTank.isLive = false;
                        //当我的子弹击中敌人坦克后，将enemyTank 从Vector 拿掉
                        enemyTanks.remove(enemyTank);
                        //当我方击中一个敌人坦克时，allEnemyTankNum++
                        //因为enemyTank 可以是Hero 也可以是EnemyTank
                        if(enemyTank instanceof EnemyTank){
                            Recorder.addAllEnemyTankNum();
                        }
                        //创建Bomb对象 加入到 bombs集合中
                        Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                        bombs.add(bomb);


                    }
                    break;
                case 1://坦克向右
                case 3://坦克向左
                    if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 60
                            && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 40) {
                        s.isLive = false;
                        enemyTank.isLive = false;
                        enemyTanks.remove(enemyTank);
                        //创建Bomb对象 加入到 bombs集合中
                        //当我方击中一个敌人坦克时，allEnemyTankNum++
                        //因为enemyTank 可以是Hero 也可以是EnemyTank
                        if(enemyTank instanceof EnemyTank){
                            Recorder.addAllEnemyTankNum();
                        }
                        Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                        bombs.add(bomb);
                    }
                    break;
            }

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理wasd键
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {//按下W键
            //改变坦克方向 向上
            hero.setDirect(0);
            //修改坦克的坐标
            if (hero.getY() > 0) {
                hero.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {//按下D键
            //改变坦克方向 向右
            hero.setDirect(1);
            //修改坦克的坐标
            if (hero.getX() + 80 < 1000) {
                hero.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {//按下S键
            //改变坦克方向向下
            hero.setDirect(2);
            //修改坦克的坐标
            if (hero.getY() + 110 < 750) {
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {//按下A键
            //改变坦克方向向左
            hero.setDirect(3);
            //修改坦克的坐标
            if (hero.getX() > 0) {
                hero.moveLeft();
            }

        }
        //如果用户按下的是J
        if (e.getKeyCode() == KeyEvent.VK_J) {
            System.out.println("用户按下了J，开始射击");
            //判断hero的子弹是否销毁
            //只发一颗子弹是】时
//            if (hero.shot == null&&!hero.shot.isLive) {
//                hero.shotEnemyTank();
//            }
            //发射多颗子弹
            hero.shotEnemyTank();
        }

        //让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//每隔100毫秒 重绘区域
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //判断 是否击中敌人坦克
            hitEnemyTank();
            //判断敌人坦克是否击中我们坦克
            hitHero();
            this.repaint();
        }
    }

    }

