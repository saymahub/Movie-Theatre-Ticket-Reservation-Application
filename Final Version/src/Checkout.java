/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.5 from 1.0
*/

import java.text.DecimalFormat;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Checkout extends JFrame{
    private int numberOfTickets; // holds all infromation and entires up until this point in the porgram
    private ArrayList<Seat> selectedseats = new ArrayList<Seat>();
    private double amount;
    protected Receipt myReceipt = new Receipt();
    protected Showtime show;
    protected Movie movie;
    protected RegisteredUser user = null; // checks if user is logged in
    protected boolean regflag = false;
    protected BillingInfo billinfo = new BillingInfo();
    protected Payment pay = new Payment();
    protected boolean paymentflag = false; // for regular users
    protected boolean addressflag = false;
    DecimalFormat df = new DecimalFormat("0.00"); // ensures purchase total is in correct decimal format

    // declaring text inputs
    JTextField textFieldcardnum;
    JTextField textFieldcardtype;
    JTextField textFieldaddress;
    JTextField textFieldnothing;
    // private final Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    Notices notice = new Notices(); // allows notices

    public Checkout (){}
    public Checkout(ArrayList<Seat> ss, Showtime selectedshow, Movie selectedmovie) // constructor
    {
        this.show = selectedshow;
        this.movie = selectedmovie;
        this.selectedseats = ss;
        this.numberOfTickets = selectedseats.size();

        this.amount = 12 * selectedseats.size(); 
        if(numberOfTickets >= 1) // check amount of tickets
            ;
        else
        {
            System.err.println("You cannot have negative tickets!");
            return;
        }
        this.amount = truncateTo2(amount);

    }

    public Checkout(ArrayList<Seat> ss, Showtime selectedshow, Movie selectedmovie, RegisteredUser user) // registered user constructor
    {
        this.show = selectedshow;
        this.movie = selectedmovie;
        this.user = user;
        this.regflag = true;

        this.billinfo = user.billinginfo; // ensures billing info autofills
        this.addressflag = true;
        this.paymentflag = true;

        this.selectedseats = ss;
        this.numberOfTickets = selectedseats.size();

        this.amount = 12 * selectedseats.size(); 
        if(numberOfTickets >= 1)
            ;
        else
        {
            System.err.println("You cannot have negative tickets!");
            return;
        }
        this.amount = truncateTo2(amount);

    }

    /*Get the Receipt. */
    public Receipt getReceipt(){ return this.myReceipt; } // receive receipt 

    public Double truncateTo2(double value)
    {
        // Using the pow() method
        value = value * Math.pow(10, 2);
        value = Math.floor(value);
        value = value / Math.pow(10, 2);
        
        return value;
    }
    
    // prints confirmatin to terminal and prints receipt to txt
    public void confirmCheckout() throws IOException
    {
        System.out.println("Tickets purchased: " + this.numberOfTickets);
        System.out.println("Total amount: " + df.format(amount));
        myReceipt.printReceipt();
    } 

    public void initialize(){ // panel for regular user
        // Creating info panel
        JPanel checkoutPanel = new JPanel();
        checkoutPanel.setLayout(new GridLayout(0,4,4,5)); // make a table foundation

        // fills in table
        checkoutPanel.add(new JLabel("Movie"));
        checkoutPanel.add(new JLabel("Showtime ID"));
        checkoutPanel.add(new JLabel("Seat Number"));
        checkoutPanel.add(new JLabel("Price"));

        for(int i = 0; i < selectedseats.size(); i++)
        {
            checkoutPanel.add(new JLabel(String.valueOf(movie.getmovie_name())));
            checkoutPanel.add(new JLabel(String.valueOf(show.getShowID())));
            checkoutPanel.add(new JLabel(String.valueOf(selectedseats.get(i).getSeatNo())));
            checkoutPanel.add(new JLabel("$12.00"));  
        }
        
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new GridLayout(0,1,4,5));
        totalPanel.add(new JLabel("Your total comes to: $ " + df.format(amount))); // adds total to panel

        JPanel confirmationPanel = new JPanel();
        confirmationPanel.setLayout(new GridLayout(0, 2, 3, 3));

        JButton paymentButton = new JButton("Enter card number");
        paymentButton.addActionListener(new ActionListener(){ // ensures card num entry is valid

            @Override
            public void actionPerformed(ActionEvent e){ // yes valid, success notice
                if(pay.editCardNum(textFieldcardnum.getText())){
                    notice.setStrategy(new ConfirmationNotice());
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(Checkout.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    notice.setStrategy(new MissingInfoNotice()); // no validito, error notice
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(Checkout.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton typeButton = new JButton("Enter card type");
        typeButton.addActionListener(new ActionListener(){// ensures card type entry is valid

            @Override
            public void actionPerformed(ActionEvent e){// yes valid, success notice
                if(pay.setPaymentType(textFieldcardtype.getText())){
                    paymentflag = true;

                    notice.setStrategy(new ConfirmationNotice());
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(Checkout.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    notice.setStrategy(new MissingInfoNotice());// no validito, error notice
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(Checkout.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        billinfo.setPayment(pay);

        JButton addressButton = new JButton("Enter address");
        addressButton.addActionListener(new ActionListener(){// ensures address entry is valid

            @Override
            public void actionPerformed(ActionEvent e){// yes valid, success notice
                billinfo.setAddress(textFieldaddress.getText());

                addressflag = true;

                notice.setStrategy(new ConfirmationNotice());
                String [] myNotice = notice.returnMessages();
                JOptionPane.showMessageDialog(Checkout.this,
                    myNotice[0],
                    myNotice[1],
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });

        

        JButton purchaseButton = new JButton("Confirm Purchase");
        purchaseButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){ // prints receipt and goes to cancellatio npage 
                if((paymentflag == true && addressflag == true)){
                    myReceipt.fillReceipt(selectedseats, show, movie, billinfo, amount);
                    try {
                        confirmCheckout();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    CancelTicket cancel = new CancelTicket();
                    cancel.initialize();
                    dispose();
                }
                else{
                    notice.setStrategy(new MissingInfoNotice()); // some information missing in form
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(Checkout.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });

        // adds everything to panel
        textFieldcardnum = new JTextField();
        confirmationPanel.add(textFieldcardnum);
        confirmationPanel.add(paymentButton);

        textFieldcardtype = new JTextField();
        confirmationPanel.add(textFieldcardtype);
        confirmationPanel.add(typeButton);

        textFieldaddress = new JTextField();
        confirmationPanel.add(textFieldaddress);
        confirmationPanel.add(addressButton);

        confirmationPanel.add(purchaseButton);
        
        add(checkoutPanel, BorderLayout.NORTH);
        add(totalPanel, BorderLayout.CENTER);
        add(confirmationPanel, BorderLayout.SOUTH);

        // winfdw settings
        setTitle("List of Seats");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600,600);
        setMinimumSize(new Dimension(450,450));
        //setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initialize(RegisteredUser user){ // panel for registered user
        // Creating info panel
        JPanel checkoutPanel = new JPanel();
        checkoutPanel.setLayout(new GridLayout(0,4,4,5)); /// makes and fills table with all info

        checkoutPanel.add(new JLabel("Movie"));
        checkoutPanel.add(new JLabel("Showtime ID"));
        checkoutPanel.add(new JLabel("Seat Number"));
        checkoutPanel.add(new JLabel("Price"));

        for(int i = 0; i < selectedseats.size(); i++)
        {
            checkoutPanel.add(new JLabel(String.valueOf(movie.getmovie_name())));
            checkoutPanel.add(new JLabel(String.valueOf(show.getShowID())));
            checkoutPanel.add(new JLabel(String.valueOf(selectedseats.get(i).getSeatNo())));
            checkoutPanel.add(new JLabel("$12.00"));  
        }
        
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new GridLayout(0,1,4,5));
        totalPanel.add(new JLabel("Your total comes to: $ " + df.format(amount)));

        JPanel confirmationPanel = new JPanel();
        confirmationPanel.setLayout(new GridLayout(0, 1, 3, 6));
        
        JButton purchaseButton = new JButton("Confirm Purchase"); // just confirm purchase button because all billing info in user onject
        purchaseButton.setPreferredSize(new Dimension(100, 50));
        purchaseButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){ // prints receipt and redirects to cancellation
                if((paymentflag == true && addressflag == true)){
                    myReceipt.fillReceipt(selectedseats, show, movie, billinfo, amount);
                    try {
                        confirmCheckout();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    CancelTicket cancel = new CancelTicket();
                    cancel.initialize();
                    dispose();

                }
                else{
                    notice.setStrategy(new MissingInfoNotice()); // never reached
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(Checkout.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });

        confirmationPanel.add(purchaseButton);
        
        add(checkoutPanel, BorderLayout.NORTH);
        add(totalPanel, BorderLayout.CENTER);
        add(confirmationPanel, BorderLayout.SOUTH);

        // window settings
        setTitle("List of Seats");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600,600);
        setMinimumSize(new Dimension(450,450));
        //setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
