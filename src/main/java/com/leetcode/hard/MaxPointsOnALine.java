package com.leetcode.hard;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * <p/>
 * https://leetcode.com/problems/max-points-on-a-line/
 */
public class MaxPointsOnALine {

    /**
     * Definition for a point.
     */
    static class Point {

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

    public static class Solution {

        private static class Line {

            Point p1;

            int a;

            int b;

            public Line(Point p1, Point p2) {
                this.p1 = p1;
                if (p1.x == p2.x) {
                    a = 0;
                    b = 0;
                } else if (p1.y == p2.y) {
                    a = 0;
                    b = 1;
                } else {
                    int gcd = gcd(p1.y - p2.y, p1.x - p2.x);
                    a = (p1.y - p2.y) / gcd;
                    b = (p1.x - p2.x) / gcd;
                }
            }

            private int gcd(int i, int j) {
                return j == 0 ? i : gcd(j, i % j);
            }

            @Override
            public boolean equals(Object o) {
                Line line = (Line) o;
                if (a != line.a || b != line.b)
                    return false;
                if (p1.x == line.p1.x && p1.y == line.p1.y)
                    return true;
                Line new_line = new Line(p1, line.p1);
                return a == new_line.a && b == new_line.b;
            }

            @Override
            public int hashCode() {
                return a + (b << 12);
            }
        }

        public int maxPoints(Point[] points) {
            if (points == null || points.length == 0)
                return 0;
            if (points.length < 3)
                return points.length;
            Map<Line, Set<Integer>> lines = new HashMap<Line, Set<Integer>>(); // 个数的集合
            for (int i = 1; i < points.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (points[i].x == points[j].x && points[i].y == points[j].y)
                        continue;
                    Line line = new Line(points[i], points[j]);
                    Set<Integer> ps;
                    if ((ps = lines.get(line)) == null) {
                        ps = new HashSet<Integer>();
                        lines.put(line, ps);
                    }
                    ps.add(i);
                    ps.add(j);
                }
            }
            int result = 1;
            int start = 1;
            while (start<points.length){
                result++;
                if(points[start].x != points[start-1].x || points[start].y != points[start-1].y)
                    break;
                start++;
            }
            for (Map.Entry<Line, Set<Integer>> entry : lines.entrySet()) {
                if (entry.getValue().size() > result)
                    result = entry.getValue().size();
            }
            return result;
        }

    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("hh:mm:ss SSS").format(new Date()));
        //int[][] input = {{40, -23}, {9, 138}, {429, 115}, {50, -17}, {-3, 80}, {-10, 33}, {5, -21}, {-3, 80}, {-6, -65}, {-18, 26}, {-6, -65}, {5, 72}, {0, 77}, {-9, 86}, {10, -2}, {-8, 85}, {21, 130}, {18, -6}, {-18, 26}, {-1, -15}, {10, -2}, {8, 69}, {-4, 63}, {0, 3}, {-4, 40}, {-7, 84}, {-8, 7}, {30, 154}, {16, -5}, {6, 90}, {18, -6}, {5, 77}, {-4, 77}, {7, -13}, {-1, -45}, {16, -5}, {-9, 86}, {-16, 11}, {-7, 84}, {1, 76}, {3, 77}, {10, 67}, {1, -37}, {-10, -81}, {4, -11}, {-20, 13}, {-10, 77}, {6, -17}, {-27, 2}, {-10, -81}, {10, -1}, {-9, 1}, {-8, 43}, {2, 2}, {2, -21}, {3, 82}, {8, -1}, {10, -1}, {-9, 1}, {-12, 42}, {16, -5}, {-5, -61}, {20, -7}, {9, -35}, {10, 6}, {12, 106}, {5, -21}, {-5, 82}, {6, 71}, {-15, 34}, {-10, 87}, {-14, -12}, {12, 106}, {-5, 82}, {-46, -45}, {-4, 63}, {16, -5}, {4, 1}, {-3, -53}, {0, -17}, {9, 98}, {-18, 26}, {-9, 86}, {2, 77}, {-2, -49}, {1, 76}, {-3, -38}, {-8, 7}, {-17, -37}, {5, 72}, {10, -37}, {-4, -57}, {-3, -53}, {3, 74}, {-3, -11}, {-8, 7}, {1, 88}, {-12, 42}, {1, -37}, {2, 77}, {-6, 77}, {5, 72}, {-4, -57}, {-18, -33}, {-12, 42}, {-9, 86}, {2, 77}, {-8, 77}, {-3, 77}, {9, -42}, {16, 41}, {-29, -37}, {0, -41}, {-21, 18}, {-27, -34}, {0, 77}, {3, 74}, {-7, -69}, {-21, 18}, {27, 146}, {-20, 13}, {21, 130}, {-6, -65}, {14, -4}, {0, 3}, {9, -5}, {6, -29}, {-2, 73}, {-1, -15}, {1, 76}, {-4, 77}, {6, -29}};
        int[][] input = {{0, 0}, {-1, -1}, {2, 2}};
        Point[] points = new Point[input.length];
        for (int i = 0; i < input.length; i++) {
            points[i] = new Point(input[i][0], input[i][1]);
        }
        System.out.println(new Solution().maxPoints(points));
        System.out.println(new SimpleDateFormat("hh:mm:ss SSS").format(new Date()));
    }

}
