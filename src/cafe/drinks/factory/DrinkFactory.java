package cafe.drinks.factory;

import cafe.Tea;
import cafe.drinks.Coffee;

//simple factory
public class DrinkFactory {

  private DrinkFactory() {
  }

  public static cafe.drinks.Drink createDrink(String type) {
    if (type == null) {
      throw new IllegalArgumentException("Drink type cannot be null");
    }

    return switch (type.trim().toLowerCase()) {
      case "tea" -> new Tea();
      case "coffee" -> new Coffee();
      default -> throw new IllegalArgumentException("Unknown drink type: " + type);
    };
  }

}
