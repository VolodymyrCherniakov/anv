package cafe.payment;

public class Checkout {

  private PaymentStrategy strategy;

  public Checkout(PaymentStrategy strategy) {
    this.strategy = strategy;
  }

  public void setStrategy(PaymentStrategy strategy) {
    this.strategy = strategy;
  }

  public String processPayment(double amount, int table) {
    return strategy.pay(amount, table);
  }

}
