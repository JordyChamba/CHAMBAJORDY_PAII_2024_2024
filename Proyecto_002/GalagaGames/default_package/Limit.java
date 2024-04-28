package default_package;

import java.awt.Color;
import java.awt.Graphics;
import Interface_package.IDrawable;

class Limit implements IDrawable {
    private int x1, y1, x2, y2;

    public Limit(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE); 
        g.drawLine(x1, y1, x2, y2);
    }

}