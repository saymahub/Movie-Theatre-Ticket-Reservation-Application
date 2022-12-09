/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.4 from 1.0
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HomePage extends JFrame 
{

    Notices notice = new Notices(); // to display different pop-up messages

    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18); // setting the font for the panel

    private RegisteredUser registeredUser = null; // holds the information of the registered user if applicable
    protected boolean regflag = false; // indidcates whether ot not sometone is logged in

    private int feeFlag = 0; // indicates if fee has been payed

    public HomePage(){}

    public HomePage(int feeFlag){ //sets fee flag upon construction
        this.feeFlag = feeFlag;
    }

    public void initialize(RegisteredUser newUser){ // initlizes the panel for a registered user

        this.registeredUser = newUser;  // setting up user and flag info
        regflag = true;

        // Creating info panel for saved or registered user.
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0,2,5,5)); // setting up grid layour for information

        // filling in table
        infoPanel.add(new JLabel("Name"));
        infoPanel.add(new JLabel(registeredUser.name));
        infoPanel.add(new JLabel("Email"));
        infoPanel.add(new JLabel(registeredUser.email));
        infoPanel.add(new JLabel("Card On File"));
        infoPanel.add(new JLabel(registeredUser.billinginfo.getPayment().getPaymentType() + " card number: " + registeredUser.billinginfo.getPayment().getCardNumber()));
        infoPanel.add(new JLabel("Adress"));
        infoPanel.add(new JLabel(registeredUser.billinginfo.getAddress()));
        infoPanel.add(new JLabel("Have You Paid Your Annual fee of 24$?"));

        if(feeFlag == 0){   // changes fee paid label dpending on flag status
            infoPanel.add(new JLabel("Not Yet"));
        }
        else{
            infoPanel.add(new JLabel("You Paid"));
        }

        // Creating Buttons
        JButton backButton = new JButton("Back");
        backButton.setFont(mainFont);
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){ // takes user back to login page
                LoginPage login = new LoginPage();
                login.initialize();
                dispose();
            }
        });

        JButton payButton = new JButton("Pay annual fee");
        payButton.setFont(mainFont);
        payButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){ // allows user to pay membership fee 

                if(feeFlag == 0){ // chnages fee status
                    feeFlag = 1;
                    dispose();

                    HomePage newHome = new HomePage(1);
                    notice.setStrategy(new SuccessNotice());
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(HomePage.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.INFORMATION_MESSAGE);
                    newHome.initialize(registeredUser); // confirms fee payment and sends uer to new panel that reflects this
                } 
                else{
                    notice.setStrategy(new PaidNotice());
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(HomePage.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.INFORMATION_MESSAGE); // indicates fee has already been paid
                }
            }
           
        });

        JButton newsButton = new JButton("View Movie News");
        newsButton.setFont(mainFont);
        newsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){ // allows users to vie and reserve seats for unreleased movies

                NewMoviesPage newmovies = new NewMoviesPage();
                newmovies.initialize(registeredUser);
                dispose();
            }
           
        });


        JButton theatreButton = new JButton("View Theatre Movies");
        theatreButton.setFont(mainFont);
        theatreButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){ // allows users to view all movies
                
                MoviesPage movies = new MoviesPage();
                movies.initialize(registeredUser);
                dispose();
            }
        });

        // adding all labels and buttons to the panel and puts them in the right place
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,3,10,0));
        buttonPanel.add(backButton);
        buttonPanel.add(payButton);
        buttonPanel.add(theatreButton);
        buttonPanel.add(newsButton);

        add(infoPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // window settings
        setTitle("Homepage");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initialize(){ // initilizes the panel for a regular user

        // Creating info panel for guest user.
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0,2,5,5)); // new table

        // filling in table
        infoPanel.add(new JLabel("Name"));
        infoPanel.add(new JLabel("Unknown User"));
        infoPanel.add(new JLabel("Email"));
        infoPanel.add(new JLabel("Unknown User"));
        infoPanel.add(new JLabel("Card On File"));
        infoPanel.add(new JLabel("Unknown User"));

        // Creating Buttons
        JButton backButton = new JButton("Back");
        backButton.setFont(mainFont);
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){ // takes user back to homepage
                LoginPage login = new LoginPage();
                login.initialize();
                dispose();
            }
        });

        JButton theatreButton = new JButton("View Theatre Movies");
        theatreButton.setFont(mainFont);
        theatreButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){ // takes user to the list of all movies
                
                MoviesPage movies = new MoviesPage();
                movies.initialize();
                dispose();
            }
        });

        // adding in all panel components to panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2,10,0));
        buttonPanel.add(backButton);
        buttonPanel.add(theatreButton);

        add(infoPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // window settings
        setTitle("Homepage");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
