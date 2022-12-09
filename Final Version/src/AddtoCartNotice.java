/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.1 from 1.0
*/

// Part of Strategy Pattern
// Pop up that tells user the seat has been successfully added to cart

public class AddtoCartNotice implements NoticeStrategy {

    @Override
    public String[] getMessage(){

        String [] msg = new String[2];

        msg[0] = "Press OK to continue";
        msg[1] = "Your selection has been added to cart";

        return msg;
    }

}