package com.leetcode.hard;

import com.leetcode.Solution;

import java.util.HashSet;
import java.util.Set;

/**
 * RobotRoomCleaner
 * 房间（用格栅表示）中有一个扫地机器人。格栅中的每一个格子有空和障碍物两种可能。
 * <p>
 * 扫地机器人提供4个API，可以向前进，向左转或者向右转。每次转弯90度。
 * <p>
 * 当扫地机器人试图进入障碍物格子时，它的碰撞传感器会探测出障碍物，使它停留在原地。
 * <p>
 * 请利用提供的4个API编写让机器人清理整个房间的算法。
 * <p>
 * interface Robot {
 *   // 若下一个方格为空，则返回true，并移动至该方格
 *   // 若下一个方格为障碍物，则返回false，并停留在原地
 *   boolean move();
 * <p>
 * // 在调用turnLeft/turnRight后机器人会停留在原位置
 *   // 每次转弯90度
 *   void turnLeft();
 *   void turnRight();
 * <p>
 * // 清理所在方格
 * void clean();
 * }
 * 示例:
 * <p>
 * 输入:
 * room = [
 * [1,1,1,1,1,0,1,1],
 * [1,1,1,1,1,0,1,1],
 * [1,0,1,1,1,1,1,1],
 * [0,0,0,1,0,0,0,0],
 * [1,1,1,1,1,1,1,1]
 * ],
 * row = 1,
 * col = 3
 * <p>
 * 解析:
 * 房间格栅用0或1填充。0表示障碍物，1表示可以通过。
 * 机器人从row=1，col=3的初始位置出发。在左上角的一行以下，三列以右。
 * 注意:
 * <p>
 * 输入只用于初始化房间和机器人的位置。你需要“盲解”这个问题。换而言之，你必须在对房间和机器人位置一无所知的情况下，只使用4个给出的API解决问题。 
 * 扫地机器人的初始位置一定是空地。
 * 扫地机器人的初始方向向上。
 * 所有可抵达的格子都是相连的，亦即所有标记为1的格子机器人都可以抵达。
 * 可以假定格栅的四周都被墙包围。
 * <p>
 * 链接：https://leetcode-cn.com/problems/robot-room-cleaner
 * <p>
 *
 * @author yangzhongwei
 * @date 2020-03-02 16:38
 */
public class RobotRoomCleaner extends Solution {

    public void cleanRoom(Robot robot) {
        Set<String> cleaned = new HashSet<>();
        clean(0,0,-1, cleaned, robot);
        doClean(0, 0 , 2, cleaned, robot);
    }

    public boolean doClean(int x, int y, int direct, Set<String> cleaned, Robot robot) {
        int next_x = x, next_y = y;
        switch (direct) {
            case 0:
                next_x = x + 1;
                break;
            case 1:
                next_y = y - 1;
                break;
            case 2:
                next_x = x - 1;
                break;
            case 3:
                next_y = y + 1;
        }
        if (!cleaned.contains(next_x + "_" + next_y)) {
            cleaned.add(next_x + "_" + next_y);
            boolean result = robot.move();
            if (result) {
                clean(next_x, next_y, direct, cleaned, robot);
                robot.move();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    public void clean(int x, int y, int direct, Set<String> cleaned, Robot robot) {
        robot.clean();
        boolean reuslt = doClean(x, y, direct, cleaned, robot);
        if (reuslt)
            robot.turnLeft();
        else
            robot.turnRight();
        direct = (direct + 1) % 4;
        reuslt = doClean(x, y, direct, cleaned, robot);
        if (!reuslt) {
            robot.turnRight();
            robot.turnRight();
        }
        direct = (direct + 2) % 4;
        reuslt = doClean(x, y, direct, cleaned, robot);
        if(!reuslt){
            robot.turnLeft();
        }else {
            robot.turnRight();
        }
    }

    @Override
    public void test() {
    }
}

// This is the robot's control interface.
// You should not implement it, or speculate about its implementation
interface Robot {

    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    public boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();

    public void turnRight();

    // Clean the current cell.
    public void clean();
}
