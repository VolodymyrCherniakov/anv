package cafe.payment;

import cafe.orders.Command;
import cafe.orders.OrderSubject;

public class PaymentCommand implements Command {

  private Checkout     checkout = null;
  private OrderSubject payment  = null;
  private double       amount   = 0;
  private int          table    = 0;

  public PaymentCommand(OrderSubject payment, Checkout checkout, double amount, int table) {
    this.payment = payment;
    this.checkout = checkout;
    this.amount = amount;
    this.table = table;
  }

  @Override
  public void execute() {
    this.checkout.processPayment(amount, table);
  }

}
