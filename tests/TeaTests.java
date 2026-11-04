import cafe.drinks.Tea;
import cafe.drinks.Drink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class TeaTests {

  @Test
  @DisplayName("serve() tea instance")
  void testServeTea() {
    Tea tea = new Tea();
    assertSame("Tea", tea.serve());
  }

  @Test
  @DisplayName("serve() drink instance")
  void testServeDrink() {
    Drink tea = new Tea();
    assertSame("Tea", tea.serve());
  }

}