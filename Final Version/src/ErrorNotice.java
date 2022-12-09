/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.1 from 1.0
*/

// Part of Strategy Pattern
// General Pop up that tells user that an error has occured

public class ErrorNotice implements NoticeStrategy {

    @Override
    public String[] getMessage(){

        String [] msg = new String[2];

        msg[0] = "Error Ocurred";
        msg[1] = "Please Try Again";

        return msg;
    }

}
