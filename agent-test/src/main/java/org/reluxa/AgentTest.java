package org.reluxa;

import java.util.Date;

/**
 * Created by reluxa on 6/7/2015.
 */
public class AgentTest implements Runnable {

  public static void main(String[] args) {
    new Thread(new AgentTest()).start();
  }

  public void run() {
    while (true) {
      System.out.println(new Date());
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }


}
