import drinks.Drink;
import drinks.Coffee;
import drinks.DrinkFactory;
import drinks.Tea;

void main() {

  Drink coffee = new Coffee();
  IO.println(coffee.serve());

  Drink tea = new Tea();
  IO.println(tea.serve());

  //factory

  IO.println(DrinkFactory.createDrink("tea").serve() + " in " + CafeConfig.getInstance().getCafeName());
  IO.println(DrinkFactory.createDrink("coffee").serve() + " in " + CafeConfig.getInstance().getCafeName());

  //throwing exception
  //IO.println(DrinkFactory.createDrink("beer").serve() + " in " + CafeConfig.getInstance().getCafeName());
}

