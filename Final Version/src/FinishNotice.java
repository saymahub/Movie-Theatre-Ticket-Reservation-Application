/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.1 from 1.0
*/

// Part of Strategy Pattern
// Pop up that tells user their purchase is complete and no refund has been selected

public class FinishNotice implements NoticeStrategy {

    @Override
    public String[] getMessage(){

        String [] msg = new String[2];

        msg[0] = "Please close the program.";
        msg[1] = "Purchase Complete";

        return msg;
    }

}