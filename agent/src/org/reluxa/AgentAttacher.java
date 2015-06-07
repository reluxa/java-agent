package org.reluxa;

import com.sun.tools.attach.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AgentAttacher {

  public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("The list of running VMs:\n\n");

    for (VirtualMachineDescriptor vmd : VirtualMachine.list()) {
      System.out.println(vmd);
    }

    System.out.print("Please enter the PID: ");
    String pid = reader.readLine();
    VirtualMachine vm = VirtualMachine.attach(pid);
    vm.loadAgent("D:/agent.jar");

    System.out.print("Press enter to disconnect");
    reader.readLine();
  }

}
