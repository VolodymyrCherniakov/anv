import cafe.drinks.CustomDrink;
import cafe.drinks.config.CafeConfig;
import cafe.observers.EmployeeObserver;
import cafe.orders.OrderSubject;
import cafe.payment.CardPayment;
import cafe.payment.CashPayment;
import cafe.payment.Checkout;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ResourceLock(Resources.SYSTEM_OUT)
class EmployeeObserverTests {

  private ByteArrayOutputStream output;
  private PrintStream originalOut;
  private PrintStream capturedOut;

  @BeforeEach
  void captureOutput() {
    originalOut = System.out;
    output = new ByteArrayOutputStream();
    capturedOut = new PrintStream(output, true, StandardCharsets.UTF_8);
    System.setOut(capturedOut);
  }

  @AfterEach
  void restoreOutput() {
    System.setOut(originalOut);
    capturedOut.close();
  }

  @Test
  @DisplayName("update() should print the employee name and exact status")
  void testUpdatePrintsEmployeeNameAndStatus() {
    EmployeeObserver employee = new EmployeeObserver("Anna");

    employee.update("Order is ready");

    assertEquals("Anna notified: Order is ready" + System.lineSeparator(), output.toString(StandardCharsets.UTF_8));
  }

  @Test
  @DisplayName("Each update should print a separate line with the current status")
  void testRepeatedUpdatesPrintEachStatus() {
    EmployeeObserver employee = new EmployeeObserver("Waiter");

    employee.update("Ordered Coffee");
    employee.update("Ordered Tea");

    assertEquals(
      "Waiter notified: Ordered Coffee" + System.lineSeparator()
        + "Waiter notified: Ordered Tea" + System.lineSeparator(),
      output.toString(StandardCharsets.UTF_8)
    );
  }

  @Test
  @DisplayName("The assignment example should notify both employees with the complete order")
  void testAssignmentExampleNotifiesBothEmployees() {
    OrderSubject order = new OrderSubject();
    order.addObserver(new EmployeeObserver("Barista"));
    order.addObserver(new EmployeeObserver("Waiter"));
    CustomDrink drink = new CustomDrink.Builder("coffee").milk().sugar().build();

    order.notifyAll(drink + " in " + CafeConfig.getInstance().getCafeName());

    assertEquals(
      "Barista notified: Ordered Coffee, milk, sugar in Smart Café" + System.lineSeparator()
        + "Waiter notified: Ordered Coffee, milk, sugar in Smart Café" + System.lineSeparator(),
      output.toString(StandardCharsets.UTF_8)
    );
  }

  @Test
  @DisplayName("Orders should notify both employees, while card and cash payments should notify only the waiter")
  void testPaymentNotificationsOnlyReachWaiter() {
    EmployeeObserver waiter = new EmployeeObserver("Waiter");
    EmployeeObserver barista = new EmployeeObserver("Barista");
    OrderSubject order = new OrderSubject();
    order.addObserver(barista);
    order.addObserver(waiter);
    OrderSubject payment = new OrderSubject();
    payment.addObserver(waiter);
    Checkout checkout = new Checkout(new CardPayment());

    order.notifyAll("Ordered Coffee");
    payment.notifyAll(checkout.processPayment(150, 4));
    checkout.setStrategy(new CashPayment());
    payment.notifyAll(checkout.processPayment(100, 2));

    assertEquals(
      "Barista notified: Ordered Coffee" + System.lineSeparator()
        + "Waiter notified: Ordered Coffee" + System.lineSeparator()
        + "Waiter notified: Customer want to pay 150.0 using Card (table 4)" + System.lineSeparator()
        + "Waiter notified: Customer want to pay 100.0 using Cash (table 2)" + System.lineSeparator(),
      output.toString(StandardCharsets.UTF_8)
    );
  }

}
