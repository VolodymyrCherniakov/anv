package cafe.drinks.decorator;

import cafe.drinks.CustomDrink;

public class ToGoDecorator extends CustomDrinkDecorator {

  public ToGoDecorator(CustomDrink customDrink) {
    super(customDrink);
  }

  @Override
  public String toString() {
    return super.toString() + " [to-go]";
  }

}
