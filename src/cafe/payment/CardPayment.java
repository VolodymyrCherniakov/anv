package cafe.payment;

public class CardPayment implements PaymentStrategy {

  @Override
  public String pay(double amount, int table) {
    return "Customer want to pay " + amount + " using Card (table " + 4 + " )";
  }

}
