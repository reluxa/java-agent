package org.reluxa;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class DateTransformer implements ClassFileTransformer {
  @Override

  public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
    System.out.println("Transform was called: " + className);
    if ("java/util/Date".equals(className)) {
      return doClass(className, classBeingRedefined, classfileBuffer);
    }
    return classfileBuffer;
  }

  private byte[] doClass(String className, Class<?> classBeingRedefined, byte[] classfileBuffer) {
    ClassPool classPool = ClassPool.getDefault();
    try {
      CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
      CtConstructor[] ctrs = ctClass.getConstructors();
      for (CtConstructor ctr : ctrs) {
        if (ctr.getParameterTypes().length == 0) {
          ctClass.removeConstructor(ctr);
        }
      }
      CtConstructor ctr = CtNewConstructor.defaultConstructor(ctClass);
      ctr.setBody("this(392011200000l);");
      ctClass.addConstructor(ctr);
      return ctClass.toBytecode();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return classfileBuffer;
  }


}
