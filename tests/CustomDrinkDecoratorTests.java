import cafe.drinks.CustomDrink;
import cafe.drinks.decorator.CustomDrinkDecorator;
import cafe.drinks.decorator.OwnMugDecorator;
import cafe.drinks.decorator.ToGoDecorator;
import cafe.orders.Command;
import cafe.orders.OrderCommand;
import cafe.orders.OrderSubject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomDrinkDecoratorTests {

  @Test
  @DisplayName("Base CustomDrinkDecorator should delegate toString() and serve() to the wrapped drink")
  void testBaseDecoratorDelegatesToWrappedDrink() {
    CustomDrink drink = new CustomDrink.Builder("coffee").milk().sugar().build();
    CustomDrinkDecorator passThrough = new CustomDrinkDecorator(drink) {};

    assertEquals("Ordered Coffee, milk, sugar", passThrough.toString());
    assertEquals("Ordered Coffee, milk, sugar", passThrough.serve());
  }

  @Test
  @DisplayName("OwnMugDecorator wrapping ToGoDecorator should append [to-go] then [own mug]")
  void testOwnMugWrappingToGo() {
    CustomDrink coffee = new CustomDrink.Builder("coffee")
      .milk()
      .sugar()
      .build();
    coffee = new OwnMugDecorator(new ToGoDecorator(coffee));

    assertEquals("Ordered Coffee, milk, sugar [to-go] [own mug]", coffee.toString());
    assertEquals("Ordered Coffee, milk, sugar [to-go] [own mug]", coffee.serve());
  }

  @Test
  @DisplayName("ToGoDecorator wrapping OwnMugDecorator should append [own mug] then [to-go]")
  void testToGoWrappingOwnMug() {
    CustomDrink tea = new CustomDrink.Builder("tea")
      .sugar()
      .build();
    tea = new ToGoDecorator(new OwnMugDecorator(tea));

    assertEquals("Ordered Tea, sugar [own mug] [to-go]", tea.toString());
    assertEquals("Ordered Tea, sugar [own mug] [to-go]", tea.serve());
  }

  @Test
  @DisplayName("Decorated drink should produce the expected notification when used in OrderCommand")
  void testDecoratedDrinkWithOrderCommand() {
    OrderSubject order = new OrderSubject();
    List<String> notifications = new ArrayList<>();
    order.addObserver(notifications::add);

    CustomDrink coffee = new CustomDrink.Builder("coffee").milk().sugar().build();
    coffee = new OwnMugDecorator(new ToGoDecorator(coffee));

    Command coffeeOrder = new OrderCommand(order, coffee + " in Smart Café");
    coffeeOrder.execute();

    assertEquals(List.of("Ordered Coffee, milk, sugar [to-go] [own mug] in Smart Café"), notifications);
  }

}
