package payment;

public class CreditCardPayment extends Payment {
    @Override
    public boolean pay(int amount) {
        System.out.println("Paid " + amount + " with credit card");
        return true;
    }
}
