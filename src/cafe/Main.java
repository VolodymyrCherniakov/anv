import cafe.drinks.config.CafeConfig;
import cafe.drinks.Tea;
import cafe.drinks.Drink;
import cafe.drinks.Coffee;
import cafe.drinks.factory.DrinkFactory;
import cafe.drinks.CustomDrink;
import cafe.observers.EmployeeObserver;
import cafe.orders.OrderSubject;

void main() {

  OrderSubject order = new OrderSubject();
  order.addObserver(new EmployeeObserver("Barista"));
  order.addObserver(new EmployeeObserver("Waiter"));

  // factory
  System.out.println(Drink.ORDER_PREFIX + " " + DrinkFactory.createDrink("tea").serve() + " in " + CafeConfig.getInstance().getCafeName());
  System.out.println(Drink.ORDER_PREFIX + " " + DrinkFactory.createDrink("coffee").serve() + " in " + CafeConfig.getInstance().getCafeName());

  // throwing exception
  //System.out.println(Drink.ORDER_PREFIX + " " + DrinkFactory.createDrink("beer").serve() + " in " + CafeConfig.getInstance().getCafeName());

  // builder
  CustomDrink customDrink = new CustomDrink.Builder("coffee").milk().sugar().build();
  String      msg         = customDrink + " in " + CafeConfig.getInstance().getCafeName();
  order.notifyAll(msg);

}
