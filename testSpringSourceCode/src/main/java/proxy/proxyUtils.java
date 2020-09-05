package proxy;

import org.junit.Test;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class proxyUtils {

    public static Object proxy(Object target) {
        String line = "\n";
        String tab = "\t";
        int i = 0;
        String content = "";
        Method[] methods = target.getClass().getDeclaredMethods();

        String packageName = target.getClass().getInterfaces()[0].getName();
        String targetName = target.getClass().getInterfaces()[0].getSimpleName();
        String packageContent = "package com.zzh;"+line;
        String importContent = "import " + packageName + ";" + line;
        String classContent = "public class myProxy implements " + targetName;
        String methodContent = "";
        String constructContent = line + tab + "public myProxy" + "(" + targetName + " target" + "){" + line +
                tab+"this.target = target;" + line +tab+ "}" + line;
        for (Method method : methods) {
            String methodName = method.getName();
            String argsContent = "";
            String returnArgs = "";
            Class<?>[] args = method.getParameterTypes();
            String returnType = method.getReturnType().getSimpleName();
            for (Class arg : args) {
                argsContent += arg.getSimpleName() + " p" + i + ",";
                returnArgs+="p"+i+",";
                i++;
            }

            if (argsContent.length() > 0) {
                argsContent = argsContent.substring(0, argsContent.lastIndexOf(",") - 1);
                returnArgs = returnArgs.substring(0, returnArgs.lastIndexOf(",") - 1);
            }
//                methodContent += line+tab+tab+"public " +returnType+" "+ method.getName()+"()"
//                        +"{"
//                        +line+tab+tab+"System.out.println(\"log\");"
//                        +line+tab+tab+"return "+target+"."+methodName+"()"+line+"}";
            methodContent += line  + tab + "public " + returnType + " " + method.getName() + "(" + argsContent + ")"
                    + "{"
                    + line + tab + "System.out.println(\"log\");"
                    + line + tab;

            if(!returnType.equals("void")){
                methodContent += "return target." + methodName + "(" + returnArgs + ");" + line +tab +"}";
            }else {
                methodContent += "target." + methodName + "(" + returnArgs + ");" + line +tab +"}";
            }

            i++;
        }
        content = packageContent + importContent + classContent + "{" + line +tab+"private "+targetName+" target;"+ constructContent + methodContent + line + "}";
        Object myProxy = null;

        try {
            File file = new File("D:\\com\\zzh\\myProxy.java");

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(null,null,null);
            Iterable units = fileManager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = javaCompiler.getTask(null, fileManager, null, null, null, units);
            task.call();
            fileManager.close();
            URL[] urls = new URL[]{new URL("file:D:\\\\")};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class<?> aClass = urlClassLoader.loadClass("com.zzh.myProxy");
            Constructor<?> constructor = aClass.getConstructor(target.getClass().getInterfaces()[0]);
             myProxy = constructor.newInstance(target);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return myProxy;
    }

    @Test
    public void test() {
        a proxy = (a)proxy(new c());
        proxy.say();
    }
}
