
/* 
 * @authors Kolby Lalonde, Carlos Morera Pinilla, Sayma Haque, Jana Afifi
 * @version 1.1 from 1.0
*/

public class BillingInfo 
{
    private Payment payment; // holds payment info and user address
    private String address;
    
    public BillingInfo(){}
    public BillingInfo(Payment payment, String address) // constructors
    {
        this.payment = payment;
        this.address = address;
    }

    // setters and getters
    public Payment getPayment(){return payment;}
    public String getAddress(){return address;}
    public void setPayment(Payment pay){this.payment = pay;}
    public void setAddress(String addy){this.address = addy;}
}
