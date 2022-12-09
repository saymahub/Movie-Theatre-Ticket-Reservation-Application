/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.1 from 1.0
*/

// Part of Strategy Pattern
// Pop up that tells user their ticket has been refunded

public class CancellationnNotice implements NoticeStrategy {

    @Override
    public String[] getMessage(){

        String [] msg = new String[2];

        msg[0] = "A refund has been sent to your card. Please close the program.";
        msg[1] = "Your cancellation has been confirmed";

        return msg;
    }

}