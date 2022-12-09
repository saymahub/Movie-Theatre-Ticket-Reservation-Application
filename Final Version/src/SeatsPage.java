import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.util.*;

public class SeatsPage extends JFrame{
    Notices notice = new Notices(); // allows notices
    ArrayList<Seat> seats = new ArrayList<Seat>(); // holds all seats
    ArrayList<Seat> selectedSeats = new ArrayList<Seat>(); // holds seats selected by user
    JTextField textFieldID; // decrlaring text input
    private int correspondingID; // information corresponding to past selections
    protected Showtime show;
    protected Movie movie;
    protected RegisteredUser user = null; // holds logged in info
    protected boolean regflag = false;

    public SeatsPage(){}
    public SeatsPage(int showID, Showtime selectedshow, Movie selectedmovie){ // regular constructor
        this.correspondingID = showID;
        this.show = selectedshow;
        this.movie = selectedmovie; 
    }

    public SeatsPage(int showID, Showtime selectedshow, Movie selectedmovie, RegisteredUser user){ // registered user constrctor
        this.correspondingID = showID;
        this.show = selectedshow;
        this.movie = selectedmovie; 
        this.user = user;
        this.regflag = true;
    }

    public void initialize() // panel creation for both end users 
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/ensf480project"; // database connection wooooooo
        final String NAME = "root";
        final String PASSWORD = "";

        try 
        {
            con = DriverManager.getConnection(DB_URL, NAME, PASSWORD);
            st = con.createStatement();
            String query = "SELECT * FROM seat"; // yas selecting all them seats sis
            rs = st.executeQuery(query);

            /*Iterate thru the db retrieving by column name. */
            while(rs.next())
            {
                String seatnum = rs.getString("number");
                int showtimeID = rs.getInt("showtime id");
                String avail = rs.getString("available");

                Boolean available = Boolean.parseBoolean(avail);

                seats.add(new Seat(seatnum, showtimeID, available)); // add them seatas to the array list period
            }

            rs.close();
            st.close();
            con.close();
        } 
        catch (Exception e) 
        {
            System.err.println("Movie Database connection failed!"); // oh no databse no connecto
        }

        // you want the successful add to cart to come back here!!

        // Creating info panel
        JPanel seatPanel = new JPanel();
        seatPanel.setLayout(new GridLayout(0,3,4,5)); // setting up that grid slayy

        // filling in the table queen
        seatPanel.add(new JLabel("Seat Number"));
        seatPanel.add(new JLabel("Showtime ID"));
        seatPanel.add(new JLabel("Still Available?"));

        for(int i = 0; i < seats.size(); i++)
        {
            if(seats.get(i).getShowTimeID() == correspondingID){
                seatPanel.add(new JLabel(String.valueOf(seats.get(i).getSeatNo())));
                seatPanel.add(new JLabel(String.valueOf(seats.get(i).getShowTimeID())));
                seatPanel.add(new JLabel(String.valueOf(seats.get(i).getAvailability())));
            }
        }

        // all them buttons
        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new GridLayout(0, 3, 3, 3));

        JButton cartButton = new JButton("Add to Cart");
        cartButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){ // adds seat to cart if that input is valid yo


                String ID = textFieldID.getText();
                int flag = 1;
                
                if(Integer.parseInt(ID) <= 0)
                {
                    notice.setStrategy(new ErrorNotice()); // invalid input sad
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(SeatsPage.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try
                {
                    Integer.parseInt(ID);
                }
                catch(NumberFormatException ex)
                {
                    flag = 0;
                }

                if(flag == 1)
                {
                    if(Integer.parseInt(ID) <= seats.size())
                    {
                        flag = 2;
                    }
                }

                if(flag == 2){ // all informatio valid 
                    
                    if(seats.get(Integer.valueOf(ID) - 1).getAvailability() == false){ // seat no longer available *cries*
                        notice.setStrategy(new ReservedNotice());
                        String [] myNotice = notice.returnMessages();
                        JOptionPane.showMessageDialog(SeatsPage.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.ERROR_MESSAGE);
                        
                    }
                    else{
                        System.out.println("YOU PICKED SEAT " + seats.get(Integer.valueOf(ID) - 1).getSeatNo()); // selection confirmation in terminal 
                        
                        seats.get(Integer.valueOf(ID) - 1).setAvailability(false); // changes availability
                        selectedSeats.add(seats.get(Integer.valueOf(ID) - 1));
                        
                        notice.setStrategy(new AddtoCartNotice()); // is added to cart and notice confirtms that
                        String [] myNotice = notice.returnMessages();
                        JOptionPane.showMessageDialog(SeatsPage.this,
                            myNotice[0],
                            myNotice[1],
                            JOptionPane.INFORMATION_MESSAGE);

                        
                    }
                }
                else
                {
                    notice.setStrategy(new ErrorNotice()); // sad
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(SeatsPage.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton backButton = new JButton("Continue to Checkout"); 
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){ // allows to progress to checkout 
                Checkout ch;
                if(regflag == true){
                    ch = new Checkout(selectedSeats, show, movie, user); // if logged in
                    ch.initialize(user);
                }
                else{
                    ch = new Checkout(selectedSeats, show, movie); // if not logged in
                    ch.initialize();
                }  
                
                dispose();
            }
        });

        textFieldID = new JTextField();
        selectionPanel.add(textFieldID);
        selectionPanel.add(cartButton);
        selectionPanel.add(backButton);
        
        add(seatPanel, BorderLayout.NORTH);
        add(selectionPanel, BorderLayout.SOUTH);

        // window settings blah blah
        setTitle("List of Seats");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600,600);
        setMinimumSize(new Dimension(450,450));
        //setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
