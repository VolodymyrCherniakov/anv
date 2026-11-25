import cafe.drinks.CustomDrink;
import cafe.drinks.Drink;
import cafe.drinks.decorator.CustomDrinkDecorator;
import cafe.drinks.decorator.ToGoDecorator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ToGoDecoratorTests {

  @Test
  @DisplayName("ToGoDecorator should extend CustomDrinkDecorator and be a CustomDrink and Drink")
  void testToGoDecoratorHierarchy() {
    CustomDrink drink = new CustomDrink.Builder("coffee").build();
    ToGoDecorator toGoDrink = new ToGoDecorator(drink);

    assertInstanceOf(CustomDrinkDecorator.class, toGoDrink);
    assertInstanceOf(CustomDrink.class, toGoDrink);
    assertInstanceOf(Drink.class, toGoDrink);
  }

  @Test
  @DisplayName("ToGoDecorator should append [to-go] to a base drink")
  void testToGoBaseDrink() {
    CustomDrink drink = new ToGoDecorator(new CustomDrink.Builder("coffee").build());

    assertEquals("Ordered Coffee [to-go]", drink.toString());
    assertEquals("Ordered Coffee [to-go]", drink.serve());
  }

  @Test
  @DisplayName("ToGoDecorator should preserve additions and append [to-go]")
  void testToGoDrinkWithAdditions() {
    CustomDrink drink = new CustomDrink.Builder("coffee")
      .milk()
      .sugar()
      .caramel()
      .build();
    CustomDrink decorated = new ToGoDecorator(drink);

    assertEquals("Ordered Coffee, milk, sugar, caramel [to-go]", decorated.toString());
    assertEquals("Ordered Coffee, milk, sugar, caramel [to-go]", decorated.serve());
  }

  @Test
  @DisplayName("ToGoDecorator should not modify the original drink")
  void testToGoDoesNotMutateOriginalDrink() {
    CustomDrink original = new CustomDrink.Builder("tea").sugar().build();
    CustomDrink decorated = new ToGoDecorator(original);

    assertEquals("Ordered Tea, sugar", original.toString());
    assertEquals("Ordered Tea, sugar [to-go]", decorated.toString());
  }

}
