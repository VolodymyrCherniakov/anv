package cafe.drinks;

import cafe.drinks.factory.DrinkFactory;

public class CustomDrink implements cafe.drinks.Drink {

  private String  base;
  private boolean sugar;
  private boolean milk;
  private boolean caramel;

  private CustomDrink(Builder builder) {
    this.base = builder.base;
    this.sugar = builder.sugar;
    this.milk = builder.milk;
    this.caramel = builder.caramel;
  }
  
  @Override
  public String toString() {
    String        base = baseName();
    StringBuilder sb   = new StringBuilder(ORDER_PREFIX).append(" ").append(base);
    if (milk) {
      sb.append(", milk");
    }
    if (sugar) {
      sb.append(", sugar");
    }
    if (caramel) {
      sb.append(", caramel");
    }
    return sb.toString();
  }

  private String baseName() {
    return DrinkFactory.createDrink(base).serve();
  }

  @Override
  public String serve() {
    return toString();
  }

  public static class Builder {

    private String  base;
    private boolean sugar;
    private boolean milk;
    private boolean caramel;

    public Builder(String drink) {
      this.base = drink;
    }

    public Builder milk() {
      this.milk = true;
      return this;
    }

    public Builder caramel() {
      this.caramel = true;
      return this;
    }

    public Builder sugar() {
      this.sugar = true;
      return this;
    }

    public CustomDrink build() {
      return new CustomDrink(this);
    }

  }

}