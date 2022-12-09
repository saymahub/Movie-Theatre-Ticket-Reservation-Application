/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.1 from 1.0
*/

// Part of Strategy Pattern
// Pop up that tells user the seat they selected is already reserved and cannot be added to cart

public class ReservedNotice implements NoticeStrategy {

    @Override
    public String[] getMessage(){

        String [] msg = new String[2];

        msg[0] = "This seat is already reserved.";
        msg[1] = "Selection Error";

        return msg;
    }

}
