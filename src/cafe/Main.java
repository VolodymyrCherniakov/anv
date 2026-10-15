import cafe.drinks.config.CafeConfig;
import cafe.Tea;
import cafe.drinks.Drink;
import cafe.drinks.Coffee;
import cafe.drinks.factory.DrinkFactory;
import cafe.drinks.CustomDrink;

void main() {

  Drink coffee = new Coffee();
  IO.println(coffee.serve());

  Drink tea = new Tea();
  IO.println(tea.serve());

  //factory

  IO.println(DrinkFactory.createDrink("tea").serve() + " in " + CafeConfig.getInstance().getCafeName());
  IO.println(DrinkFactory.createDrink("coffee").serve() + " in " + CafeConfig.getInstance().getCafeName());

  //throwing exception
  //IO.println(DrinkFactory.createDrink("beer").serve() + " in " + cafe.drinks.config.CafeConfig.getInstance().getCafeName());

  //builder
  CustomDrink customDrink = new CustomDrink.Builder("coffee").milk().sugar().build();
  System.out.println(customDrink + " in " + CafeConfig.getInstance().getCafeName());

}

