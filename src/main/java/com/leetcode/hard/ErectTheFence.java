package com.leetcode.hard;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * There are some trees, where each tree is represented by (x,y) coordinate in a
 * two-dimensional garden. Your job is to fence the entire garden using the
 * minimum length of rope as it is expensive. The garden is well fenced only if
 * all the trees are enclosed. Your task is to help find the coordinates of
 * trees which are exactly located on the fence perimeter. Example 1:
 * <p>
 * Input: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]] Output:
 * [[1,1],[2,0],[4,2],[3,3],[2,4]] Explanation:
 * <p>
 * Example 2:
 * <p>
 * Input: [[1,2],[2,2],[4,2]] Output: [[1,2],[2,2],[4,2]] Explanation:
 * <p>
 * Even you only have trees in a line, you need to use rope to enclose them.
 * <p>
 * <p>
 * Note:
 * <p>
 * All trees should be enclosed together. You cannot cut the rope to enclose
 * trees that will separate them in more than one group. All input integers will
 * range from 0 to 100. The garden has at least one tree. All coordinates are
 * distinct. Input points have NO order. No order required for output.
 * <p>
 * Definition for a point. class Point { int x; int y; Point() { x = 0; y = 0; }
 * Point(int a, int b) { x = a; y = b; } }
 * <p>
 * https://leetcode.com/problems/erect-the-fence/
 */
public class ErectTheFence {

    public static class Point {

        int x;

        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    public List<Point> outerTrees(Point[] points) {
        int min = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i].x < points[min].x)
                min = i;
            else if ((points[i].x == points[min].x) && (points[i].y < points[min].y))
                min = i;
        }
        List<Point> pointList = new ArrayList<Point>(Arrays.asList(points));
        pointList.remove(min); // 移除极点
        final Point finalMin = points[min];
        Collections.sort(pointList, new Comparator<Point>() {
            public int compare(Point o1, Point o2) {
                int isright = isright(finalMin, o1, o2);
                if (isright != 0)
                    return -isright;
                else
                    return o1.x - o2.x;
            }
        });
        pointList.add(0, finalMin); // 添加极点
        if (pointList.size() < 4)
            return pointList;
        Stack<Point> stacks = new Stack<Point>();
        stacks.push(pointList.get(0));
        Point last = pointList.get(1);
        for (int i = 2; i < pointList.size(); i++) {
            while (stacks.size() != 0 && isright(stacks.peek(), last, pointList.get(i)) < 0)
                last = stacks.pop();
            stacks.push(last);
            last = pointList.get(i);
        }
        int isright = isright(stacks.peek(), last, finalMin);
        List<Point> heads = new ArrayList<Point>();
        if (isright >= 0) {
            stacks.push(last);
            for (int i = pointList.size() - 1; i >= 0; i--) {
                if (isright(finalMin, last, pointList.get(i)) == 0)
                    heads.add(pointList.get(i));
                else
                    break;

            }
        }
        List<Point> result = new ArrayList<Point>(stacks);
        if (heads.size() > 1) {
            for (int i = 0; i < heads.size(); i++)
                if (!result.contains(heads.get(i)))
                    result.add(heads.get(i));
        }
        return result;
    }

    // 向量的叉积为正说明在右
    private int isright(Point start, Point x, Point y) {
        return (x.x - start.x) * (y.y - start.y) - (y.x - start.x) * (x.y - start.y);
    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        List<Point> points = new ErectTheFence().outerTrees(new Point[]{
                new Point(0, 3), new Point(1, 2), new Point(2, 1), new Point(2, 5), new Point(1, 4)});
        System.out.println(points);
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
    }

}
