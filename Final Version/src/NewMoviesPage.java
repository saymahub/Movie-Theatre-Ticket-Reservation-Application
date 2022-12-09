/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.7 from 1.0
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.util.*;

public class NewMoviesPage extends JFrame{

    Notices notice = new Notices(); //allows notices
    ArrayList<Movie> movies = new ArrayList<Movie>();
    JTextField textFieldID; // declares text input
    private RegisteredUser registeredUser = null; // can only be accessed by registered users

    public void initialize(RegisteredUser newUser)
    {
        this.registeredUser = newUser;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/ensf480project"; // database connectin
        final String NAME = "root";
        final String PASSWORD = "";

        try 
        {
            con = DriverManager.getConnection(DB_URL, NAME, PASSWORD);
            st = con.createStatement();
            String query = "SELECT id, title, duration, released FROM movie"; // gets all movies and put them into arraylist
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
            if(movies.get(i).getreleased().equalsIgnoreCase("false")){ // only chooses unreleased movies
                moviePanel.add(new JLabel(String.valueOf(movies.get(i).getMovieId())));
                moviePanel.add(new JLabel(String.valueOf(movies.get(i).getmovie_name())));
                moviePanel.add(new JLabel(String.valueOf(movies.get(i).getduration())));
                moviePanel.add(new JLabel(String.valueOf(movies.get(i).getreleased())));
            }
        }

        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new GridLayout(0, 3, 3, 3)); // buttons table

        JButton showtimesButton = new JButton("View Movie Showtimes");
        showtimesButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){


                String ID = textFieldID.getText();
                int flag = 1;
                
                if(Integer.parseInt(ID) <= 0) // check input validity
                {
                    notice.setStrategy(new ErrorNotice());
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(NewMoviesPage.this,
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
                    System.out.println("YOU PICKED " + movies.get(Integer.valueOf(ID) - 1).getmovie_name()); // picks movie and go to show time, prints to terminal

                    ShowtimeList shows = new ShowtimeList(movies.get(Integer.valueOf(ID) - 1).getMovieId(), movies.get(Integer.valueOf(ID) - 1), registeredUser);
                    shows.initialize();
                    dispose();
                }
                else
                {
                    notice.setStrategy(new ErrorNotice());
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(NewMoviesPage.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){ // go to homepage
                HomePage home = new HomePage();
                home.initialize(registeredUser);
                dispose();
            }
        });

        

        textFieldID = new JTextField();
        selectionPanel.add(textFieldID);
        selectionPanel.add(showtimesButton);
        selectionPanel.add(backButton);
        
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