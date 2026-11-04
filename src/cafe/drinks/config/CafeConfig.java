package cafe.drinks.config;

public class CafeConfig {

  private static       CafeConfig instance;
  private static final String     CAFE_NAME = "Smart Café";

  private CafeConfig() {
  }

  public static CafeConfig getInstance() {
    if (instance == null) {
      instance = new CafeConfig();
    }
    return instance;
  }

  public String getCafeName() {
    return CAFE_NAME;
  }

}
