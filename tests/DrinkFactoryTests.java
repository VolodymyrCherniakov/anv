import cafe.drinks.Coffee;
import cafe.drinks.Drink;
import cafe.drinks.Tea;
import cafe.drinks.factory.DrinkFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrinkFactoryTests {

  @Test
  @DisplayName("createDrink() should return tea")
  void testCreateDrinkTea() {
    Drink tea = DrinkFactory.createDrink("tea");
    assertInstanceOf(Tea.class, tea);
  }

  @Test
  @DisplayName("createDrink() should return coffee")
  void testCreateDrinkCoffee() {
    Drink coffee = DrinkFactory.createDrink("coffee");
    assertInstanceOf(Coffee.class, coffee);
  }

  @Test
  @DisplayName("createDrink() should throw exception for unknown drink type")
  void testCreateDrinkUnknownDrinkError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      DrinkFactory.createDrink("vodka");
    });
    assertEquals("Unknown drink type: vodka", exception.getMessage());
  }

  @Test
  @DisplayName("createDrink() should throw exception when drink type is null")
  void testCreateDrinkNullError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      DrinkFactory.createDrink(null);
    });
    assertEquals("Drink type cannot be null", exception.getMessage());
  }

}