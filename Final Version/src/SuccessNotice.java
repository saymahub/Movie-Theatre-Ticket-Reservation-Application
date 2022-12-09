/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.1 from 1.0
*/

// Part of Strategy Pattern
// Pop up that tells user that thier fee has been paid succesfully

public class SuccessNotice implements NoticeStrategy{
    
    @Override
    public String[] getMessage(){

        String [] msg = new String[2];

        msg[0] = "Success!";
        msg[1] = "Thank you";

        return msg;
    }
}
