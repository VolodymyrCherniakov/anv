package cafe.payment;

public interface PaymentStrategy {

  public String pay(double amount, int table);

}
