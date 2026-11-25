package cafe.drinks.decorator;

import cafe.drinks.CustomDrink;

public abstract class CustomDrinkDecorator extends CustomDrink {

  protected CustomDrink customDrink = null;

  protected CustomDrinkDecorator(CustomDrink customDrink) {
    super();
    this.customDrink = customDrink;
  }

  @Override
  public String toString() {
    return customDrink.toString();
  }

}
