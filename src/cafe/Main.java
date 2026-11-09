import cafe.drinks.config.CafeConfig;
import cafe.drinks.Tea;
import cafe.drinks.Drink;
import cafe.drinks.Coffee;
import cafe.drinks.factory.DrinkFactory;
import cafe.drinks.CustomDrink;
import cafe.observers.EmployeeObserver;
import cafe.orders.OrderSubject;
import cafe.payment.CardPayment;
import cafe.payment.CashPayment;
import cafe.payment.Checkout;

void main() {
  // observer
  EmployeeObserver waiter = new EmployeeObserver("Waiter");

  OrderSubject order = new OrderSubject();
  order.addObserver(new EmployeeObserver("Barista"));
  order.addObserver(waiter);

  // factory
  System.out.println(Drink.ORDER_PREFIX + " " + DrinkFactory.createDrink("tea").serve() + " in " + CafeConfig.getInstance().getCafeName());
  System.out.println(Drink.ORDER_PREFIX + " " + DrinkFactory.createDrink("coffee").serve() + " in " + CafeConfig.getInstance().getCafeName());

  // throwing exception
  //System.out.println(Drink.ORDER_PREFIX + " " + DrinkFactory.createDrink("beer").serve() + " in " + CafeConfig.getInstance().getCafeName());

  // builder
  CustomDrink customDrink = new CustomDrink.Builder("coffee").milk().sugar().build();
  String      msg         = customDrink + " in " + CafeConfig.getInstance().getCafeName();
  order.notifyAll(msg);

  // strategy
  OrderSubject payment = new OrderSubject();
  payment.addObserver(waiter);

  Checkout checkout = new Checkout(new CardPayment());
  payment.notifyAll(checkout.processPayment(150, 4));

  checkout.setStrategy(new CashPayment());
  payment.notifyAll(checkout.processPayment(300, 2));

}
