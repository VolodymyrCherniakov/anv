package drinks;

//simple factory
public class DrinkFactory {

  private DrinkFactory() {
  }

  public static Drink createDrink(String type) {
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
