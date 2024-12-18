package payment;

public class CreditCardPayment extends Payment {
    @Override
    public boolean pay(int amount) {
        return true;
    }
}
