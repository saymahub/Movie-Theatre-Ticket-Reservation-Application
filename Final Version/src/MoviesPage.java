/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.9 from 1.0
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.util.*;

public class MoviesPage extends JFrame{

    Notices notice = new Notices(); // allwos notices
    ArrayList<Movie> movies = new ArrayList<Movie>(); // holds all movies
    JTextField textFieldID;
    protected RegisteredUser user; 
    protected boolean regflag = false; // checks if user registered

    public void initialize() // panel for regular users
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/ensf480project"; // databasae connection
        final String NAME = "root";
        final String PASSWORD = "";

        try 
        {
            con = DriverManager.getConnection(DB_URL, NAME, PASSWORD);
            st = con.createStatement();
            String query = "SELECT id, title, duration, released FROM movie"; // selections all info from movie table
            rs = st.executeQuery(query);

            /*Iterate thru the db retrieving by column name. */
            while(rs.next()) // parses result to add to array list
            {
                int movieID = rs.getInt("id");
                String title = rs.getString("title");
                int duration = rs.getInt("duration");
                String released = rs.getString("released");

                movies.add(new Movie(movieID, title, duration, released));
            }

            rs.close();
            st.close();
            con.close();
        } 
        catch (Exception e) 
        {
            System.err.println("Movie Database connection failed!"); // no connection
        }

        // Creating info panel
        JPanel moviePanel = new JPanel();
        moviePanel.setLayout(new GridLayout(0,4,4,5));

        // filling in table
        moviePanel.add(new JLabel("Movie ID"));
        moviePanel.add(new JLabel("Title"));
        moviePanel.add(new JLabel("Duration (mins)"));
        moviePanel.add(new JLabel("Released"));

        for(int i = 0; i < movies.size(); i++)
        {
            moviePanel.add(new JLabel(String.valueOf(movies.get(i).getMovieId())));
            moviePanel.add(new JLabel(String.valueOf(movies.get(i).getmovie_name())));
            moviePanel.add(new JLabel(String.valueOf(movies.get(i).getduration())));
            moviePanel.add(new JLabel(String.valueOf(movies.get(i).getreleased())));
        }

        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new GridLayout(0, 3, 3, 3));

        // buttons table
        JButton showtimesButton = new JButton("View Movie Showtimes");
        showtimesButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){


                String ID = textFieldID.getText();
                int flag = 1;
                
                if(Integer.parseInt(ID) <= 0) // checks if input is valid
                {
                    notice.setStrategy(new ErrorNotice());
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(MoviesPage.this,
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
                    if(Integer.parseInt(ID) <= movies.size())
                    {
                        flag = 2;
                    }
                }

                if(flag == 2){
                    if(movies.get(Integer.valueOf(ID) - 1).getreleased().equalsIgnoreCase("false")){ // ensure movie is released
                        notice.setStrategy(new MovieUnreleasedNotice());
                        String [] myNotice = notice.returnMessages();
                        JOptionPane.showMessageDialog(MoviesPage.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.ERROR_MESSAGE);
                    }

                    else{
                        System.out.println("YOU PICKED " + movies.get(Integer.valueOf(ID) - 1).getmovie_name()); // confirmation in terminal

                        ShowtimeList shows = new ShowtimeList(movies.get(Integer.valueOf(ID) - 1).getMovieId(), movies.get(Integer.valueOf(ID) - 1)); // goes to showtime page
                        shows.initialize();
                        dispose();
                    }
                }
                else
                {
                    notice.setStrategy(new ErrorNotice()); // error
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(MoviesPage.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        

        textFieldID = new JTextField();
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

    public void initialize(RegisteredUser user) // panel for registered users
    {
        this.user = user;
        this.regflag = true;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/ensf480project"; // database connect
        final String NAME = "root";
        final String PASSWORD = "";

        try 
        {
            con = DriverManager.getConnection(DB_URL, NAME, PASSWORD);
            st = con.createStatement();
            String query = "SELECT id, title, duration, released FROM movie"; // shows all movies from database
            rs = st.executeQuery(query);

            /*Iterate thru the db retrieving by column name. */
            while(rs.next())
            {
                int movieID = rs.getInt("id");
                String title = rs.getString("title");
                int duration = rs.getInt("duration");
                String released = rs.getString("released");

                movies.add(new Movie(movieID, title, duration, released));
            }

            rs.close();
            st.close();
            con.close();
        } 
        catch (Exception e) 
        {
            System.err.println("Movie Database connection failed!");
        }

        // Creating info panel
        JPanel moviePanel = new JPanel();
        moviePanel.setLayout(new GridLayout(0,4,4,5)); // creates and fills table

        moviePanel.add(new JLabel("Movie ID"));
        moviePanel.add(new JLabel("Title"));
        moviePanel.add(new JLabel("Duration (mins)"));
        moviePanel.add(new JLabel("Released"));

        for(int i = 0; i < movies.size(); i++)
        {
            moviePanel.add(new JLabel(String.valueOf(movies.get(i).getMovieId())));
            moviePanel.add(new JLabel(String.valueOf(movies.get(i).getmovie_name())));
            moviePanel.add(new JLabel(String.valueOf(movies.get(i).getduration())));
            moviePanel.add(new JLabel(String.valueOf(movies.get(i).getreleased())));
        }

        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new GridLayout(0, 3, 3, 3)); // table of buttons

        JButton showtimesButton = new JButton("View Movie Showtimes");
        showtimesButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){


                String ID = textFieldID.getText();
                int flag = 1;
                
                if(Integer.parseInt(ID) <= 0) // checks input validity
                {
                    notice.setStrategy(new ErrorNotice());
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(MoviesPage.this,
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
                    if(Integer.parseInt(ID) <= movies.size())
                    {
                        flag = 2;
                    }
                }

                if(flag == 2){
                    
                    System.out.println("YOU PICKED " + movies.get(Integer.valueOf(ID) - 1).getmovie_name()); // any movie is valid for registered users nad prints selection to terminal

                    // GO TO APPROPRIATE SHOWTIMES HERE
                    ShowtimeList shows = new ShowtimeList(movies.get(Integer.valueOf(ID) - 1).getMovieId(), movies.get(Integer.valueOf(ID) - 1), user); // to show time page
                    shows.initialize();
                    dispose();
            
                }
                else
                {
                    notice.setStrategy(new ErrorNotice());
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(MoviesPage.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        

        textFieldID = new JTextField();
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
