package org.reluxa;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.Date;

import static java.lang.String.format;

public class AgentMain {

  public static void agentmain(String agentArgs, Instrumentation inst) throws UnmodifiableClassException {
    System.out.println(format("agentmain was called: %s, %s", agentArgs, inst));
    inst.addTransformer(new DateTransformer(), true);
    inst.retransformClasses(Date.class);
  }


}
