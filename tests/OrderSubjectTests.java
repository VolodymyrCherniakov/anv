import cafe.orders.OrderSubject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderSubjectTests {

  @Test
  @DisplayName("Notifying an order without observers should not fail")
  void testNotifyWithoutObservers() {
    OrderSubject order = new OrderSubject();

    assertDoesNotThrow(() -> order.notifyAll("Ordered Coffee"));
  }

  @Test
  @DisplayName("A registered observer should receive the exact status once")
  void testNotifySingleObserver() {
    OrderSubject order = new OrderSubject();
    List<String> notifications = new ArrayList<>();
    order.addObserver(notifications::add);

    order.notifyAll("Ordered Coffee, milk, sugar in Smart Café");

    assertEquals(List.of("Ordered Coffee, milk, sugar in Smart Café"), notifications);
  }

  @Test
  @DisplayName("Every observer should receive the status in registration order")
  void testNotifyAllObserversInRegistrationOrder() {
    OrderSubject order = new OrderSubject();
    List<String> notifications = new ArrayList<>();
    order.addObserver(status -> notifications.add("Barista: " + status));
    order.addObserver(status -> notifications.add("Waiter: " + status));
    order.addObserver(status -> notifications.add("Manager: " + status));

    order.notifyAll("Ordered Tea");

    assertEquals(List.of("Barista: Ordered Tea", "Waiter: Ordered Tea", "Manager: Ordered Tea"), notifications);
  }

  @Test
  @DisplayName("Registered observers should receive every subsequent notification")
  void testRepeatedNotifications() {
    OrderSubject order = new OrderSubject();
    List<String> notifications = new ArrayList<>();
    order.addObserver(status -> notifications.add("Barista: " + status));
    order.addObserver(status -> notifications.add("Waiter: " + status));

    order.notifyAll("Ordered Coffee");
    order.notifyAll("Ordered Tea");

    assertEquals(List.of(
      "Barista: Ordered Coffee",
      "Waiter: Ordered Coffee",
      "Barista: Ordered Tea",
      "Waiter: Ordered Tea"
    ), notifications);
  }

  @Test
  @DisplayName("Null observers should be ignored without affecting registered observers")
  void testNullObserverIsIgnored() {
    OrderSubject order = new OrderSubject();
    List<String> notifications = new ArrayList<>();
    order.addObserver(status -> notifications.add("Barista: " + status));
    order.addObserver(null);
    order.addObserver(status -> notifications.add("Waiter: " + status));

    order.notifyAll("Ordered Coffee");

    assertEquals(List.of("Barista: Ordered Coffee", "Waiter: Ordered Coffee"), notifications);
  }

}
