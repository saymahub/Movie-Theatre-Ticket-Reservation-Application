/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.2 from 1.0
*/

// part of decorator strategy to allow logo to appear
abstract class Decorator implements Component{

    Component cmp;
    int x;
    int y;
    int width;
    public int height;

    public Decorator(Component cmp, int x, int y, int width, int height){
        this.cmp = cmp;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

}
