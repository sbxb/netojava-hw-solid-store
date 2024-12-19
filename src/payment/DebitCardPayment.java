package payment;

public class DebitCardPayment extends Payment {
    @Override
    public boolean pay(int amount) {
        System.out.println("Paid " + amount + " with debit card");
        return true;
    }
}
