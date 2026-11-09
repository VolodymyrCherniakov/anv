import cafe.payment.CashPayment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CashPaymentTests {

  @Test
  @DisplayName("Cash payment should include the amount, payment method and table")
  void testPayWithCash() {
    CashPayment payment = new CashPayment();

    assertEquals("Customer want to pay 100.0 using Cash (table 2)", payment.pay(100, 2));
  }

  @Test
  @DisplayName("Cash payment should use the supplied fractional amount and a different table")
  void testPayWithDifferentAmountAndTable() {
    CashPayment payment = new CashPayment();

    assertEquals("Customer want to pay 75.5 using Cash (table 8)", payment.pay(75.5, 8));
  }

}
