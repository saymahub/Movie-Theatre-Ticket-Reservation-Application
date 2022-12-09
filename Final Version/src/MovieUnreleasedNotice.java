/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.1 from 1.0
*/

// Part of Strategy Pattern
// Pop up that tells non registered-user that the movie selected is unreleased and cannot be reserved

public class MovieUnreleasedNotice implements NoticeStrategy {

    @Override
    public String[] getMessage(){

        String [] msg = new String[2];

        msg[0] = "This movie is not available yet :(";
        msg[1] = "Selection Error";

        return msg;
    }

}