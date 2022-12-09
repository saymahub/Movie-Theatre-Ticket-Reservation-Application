/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.5 from 1.0
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CancelTicket extends JFrame{
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18); // sets font
    Notices notice = new Notices(); // allows pop up notice

    public CancelTicket(){}

    public void initialize(){ // creates panel and allows users to choose
        // Creating info panel
        JPanel questionPanel = new JPanel();
        JLabel label = new JLabel("Would you like to cancel your ticket(s)?");
        label.setFont(mainFont);
        questionPanel.add(label);
       
        JPanel optionsPanel = new JPanel(); // creates grid of buttons
        optionsPanel.setLayout(new GridLayout(0,2,4,5));
        JButton yesButton = new JButton("Yes");
        yesButton.setPreferredSize(new Dimension(100, 50));
        JButton noButton = new JButton("No");
        noButton.setPreferredSize(new Dimension(100, 50));

        
        optionsPanel.add(yesButton); // adds buttons to panel
        optionsPanel.add(noButton);

        yesButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){ // if yes, users will recieve a cancellation pop up and the refund is sent
                notice.setStrategy(new CancellationnNotice());
                String [] myNotice = notice.returnMessages();
                JOptionPane.showMessageDialog(CancelTicket.this,
                    myNotice[0],
                    myNotice[1],
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });

        noButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){ // if no, users will receive finish notice
                notice.setStrategy(new FinishNotice());
                String [] myNotice = notice.returnMessages();
                JOptionPane.showMessageDialog(CancelTicket.this,
                    myNotice[0],
                    myNotice[1],
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });

        add(questionPanel, BorderLayout.CENTER);
        add(optionsPanel, BorderLayout.SOUTH);

        // window settings
        setTitle("Ticket(s) Cancellation");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(450,200);
        setMinimumSize(new Dimension(200,200));
        //setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    
    }
}
