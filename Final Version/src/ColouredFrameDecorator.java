/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.2 from 1.0
*/

// part of decorator strategy to allow logo to appear
import java.awt.Graphics;
import java.awt.*;

public class ColouredFrameDecorator extends Decorator {

    int thickness;

    public ColouredFrameDecorator(Component cmp, int x, int y, int width, int heigth, int thickness){
        super(cmp, x, y, width, heigth);
        this.thickness = thickness;
    }

    @Override
    public void draw(Graphics g){

        cmp.draw(g);
    
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(this.thickness));
        g2d.setColor(Color.gray);
        g2d.drawRect(this.x, this.y, this.width, this.height);

    }

}