/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.2 from 1.0
*/

// part of decorator strategy to allow logo to appear
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DemoDecoratorPattern extends JPanel {
	Component t;
	
    public DemoDecoratorPattern(){ // creates logo text
   		t = new Text ("Welcome to our App", 100, 80); 
   }
	
    public void paintComponent(Graphics g){ // creates logo decorations
 	    int fontSize = 15;
		g.setFont(new Font("Segoe print", Font.BOLD, fontSize));
			
		t = new ColouredGlassDecorator(new ColouredFrameDecorator(
			new BorderDecorator(t, 93, 50, 178, 50), 93, 50, 178, 50, 5), 95, 50, 178, 50);

		t.draw(g);
    }

}