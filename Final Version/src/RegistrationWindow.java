/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.2 from 1.0
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class RegistrationWindow extends JFrame{

    Notices notice = new Notices(); // notices to allow pop ups

    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18); // setting font 

    // declaring text fields
    JTextField textFieldName;
    JPasswordField textFieldPass;
    JTextField textFieldEmail;
    JTextField textFieldCard;
    JTextField textFieldCardType;
    JTextField textFieldAddress;

    public void initialize(){ // creates registration window and all of its text components
        
        // Form panel
        JLabel myRegWindow = new JLabel("Registration Window", SwingConstants.CENTER);

        JLabel myName = new JLabel("Name");
        myName.setFont(mainFont);

        textFieldName = new JTextField();
        textFieldName.setFont(mainFont);

        JLabel myPass = new JLabel("Password");
        myPass.setFont(mainFont);

        textFieldPass = new JPasswordField();
        textFieldPass.setFont(mainFont);

        JLabel myEmail = new JLabel("Email");
        myEmail.setFont(mainFont);

        textFieldEmail = new JTextField();
        textFieldEmail.setFont(mainFont);

        JLabel myCard = new JLabel("Payment Card Number");
        myCard.setFont(mainFont);

        textFieldCard = new JTextField();
        textFieldCard.setFont(mainFont);

        JLabel myCardType = new JLabel("Payment Card Type Number");
        myCardType.setFont(mainFont);

        textFieldCardType = new JTextField();
        textFieldCardType.setFont(mainFont);

        JLabel myAddress = new JLabel("Address");
        myAddress.setFont(mainFont);

        textFieldAddress = new JTextField();
        textFieldAddress.setFont(mainFont);

        JPanel pagePanel = new JPanel();
        pagePanel.setLayout(new GridLayout(0, 1, 10, 10)); // sets up grid layout for all sign up information

        // adds all components to panel
        pagePanel.add(myRegWindow);
        pagePanel.add(myName);
        pagePanel.add(textFieldName);
        pagePanel.add(myPass);
        pagePanel.add(textFieldPass);
        pagePanel.add(myEmail);
        pagePanel.add(textFieldEmail);
        pagePanel.add(myCard);
        pagePanel.add(textFieldCard);
        pagePanel.add(myCardType);
        pagePanel.add(textFieldCardType);
        pagePanel.add(myAddress);
        pagePanel.add(textFieldAddress);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(mainFont);
        cancelButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){ // cancels registration and closes program
                dispose();
            }
        });

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(mainFont);
        confirmButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){ // confirms entries and creates new user
                String name = textFieldName.getText();
                String password = String.valueOf(textFieldPass.getPassword());
                String email = textFieldEmail.getText();
                String card = textFieldCard.getText();
                String cardtype = textFieldCardType.getText();
                String address = textFieldAddress.getText();

                RegisteredUser registeredUser = authenticateNewUser(name, password, email, card, cardtype, address); // creates new registered user if inforamtion provided is valid

                if(registeredUser != null) { // if registration successful, user is taken back to login
                    LoginPage login = new LoginPage();
                    login.initialize();
                    dispose();
                }
                else{ // otherwise, error
                    notice.setStrategy(new ErrorNotice());
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(RegistrationWindow.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2,10,0)); // grid layout for buttons
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // Initailizing Frame
        add(pagePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // window settings
        setTitle("Registration Window");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400,500);
        setMinimumSize(new Dimension(350,800));
        //setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private RegisteredUser authenticateNewUser(String name, String password, String email, String card, String cardtype, String address) { // ensures information is valid for registration
        
        RegisteredUser registeredUser = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/ensf480Project"; // connects to sql to access users table
        final String NAME = "root";
        final String PASSWORD = "";

        try{
            Connection con = DriverManager.getConnection(DB_URL, NAME, PASSWORD);

            String sql = "SELECT * FROM users WHERE name=? AND password=?"; // selects all users in table who's user and pass matches the given information

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            //preparedStatement.setString(3, email); Uncomment if you want to validate a user by email aswell
            //preparedStatement.setString(4, card); Uncomment if you want to validate a user by card aswell

            ResultSet resultSet = preparedStatement.executeQuery();

            // If we find registered user then create object of registered user
            if (resultSet.next()){
                // leave registerUser variable null if user exists
            }
            // register this user
            else{
                // Insert new user info to database if new user
                Connection con2 = DriverManager.getConnection(DB_URL, NAME, PASSWORD);
                String sql2 = " INSERT INTO users (name, email, card, cardtype, password, address) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement2 = con2.prepareStatement(sql2);
                preparedStatement2.setString(1, name);
                preparedStatement2.setString(2, email);
                preparedStatement2.setString(3, card);
                preparedStatement2.setString(4, cardtype);
                preparedStatement2.setString(5, password);
                preparedStatement2.setString(6, address);

                preparedStatement2.execute();

                // Create reg user object
                registeredUser = new RegisteredUser();
                registeredUser.name = name;
                registeredUser.email = email;
                registeredUser.card = card;
                registeredUser.password = password;

                preparedStatement2.close();
                con2.close();


                notice.setStrategy(new SuccessNotice()); // success pop up
                String [] myNotice = notice.returnMessages();
                JOptionPane.showMessageDialog(RegistrationWindow.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.INFORMATION_MESSAGE);

            }

            preparedStatement.close();
            con.close();

        } catch(Exception e){ // if database not connected
            System.out.println("Database Connection Error");
        }
        return registeredUser;
    }

    
}
