import cafe.drinks.CustomDrink;
import cafe.drinks.config.CafeConfig;
import cafe.observers.EmployeeObserver;
import cafe.orders.OrderSubject;
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

}
