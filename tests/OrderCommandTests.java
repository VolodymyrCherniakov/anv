import cafe.orders.Command;
import cafe.orders.OrderCommand;
import cafe.orders.OrderSubject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderCommandTests {

  @Test
  @DisplayName("Creating an order command should not notify observers before execute()")
  void testConstructorDoesNotNotifyObservers() {
    OrderSubject order = new OrderSubject();
    List<String> notifications = new ArrayList<>();
    order.addObserver(notifications::add);

    new OrderCommand(order, "Ordered Coffee");

    assertTrue(notifications.isEmpty());
  }

  @Test
  @DisplayName("Executing an order command should send the exact message once to every registered observer")
  void testExecuteNotifiesAllObservers() {
    OrderSubject order = new OrderSubject();
    List<String> baristaNotifications = new ArrayList<>();
    List<String> waiterNotifications = new ArrayList<>();
    order.addObserver(baristaNotifications::add);
    order.addObserver(waiterNotifications::add);
    Command command = new OrderCommand(order, "Ordered Coffee, milk, sugar in Smart Café");

    command.execute();

    assertEquals(List.of("Ordered Coffee, milk, sugar in Smart Café"), baristaNotifications);
    assertEquals(List.of("Ordered Coffee, milk, sugar in Smart Café"), waiterNotifications);
  }

  @Test
  @DisplayName("Each order command should retain its own message until execution")
  void testCommandsKeepSeparateOrderMessages() {
    OrderSubject order = new OrderSubject();
    List<String> notifications = new ArrayList<>();
    order.addObserver(notifications::add);
    Command coffeeOrder = new OrderCommand(order, "Ordered Coffee, milk, sugar in Smart Café");
    Command teaOrder = new OrderCommand(order, "Ordered Tea, sugar in Smart Café");

    coffeeOrder.execute();
    teaOrder.execute();

    assertEquals(List.of(
      "Ordered Coffee, milk, sugar in Smart Café",
      "Ordered Tea, sugar in Smart Café"
    ), notifications);
  }

}
