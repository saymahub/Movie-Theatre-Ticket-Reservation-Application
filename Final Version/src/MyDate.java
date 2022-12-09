/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.1 from 1.0
*/

//class date that modularizes Dates so individual portions of dates can be selected
public class MyDate{
    protected String day;
    protected String month;

    public MyDate(){}

    public MyDate(String d, String m){ // constructs date
        this.day = d;
        this.month = m;
    }

    // setters and getters
    public void setDay(String d){this.day = d;}

    public String getDay(){return this.day;}

    public String getMonth(){return this.month;}

    public void setMonth(String m){this.month = m;}

}
