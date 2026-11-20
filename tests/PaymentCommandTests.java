import cafe.observers.Observer;
import cafe.orders.Command;
import cafe.orders.OrderCommand;
import cafe.orders.OrderSubject;
import cafe.payment.CardPayment;
import cafe.payment.CashPayment;
import cafe.payment.Checkout;
import cafe.payment.PaymentCommand;
import cafe.payment.PaymentStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaymentCommandTests {

  @Test
  @DisplayName("Creating a payment command should neither process payment nor notify observers before execute()")
  void testConstructorDoesNotProcessOrNotify() {
    List<String> calls = new ArrayList<>();
    PaymentStrategy strategy = (amount, table) -> {
      calls.add(amount + ":" + table);
      return "Payment requested";
    };
    Checkout checkout = new Checkout(strategy);
    OrderSubject payment = new OrderSubject();
    List<String> notifications = new ArrayList<>();
    payment.addObserver(notifications::add);

    new PaymentCommand(payment, checkout, 125.5, 7);

    assertTrue(calls.isEmpty());
    assertTrue(notifications.isEmpty());
  }

  @Test
  @DisplayName("Executing a payment command should process the supplied amount and table once and notify every observer with the result")
  void testExecuteProcessesPaymentAndNotifiesAllObservers() {
    List<Double> amounts = new ArrayList<>();
    List<Integer> tables = new ArrayList<>();
    PaymentStrategy strategy = (amount, table) -> {
      amounts.add(amount);
      tables.add(table);
      return "Payment requested";
    };
    Checkout checkout = new Checkout(strategy);
    OrderSubject payment = new OrderSubject();
    List<String> waiterNotifications = new ArrayList<>();
    List<String> managerNotifications = new ArrayList<>();
    payment.addObserver(waiterNotifications::add);
    payment.addObserver(managerNotifications::add);
    Command command = new PaymentCommand(payment, checkout, 125.5, 7);

    command.execute();

    assertEquals(List.of(125.5), amounts);
    assertEquals(List.of(7), tables);
    assertEquals(List.of("Payment requested"), waiterNotifications);
    assertEquals(List.of("Payment requested"), managerNotifications);
  }

  @Test
  @DisplayName("Order commands should notify both employees, while card and cash commands should notify only the waiter")
  void testCardAndCashCommandsNotifyOnlyPaymentObservers() {
    List<String> waiterNotifications = new ArrayList<>();
    List<String> baristaNotifications = new ArrayList<>();
    Observer waiter = waiterNotifications::add;
    Observer barista = baristaNotifications::add;
    OrderSubject order = new OrderSubject();
    order.addObserver(barista);
    order.addObserver(waiter);
    OrderSubject payment = new OrderSubject();
    payment.addObserver(waiter);
    Checkout checkout = new Checkout(new CardPayment());

    new OrderCommand(order, "Ordered Coffee").execute();
    Command payByCard = new PaymentCommand(payment, checkout, 150, 4);
    payByCard.execute();
    checkout.setStrategy(new CashPayment());
    Command payByCash = new PaymentCommand(payment, checkout, 100, 2);
    payByCash.execute();

    assertEquals(List.of("Ordered Coffee"), baristaNotifications);
    assertEquals(List.of(
      "Ordered Coffee",
      "Customer want to pay 150.0 using Card (table 4)",
      "Customer want to pay 100.0 using Cash (table 2)"
    ), waiterNotifications);
  }

}
