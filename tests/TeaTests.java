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
    assertSame("Serving tea", tea.serve());
  }

  @Test
  @DisplayName("serve() drink instance")
  void testServeDrink() {
    Drink tea = new Tea();
    assertSame("Serving tea", tea.serve());
  }

}