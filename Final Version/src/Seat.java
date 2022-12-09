/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.1 from 1.0
*/

public class Seat {
    protected String seat_no; // class members to keep track of seat info
    protected int showtimeID;
    protected Boolean available;
    
    public Seat(String seat_no, int showtimeID, Boolean available) // constructor
    {
        this.seat_no = seat_no;
        this.showtimeID = showtimeID;
        this.available = available;
    }
    // Getter
    public String getSeatNo(){return seat_no;}
    public Boolean getAvailability(){return available;}
    public int getShowTimeID(){return showtimeID;}

    // Setter
    public void setSeatNo(String newseat_no){this.seat_no = newseat_no;}
    public void setAvailability(Boolean newavailable){this.available = newavailable;}
    public void setShowTimeID(int showtimeID){this.showtimeID = showtimeID;}

    
}
