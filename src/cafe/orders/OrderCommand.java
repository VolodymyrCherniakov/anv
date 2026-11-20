package cafe.orders;

public class OrderCommand implements Command {

  private OrderSubject order = null;
  private String       msg   = "";

  public OrderCommand(OrderSubject orderSubject, String msg) {
    this.order = orderSubject;
    this.msg = msg;
  }

  @Override
  public void execute() {
    this.order.notifyAll(msg);
  }

}
