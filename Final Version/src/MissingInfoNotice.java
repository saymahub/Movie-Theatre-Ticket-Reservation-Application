/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.1 from 1.0
*/

// Part of Strategy Pattern
// Pop up that tells user that their is some information that is either not entered or entered incorrectly

public class MissingInfoNotice implements NoticeStrategy {

    @Override
    public String[] getMessage(){

        String [] msg = new String[2];

        msg[0] = "Error Ocurred";
        msg[1] = "Please ensure all information is valid";

        return msg;
    }

}