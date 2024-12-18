package payment;

public class DebitCardPayment extends Payment{
    @Override
    public boolean pay(int amount) {
        return true;
    }
}
