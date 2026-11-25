package cafe.drinks.decorator;

import cafe.drinks.CustomDrink;

public class OwnMugDecorator extends CustomDrinkDecorator {

  public OwnMugDecorator(CustomDrink customDrink) {
    super(customDrink);
  }

  @Override
  public String toString() {
    return super.toString() + " [own mug]";
  }

}
