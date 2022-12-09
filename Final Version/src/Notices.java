/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.1 from 1.0
*/

// Part of Strategy Pattern
// allows program to choose and apply a notice type


public class Notices {

    private NoticeStrategy notice;

    public Notices(){}

    public void setStrategy(NoticeStrategy n){

        notice = n;

    }

    public String[] returnMessages(){

        return notice.getMessage();

    }
    
}
