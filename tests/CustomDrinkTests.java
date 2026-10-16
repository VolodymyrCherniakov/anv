import cafe.drinks.CustomDrink;
import cafe.drinks.Drink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomDrinkTests {

  @Test
  @DisplayName("CustomDrink should implement Drink interface")
  void testCustomDrinkImplementsDrink() {
    CustomDrink drink = new CustomDrink.Builder("coffee").build();
    assertInstanceOf(Drink.class, drink);
  }

  @Test
  @DisplayName("Builder method chaining should return the same Builder instance")
  void testBuilderChaining() {
    CustomDrink.Builder builder = new CustomDrink.Builder("coffee");
    assertSame(builder, builder.milk());
    assertSame(builder, builder.sugar());
    assertSame(builder, builder.caramel());
  }

  @Test
  @DisplayName("CustomDrink with base only should serve base drink")
  void testBaseDrinkOnly() {
    CustomDrink drink = new CustomDrink.Builder("coffee").build();
    assertEquals("Serving coffee with", drink.serve());
    assertEquals("Serving coffee with", drink.toString());
  }

  @Test
  @DisplayName("CustomDrink with milk and sugar")
  void testCustomDrinkWithMilkAndSugar() {
    CustomDrink drink = new CustomDrink.Builder("coffee")
      .milk()
      .sugar()
      .build();
    assertEquals("Serving coffee with milk, sugar", drink.serve());
  }

  @Test
  @DisplayName("CustomDrink with all additions (milk, sugar, caramel)")
  void testCustomDrinkWithAllAdditions() {
    CustomDrink drink = new CustomDrink.Builder("coffee")
      .milk()
      .sugar()
      .caramel()
      .build();
    assertEquals("Serving coffee with milk, sugar, caramel", drink.serve());
  }

  @Test
  @DisplayName("CustomDrink with tea base and milk")
  void testCustomDrinkTeaWithMilk() {
    CustomDrink drink = new CustomDrink.Builder("tea")
      .milk()
      .build();
    assertEquals("Serving tea with milk", drink.serve());
  }

  @Test
  @DisplayName("CustomDrink with invalid base should throw exception on serve")
  void testInvalidBaseThrowsExceptionOnServe() {
    CustomDrink drink = new CustomDrink.Builder("invalid").build();
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, drink::serve);
    assertEquals("Unknown drink type: invalid", exception.getMessage());
  }

}
