package study.tankgame5_;

import java.io.*;
import java.util.Vector;
import java.util.logging.FileHandler;

//该类用于记录相关信息和文件交互
public class Recorder {
    //定义变量，记录我方击毁敌人坦克数
    private static int allEnemyTankNum = 0;
    //定义IO对象
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "e:\\myRecord";
    //定义Vector 指向 MyPanel 对象的敌人坦克 Vector
    public static Vector<EnemyTank> enemyTanks = null;
    //定义一个Node的Vector 用于保存敌人的信息node
    private static Vector<Node> nodes = new Vector<>();

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    //增加一个方法 用于读取recordFile 恢复相关信息
    //该方法 再继续上局游戏的时候调用即可
    public static Vector<Node> getNodesAndEnemyTankRec() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
                allEnemyTankNum=Integer.parseInt(br.readLine());
                //循环读取文件，生成nodes集合
            String line="";// 255 40 0  需要分割
            while ((line=br.readLine())!=null){
                String[] xyd=line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]),
                        Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);//封装到Vector


            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return nodes;
    }


    //增加一个方法 当我们游戏退出时 我们就将allEnemyTankNum 保存到 recordFile
    //对keepRecord这个方法进行升级 保存敌人坦克的坐标 和方向

    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum + "\r\n");
            //遍历敌人坦克的Vector 然后根据情况保存即可
            //OOP,定义一个属性 然后通过setXxx方法 得到敌人坦克的Vector
            for (int i = 0; i < enemyTanks.size(); i++) {
                //取出敌人坦克
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) {//建议判断
                    //保存该enemyTank信息
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                    //写入到文件
                    bw.write(record + "\r\n");

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    //当我方坦克机会一个敌方坦克，就应当allEnemyTankNum++
    public static void addAllEnemyTankNum() {
        Recorder.allEnemyTankNum++;
    }
}
