package cafe.payment;

public class Checkout {

  private double amount;
  private int    table;
  PaymentStrategy strategy;

  public Checkout(double amount, int table, String type) {
    this.amount = amount;
    this.table = table;
    switch (type.trim().toLowerCase()) {
      case "card" -> strategy = new CardPayment();
      case "cash" -> strategy = new CashPayment();
      default -> throw new IllegalArgumentException("Unknown drink type: " + type);
    }
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public void setTable(int table) {
    this.table = table;
  }

  public void setStrategy(PaymentStrategy strategy) {
    this.strategy = strategy;
  }

  public String processPayment() {
    return strategy.pay(amount, table);
  }

}
