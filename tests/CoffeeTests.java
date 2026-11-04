import cafe.drinks.Coffee;
import cafe.drinks.Drink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class CoffeeTests {

  @Test
  @DisplayName("serve() coffee instance")
  void testServeCoffee() {
    Coffee coffee = new Coffee();
    assertSame("Serving coffee", coffee.serve());
  }

  @Test
  @DisplayName("serve() drink instance")
  void testServeDrink() {
    Drink coffee = new Coffee();
    assertSame("Serving coffee", coffee.serve());
  }

}