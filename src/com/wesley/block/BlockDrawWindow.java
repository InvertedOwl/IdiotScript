package com.wesley.block;

import java.awt.*;
import java.util.ArrayList;

public class BlockDrawWindow extends Component {
    public Point pen = new Point(1, 1);
    public Point direction = new Point(1, 0);
    public ArrayList<Point> toDraw = new ArrayList<>();

    public void paint(Graphics graphics) {

        for (int i = 0; i < toDraw.size(); i++) {
            Point p = toDraw.get(i);
            graphics.fillRoundRect(p.x, p.y, 5, 5, 5, 5);
        }

        repaint();
    }
}
