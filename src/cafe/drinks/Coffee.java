package cafe.drinks;

import cafe.Drink;

public class Coffee implements Drink {

  public Coffee() {
  }

  @Override
  public String serve() {
    return "Serving coffee";
  }

}
