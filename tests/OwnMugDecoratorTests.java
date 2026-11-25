import cafe.drinks.CustomDrink;
import cafe.drinks.Drink;
import cafe.drinks.decorator.CustomDrinkDecorator;
import cafe.drinks.decorator.OwnMugDecorator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class OwnMugDecoratorTests {

  @Test
  @DisplayName("OwnMugDecorator should extend CustomDrinkDecorator and be a CustomDrink and Drink")
  void testOwnMugDecoratorHierarchy() {
    CustomDrink drink = new CustomDrink.Builder("tea").build();
    OwnMugDecorator ownMugDrink = new OwnMugDecorator(drink);

    assertInstanceOf(CustomDrinkDecorator.class, ownMugDrink);
    assertInstanceOf(CustomDrink.class, ownMugDrink);
    assertInstanceOf(Drink.class, ownMugDrink);
  }

  @Test
  @DisplayName("OwnMugDecorator should append [own mug] to a base drink")
  void testOwnMugBaseDrink() {
    CustomDrink drink = new OwnMugDecorator(new CustomDrink.Builder("tea").build());

    assertEquals("Ordered Tea [own mug]", drink.toString());
    assertEquals("Ordered Tea [own mug]", drink.serve());
  }

  @Test
  @DisplayName("OwnMugDecorator should preserve additions and append [own mug]")
  void testOwnMugDrinkWithAdditions() {
    CustomDrink drink = new CustomDrink.Builder("tea")
      .sugar()
      .build();
    CustomDrink decorated = new OwnMugDecorator(drink);

    assertEquals("Ordered Tea, sugar [own mug]", decorated.toString());
    assertEquals("Ordered Tea, sugar [own mug]", decorated.serve());
  }

  @Test
  @DisplayName("OwnMugDecorator should not modify the original drink")
  void testOwnMugDoesNotMutateOriginalDrink() {
    CustomDrink original = new CustomDrink.Builder("coffee").milk().build();
    CustomDrink decorated = new OwnMugDecorator(original);

    assertEquals("Ordered Coffee, milk", original.toString());
    assertEquals("Ordered Coffee, milk [own mug]", decorated.toString());
  }

}
