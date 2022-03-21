package com.wesley.block;

import com.wesley.Main;

import java.awt.*;
import java.util.ArrayList;

public class BlockDrawWindow extends Component {
    public Point pen = new Point(1, 1);
    public Point direction = new Point(1, 0);
    public ArrayList<Point> toDraw = new ArrayList<>();


    public void paint(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        com.wesley.Component.G2D = g2d;
        g2d.setStroke(new BasicStroke(1.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);


        graphics.fillOval(Main.blockDrawWindow.pen.x - 3, Main.blockDrawWindow.pen.y - 3, 6, 6);
        for (int i = 0; i < toDraw.size(); i++) {
            Point p = toDraw.get(i);
            graphics.fillOval(p.x-3, p.y-3, 6, 6);
        }

        repaint();
    }
}
