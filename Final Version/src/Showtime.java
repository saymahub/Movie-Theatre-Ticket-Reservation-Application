/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.1 from 1.0
*/
import java.util.ArrayList;

public class Showtime 
{
    protected int ShowID; // contains all showtiem info
    protected MyDate date;
    protected String time;
    protected ArrayList<Seat> allSeats;
    protected int movieID;
    protected boolean showfull = false;
    
    public Showtime(){}

    public Showtime(int show, MyDate d, String t, int m){ // constructor
        this.ShowID = show;
        this.date = d;
        this.time = t;
        this.movieID = m;
    }

    // setters and getters
    public void setShowFull(boolean b){this.showfull = b;}

    public boolean getShowFull(){return this.showfull;}

    public void addSeat(Seat s){allSeats.add(s);}

    public void removeSeat(Seat s){allSeats.remove(s);}

    public MyDate getDate(){return this.date;}

    public String getTime(){return this.time;}

    public int getShowID(){return this.ShowID;}

    public int getMovieID(){return this.movieID;}

}
