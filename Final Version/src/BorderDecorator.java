/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.2 from 1.0
*/

// part of decorator strategy to allow logo to appear
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

public class BorderDecorator extends Decorator{
    public BorderDecorator(Component cmp, int x, int y, int width, int height) {
        super(cmp, x, y, width, height);
    }
    
    public void draw(Graphics g){
        cmp.draw(g);
        BasicStroke outline = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(outline);
        g2d.setColor(Color.black);
        g2d.drawRect(x, y, width, height);
    }
}