/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.2 from 1.0
*/

// part of decorator strategy to allow logo to appear

import java.awt.Graphics;

class Text implements Component {

    int x;
    int y;
    private String text;

    public Text(String s, int x, int y){

        this.text = s;
        this.x = x;
        this.y = y;

    }

    @Override
    public void draw(Graphics g){

        g.drawString(this.text, this.x, this.y);
  
    }
}