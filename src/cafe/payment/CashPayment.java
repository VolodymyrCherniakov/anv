package cafe.payment;

public class CashPayment implements PaymentStrategy {

  @Override
  public String pay(double amount, int table) {
    return "Customer want to pay " + amount + " using Cash (table " + 4 + " )";
  }

}
