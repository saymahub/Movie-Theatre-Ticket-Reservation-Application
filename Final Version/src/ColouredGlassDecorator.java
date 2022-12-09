/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.2 from 1.0
*/

// part of decorator strategy to allow logo to appear
import java.awt.Graphics;
import java.awt.*;

public class ColouredGlassDecorator extends Decorator {

    public ColouredGlassDecorator(Component cmp, int x, int y, int width, int heigth){
        super(cmp, x, y, width, heigth);
    }

    @Override
    public void draw(Graphics g){

        cmp.draw(g);
    
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1 * 0.1f));
        g2d.fillRect(93, 50, 178, 50);

    }

}