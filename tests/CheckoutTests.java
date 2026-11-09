import cafe.payment.CardPayment;
import cafe.payment.CashPayment;
import cafe.payment.Checkout;
import cafe.payment.PaymentStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutTests {

  @Test
  @DisplayName("Checkout should pass the amount and table to its initial strategy exactly once and return its result")
  void testProcessPaymentDelegatesToInitialStrategy() {
    List<String> calls = new ArrayList<>();
    PaymentStrategy strategy = (amount, table) -> {
      calls.add(amount + ":" + table);
      return "Payment requested";
    };
    Checkout checkout = new Checkout(strategy);

    String result = checkout.processPayment(125.5, 7);

    assertEquals("Payment requested", result);
    assertEquals(List.of("125.5:7"), calls);
  }

  @Test
  @DisplayName("Changing the strategy should switch subsequent payments from card to cash")
  void testSetStrategyChangesPaymentMethod() {
    Checkout checkout = new Checkout(new CardPayment());

    assertEquals("Customer want to pay 150.0 using Card (table 4)", checkout.processPayment(150, 4));

    checkout.setStrategy(new CashPayment());

    assertEquals("Customer want to pay 100.0 using Cash (table 2)", checkout.processPayment(100, 2));
  }

  @Test
  @DisplayName("Repeated payments should use the amount and table from each call")
  void testRepeatedPaymentsUseCurrentArguments() {
    Checkout checkout = new Checkout(new CardPayment());

    assertEquals("Customer want to pay 150.0 using Card (table 4)", checkout.processPayment(150, 4));
    assertEquals("Customer want to pay 99.5 using Card (table 7)", checkout.processPayment(99.5, 7));
  }

}
