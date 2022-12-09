/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.8 from 1.0
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.*;

public class ShowtimeList extends JFrame
{
    private final Font mainFont = new Font("Segoe print", Font.BOLD, 18); // sets font
    Notices notice = new Notices(); // allows notices
    ArrayList<Showtime> showtimes = new ArrayList<Showtime>(); // holds all showtimes in an array list of showtime objects
    JTextField textFieldID; // declares text field for selection
    private int correspondingID; // holds past selection information
    protected Movie movie;
    protected RegisteredUser user = null; // to ensure different data is available if user is logged in
    protected boolean regflag = false;

    /*CONSTRUCTOR TO HELP GIVE SHOWTIMES BASED ON THE MOViEID. */
    public ShowtimeList(){}
    public ShowtimeList(int movieID, Movie selectedmovie){ 
        this.correspondingID = movieID;
        this.movie = selectedmovie; 
    }

    public ShowtimeList(int movieID, Movie selectedmovie, RegisteredUser user){  //if logged in
        this.correspondingID = movieID;
        this.movie = selectedmovie; 
        this.user = user;
        regflag = true;
    }

    public void initialize() // panel set up for both end users
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/ensf480project"; // database connection
        final String NAME = "root";
        final String PASSWORD = "";

        /*ESTABLISH A CONNECTION TO THE SHOWTIME TABLE. */
        try 
        {
            con = DriverManager.getConnection(DB_URL, NAME, PASSWORD);
            st = con.createStatement();
            String query = "SELECT * FROM showtimes"; // selects all showtimes to display
            rs = st.executeQuery(query);

            /*Iterate thru the db retrieving by column name. */
            while(rs.next()) // parses results 
            {
                int ID = rs.getInt("id");
                int movieID = rs.getInt("movie id");
                String month = rs.getString("month");
                String day = rs.getString("day");
                MyDate date = new MyDate(day, month);
                String time = rs.getString("time");

                showtimes.add(new Showtime(ID, date, time, movieID)); // adds to showtime arraylsit
            }

            rs.close();
            st.close();
            con.close();
        } 
        catch (Exception e) 
        {
            System.err.println("Showtime Database Connection Error!"); // database no connecto
        }
        

        // Creating info panel
        JPanel moviePanel = new JPanel();
        moviePanel.setLayout(new GridLayout(0,4,4,5)); // create table

        // fill in table
        moviePanel.add(new JLabel("Showtime Option"));
        moviePanel.add(new JLabel("Date"));
        moviePanel.add(new JLabel("Time"));
        moviePanel.add(new JLabel("Showtime Full?"));

        for(int i = 0; i < showtimes.size(); i++)
        {
            if(showtimes.get(i).getMovieID() == correspondingID)
            {
                moviePanel.add(new JLabel(String.valueOf(i)));
                moviePanel.add(new JLabel(String.valueOf(showtimes.get(i).getDate().getMonth()) + " " + String.valueOf(showtimes.get(i).getDate().getDay())));
                moviePanel.add(new JLabel(String.valueOf(showtimes.get(i).time)));
                moviePanel.add(new JLabel(String.valueOf(showtimes.get(i).showfull)));
            }
        }

        // for buttons
        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new GridLayout(0, 3, 3, 3));

        /*INSERTING NEW BACK BUTTON. */
        JButton backButton = new JButton("Back");
        backButton.setFont(mainFont);
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){ // takes user back to movie page
                MoviesPage mp = new MoviesPage();
                mp.initialize();
                dispose();
            }
        });

        JButton showtimesButton = new JButton("View Available Seats");
        showtimesButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){ // conitnues to view seats is information entered is valid


                String ID = textFieldID.getText();
                int flag = 1;
                
                try{
                    Integer.parseInt(ID);
                }catch(NumberFormatException ex){ // ensure a number has been entered
                    flag = 0;
                }

                if(flag == 1){
                    if(Integer.parseInt(ID) < showtimes.size()){ // ensures number is less than available showtimees
                        flag = 2;
                    }
                }

                if(flag == 2){
                    System.out.println("YOU PICKED THE SHOWTIME: " + showtimes.get(Integer.valueOf(ID)).getDate().getMonth() + 
                        " " + showtimes.get(Integer.valueOf(ID)).getDate().getDay() + " at " + showtimes.get(Integer.valueOf(ID)).getTime()); // prints confirmation to terminal
                    SeatsPage sp;
                    if(regflag == true){
                        sp = new SeatsPage(showtimes.get(Integer.valueOf(ID)-2).getShowID(), showtimes.get(Integer.valueOf(ID)-2), movie, user); // if registered user 
                    }
                    else{
                        sp = new SeatsPage(showtimes.get(Integer.valueOf(ID)-2).getShowID(), showtimes.get(Integer.valueOf(ID)-2), movie); // if unregistered
                    }
                    
                    sp.initialize();
                    dispose();
                }
                else{
                    notice.setStrategy(new ErrorNotice()); // selection error notice
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(ShowtimeList.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        /*Insert JFrame Components onto the GUI Page. */
        textFieldID = new JTextField();
        selectionPanel.add(backButton);
        selectionPanel.add(textFieldID);
        selectionPanel.add(showtimesButton);

        add(moviePanel, BorderLayout.NORTH);
        add(selectionPanel, BorderLayout.SOUTH);

        // window settings
        setTitle("Movie List");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600,600);
        setMinimumSize(new Dimension(450,450));
        //setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
