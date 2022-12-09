/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.1 from 1.0
*/

// Part of Strategy Pattern
// Pop up that tells the registered user that they have already paid their membership fee

public class PaidNotice implements NoticeStrategy{
    
    @Override
    public String[] getMessage(){

        String [] msg = new String[2];

        msg[0] = "Woah You Already Paid!";
        msg[1] = "Payment Declined";

        return msg;
    }
}