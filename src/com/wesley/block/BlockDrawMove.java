package com.wesley.block;

import com.wesley.Main;

import java.awt.*;

public class BlockDrawMove implements Runnable{
    Point from;
    Point to;
    int speed;
    public static boolean draw = true;

    public BlockDrawMove (Point to, int speed) {
        this.to = to;
        this.from = (Point) Main.blockDrawWindow.pen.clone();
        this.speed = speed;
    }

    @Override
    public void run() {
        System.out.println(from.x + ", " + from.y);
        double hyp = Math.hypot((to.x - from.x), (to.y - from.y));

        float xDist = (float) ((to.x - from.x)/hyp);
        float yDist = (float) ((to.y - from.y)/hyp);

        System.out.println("Triangles: " + (to.x - from.x) + "," + (to.y - from.y) + " \n\n" + "Hyp: " + hyp + "\nMove by: " + xDist + ", " + yDist + "\n");
        float fromx = from.x;
        float fromy = from.y;

        while (from.x != to.x || from.y != to.y) {



            fromx += xDist;
            fromy += yDist;

            from.x = (int) fromx;
            from.y = (int) fromy;

            if (Math.abs((to.x - from.x)) < 2) {
                from.x = to.x;
            }
            if (Math.abs((to.y - from.y)) < 2) {
                from.y = to.y;
            }
            if (Math.abs((to.x - from.x)) < 2 && Math.abs((to.y - from.y)) < 2) break;

            if (draw) {
                Main.blockDrawWindow.toDraw.add((Point) from.clone());
            }

            Main.blockDrawWindow.pen = from;

            try {
                Thread.sleep(100 / speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("from.x is close enough at " + from.x);
        System.out.println("from.y is close enough at " + from.y);

    }
}
