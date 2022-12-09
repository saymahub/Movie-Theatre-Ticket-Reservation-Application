/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.5 from 1.0
*/

import java.util.ArrayList;

public class Movie 
{
  
    private String movie_name; // holds all movie info
    private int movieID;
    private int duration; // in minutes
    private String released;
    private ArrayList<Showtime> showTimes;

    public Movie(int movieID, String movie_name, int duration, String released) // constructor
    {
        this.movieID = movieID;
        this.movie_name = movie_name;
        this.duration = duration;
        this.released = released;
        this.showTimes = new ArrayList<Showtime>();
    }

    // Getters
    public int getMovieId(){ return this.movieID; }
    public String getmovie_name() {return movie_name;}

    public int getduration(){return duration;}

    public String getreleased(){return released;}
    public  ArrayList<Showtime> getshowTimes(){return showTimes;}
    

    // Setters
    public void setMovieId(int movieID) { this.movieID = movieID; }
    
    public void setmovie_name(String newmovie_name){this.movie_name = newmovie_name;}
    public void setduration(int newduration){this.duration = newduration;}
    public void setreleased(String newreleased){this.released = newreleased;}
    public void setshowTimes(ArrayList<Showtime> newshowTimes){this.showTimes = newshowTimes;}
    
}
