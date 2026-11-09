import cafe.payment.CardPayment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardPaymentTests {

  @Test
  @DisplayName("Card payment should include the amount, payment method and table")
  void testPayWithCard() {
    CardPayment payment = new CardPayment();

    assertEquals("Customer want to pay 150.0 using Card (table 4)", payment.pay(150, 4));
  }

  @Test
  @DisplayName("Card payment should use the supplied fractional amount and a different table")
  void testPayWithDifferentAmountAndTable() {
    CardPayment payment = new CardPayment();

    assertEquals("Customer want to pay 99.5 using Card (table 7)", payment.pay(99.5, 7));
  }

}
