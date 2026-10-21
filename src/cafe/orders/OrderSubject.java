package cafe.orders;

import cafe.observers.Observer;

import java.util.ArrayList;

public class OrderSubject {

  private ArrayList<Observer> observers = new ArrayList<>();

  public void addObserver(Observer observer) {
    if (observer != null) {
      this.observers.add(observer);
    }
  }

  public void notifyAll(String status) {
    for (int i = 0; i < observers.size(); i++) {
      observers.get(i).update(status);
    }
  }

}
