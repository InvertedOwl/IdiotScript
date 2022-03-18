package com.wesley.block;

import com.wesley.Main;

import java.awt.*;

public class BlockDrawMove implements Runnable{
    Point from;
    Point to;
    int speed;

    public BlockDrawMove (Point to, int speed) {
        this.to = to;
        this.from = new Point(1, 1);
        this.speed = speed;
    }

    @Override
    public void run() {
        System.out.println(from.x + ", " + from.y);
        double hyp = Math.hypot(from.x, from.y);



        while (from.x != to.x && from.y != to.y) {
            System.out.println(from.x + ", " + from.y);


            float xDist = to.x - from.x;
            float yDist = to.y - from.y;

            System.out.println(hyp);

            from.x += xDist/hyp;
            from.y += yDist/hyp;

            if (Math.abs(xDist) < 2) from.x = to.x;
            if (Math.abs(yDist) < 2) from.y = to.y;

//            Main.blockDrawWindow.toDraw.add((Point) from.clone());

            Main.blockDrawWindow.pen = from;

            try {
                Thread.sleep(100 / speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
