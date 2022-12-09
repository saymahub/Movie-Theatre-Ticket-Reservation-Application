/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.6 from 1.0
*/

import java.util.regex.*;

// payment class that holds card number and type
public class Payment 
{
    private String paymentType;
    private String cardNumber;

    public Payment(){}
    public Payment(String pay, String card)
    {
        /*Saves the card number if the payement type is valid. */
        if(setPaymentType(pay))
            editCardNum(card);
        else
            System.out.println("Invalid payment method, cannot save payment type....");
            /* Could proably also throw an exception here too. */
    }

    /*SETTERS AND GETTERS*/
    public boolean editCardNum(String cardNumber)
    {
        /*Both credit and debit cards have 16 digits. */
        Pattern pattern = Pattern.compile("^[0-9]{16}$");
        Matcher matcher = pattern.matcher(cardNumber);
        
        if(matcher.find()) // if 16 numbers
        {
            System.out.println("Card Number saved!");
            this.cardNumber = cardNumber;
            return true;
        }
        else // if not
            System.out.println("Invalid card number!");
        return false;
    }
    public boolean setPaymentType(String paymentType)
    {
        /*Must pay by card, credit or debit.*/
        String pay = paymentType.toLowerCase();
        if(!pay.equals("debit") && !pay.equals("credit")) // must be credit or debit
        {
            /*ErrorNotice class throws the Exception here. */
            System.out.println("Invalid payment!");
            return false;
        }
        System.out.println("Payment accepted");
        this.paymentType = paymentType;
        return true;
    }    

    // getters
    public final String getCardNumber(){ return this.cardNumber; }
    public final String getPaymentType(){ return this.paymentType; }
}
