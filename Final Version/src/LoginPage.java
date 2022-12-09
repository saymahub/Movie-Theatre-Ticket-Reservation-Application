/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.7 from 1.0
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class LoginPage extends JFrame{

    Notices notice = new Notices(); // allows notices

    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18); // sets font

    JTextField textFieldName; // declars text and password fields
    JPasswordField textFieldPass;
    RegistrationWindow regWindow = new RegistrationWindow();

    Component t;

    public void initialize() { // creates panel

        // Form panel
        JLabel myLoginPage = new JLabel("Movie Theatre Ticket Reservations", SwingConstants.CENTER);
        myLoginPage.setFont(mainFont);

        JLabel myName = new JLabel("Username");
        myName.setFont(mainFont);

        textFieldName = new JTextField();
        textFieldName.setFont(mainFont);

        JLabel myPass = new JLabel("Password");
        myPass.setFont(mainFont);

        textFieldPass = new JPasswordField(); // hidden text
        textFieldPass.setFont(mainFont);

        JPanel pagePanel = new JPanel();
        pagePanel.setLayout(new GridLayout(0, 1, 10, 10));

        DemoDecoratorPattern panel = new DemoDecoratorPattern();
        this.getContentPane().add(panel);

        pagePanel.add(myLoginPage);
        pagePanel.add(myName);
        pagePanel.add(textFieldName);
        pagePanel.add(myPass);
        pagePanel.add(textFieldPass);
        
        // Creating Buttons
        JButton loginButton = new JButton("Login");
        loginButton.setFont(mainFont);
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){ // authenticates user if log in
                String name = textFieldName.getText();
                String password = String.valueOf(textFieldPass.getPassword());

                RegisteredUser registeredUser = getAuthenticatedUser(name, password);

                if(registeredUser != null) {
                    HomePage home = new HomePage(); // if valid go to homepage
                    home.initialize(registeredUser);
                    dispose();
                }
                else{
                    notice.setStrategy(new ErrorNotice());
                    String [] myNotice = notice.returnMessages();
                    JOptionPane.showMessageDialog(LoginPage.this,
                        myNotice[0],
                        myNotice[1],
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Register
        JButton registerButton = new JButton("Register");
        registerButton.setFont(mainFont);
        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){ // goes to regsitaration window
                regWindow.initialize();
                dispose();
            }
        });

        JButton continueButton = new JButton("Continue");
        continueButton.setFont(mainFont);
        continueButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){ // start program as regular user and go to homepage 
                HomePage home = new HomePage();
                home.initialize();
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2,10,0));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(continueButton);
        // Initailizing Frame
        add(pagePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // window settings
        setTitle("Login Page");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400,500);
        setMinimumSize(new Dimension(350,450));
        //setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private RegisteredUser getAuthenticatedUser(String name, String password) { // authenticates user
        RegisteredUser registeredUser = null;
        /*Change to 3307 when I am testing it.*/
        final String DB_URL = "jdbc:mysql://localhost:3306/ensf480project"; // database connection
        final String NAME = "root";
        final String PASSWORD = "";

        try{
            
            Connection con = DriverManager.getConnection(DB_URL, NAME, PASSWORD);

            String sql = "SELECT * FROM users WHERE name=? AND password=?"; // selects user with matching username and password
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next())
            {
                registeredUser = new RegisteredUser(); // creates user object to hold all info from result query
                Payment pay = new Payment();
                registeredUser.name = resultSet.getString("name");
                registeredUser.email = resultSet.getString("email");
                pay.editCardNum(resultSet.getString("card"));
                pay.setPaymentType(resultSet.getString("cardtype"));
                registeredUser.billinginfo.setPayment(pay);
                registeredUser.password = resultSet.getString("password");
                registeredUser.billinginfo.setAddress(resultSet.getString("address"));
            }

            preparedStatement.close();
            con.close();

        } catch(Exception e){
            System.out.println("Database Connection Error");
        }
        return registeredUser;
    }

    /* MAIN LOGIN PAGE, THIS IS WHERE THE USER WILL START. */
    public static void main(String[] args) 
    {
        LoginPage login = new LoginPage(); // starts program at login
        login.initialize();
    }
}
