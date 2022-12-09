/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.1 from 1.0
*/
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.*;

// this class hoolds information about a purchase and copies this information to a txt file
public class Receipt 
{
    protected ArrayList<Seat> seatsInfo;
    protected Showtime show;
    protected Movie movie;
    protected BillingInfo bill;
    protected double amount;
    DecimalFormat df = new DecimalFormat("0.00"); // ensures purchase total is in correct decimal format

    public Receipt(){}

    public void fillReceipt(ArrayList<Seat> seatsInfo, Showtime selectedshow, Movie selectedmovie, BillingInfo bill, double amount) // initilizes all info
    {
        this.seatsInfo = seatsInfo;
        this.show = selectedshow;
        this.movie = selectedmovie;
        this.bill = bill;
        this.amount = amount;
    }

    // prints receipt to txt file outside src/lib/bin 
    public void printReceipt() throws IOException 
    {
        System.out.println("Printing receipt to txt file, this may take a moment...");
        FileWriter file = new FileWriter("receipt.txt");
        PrintWriter writer = new PrintWriter(file);

        writer.println("Theatre Ticket Reservation Confirmation Receipt");
        writer.println("\nTicket(s) Reserved for " + movie.getmovie_name() + ":");
        writer.println("\nSeat(s) selected: ");
        for(int i = 0; i < seatsInfo.size(); i++){ writer.println(seatsInfo.get(i).getSeatNo());}
        writer.println("\nYour total: $" + df.format(amount));
        writer.println("\nMovie Date + Time: " + show.getDate().getMonth() + " " + show.getDate().getDay() + " " + show.getTime());
        writer.println("\nBilling Info:");
        writer.println("Card Number: " + bill.getPayment().getCardNumber());
        writer.println("Address: " + bill.getAddress());
        writer.println("\nThank you for your purchase! \nHave a nice day :)");
        writer.close();
        
        System.out.println("Receipt printed. Please check the source folder.");
    }

    // getters
    public ArrayList<Seat> getSeats(){return this.seatsInfo;}
    public Showtime getShowtime(){return this.show;}
    public Movie getMovie(){return this.movie;}
    public BillingInfo getBillInfo(){return this.bill;}
    public Double getAmount(){return this.amount;}

}
