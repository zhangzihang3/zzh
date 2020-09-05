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

/**
 * @author 张帅
 * @class jdk代理实现
 */
public class proxyUtilsPlus {
    /**
     * @param target被代理的对象 myHandler被代理的逻辑
     * @method
     */
    public static Object proxy(Class target, myHadlerI myHandler) {
        String line = "\n";
        String tab = "\t";
        int i = 0;
        String content = "";

        String packageName = target.getName();
        String targetName = target.getSimpleName();
        String packageContent = "package com.zzh;" + line;
        String importContent = "import " + packageName + ";" + line
                + "import " + myHandler.getClass().getName() + ";" + line
                + "import java.lang.reflect.Method;" + line;
        String classContent = "public class myProxy implements " + targetName;
        String methodContent = "";
        String constructContent = line + tab + "public myProxy" + "(" + "myHandler target" + "){" + line +
                tab + "this.h = target;" + line + tab + "}" + line;
        String myHandlerName = proxy.myHandler.class.getSimpleName();
        Method[] methods = target.getDeclaredMethods();
        for (Method method : methods) {
            //方法的参数部分
            String argsContent = "";
            //方法的返回值
            String returnArgs = "";
            //获取代理方法的所有参数
            Class<?>[] args = method.getParameterTypes();
            //获取代理方法的返回值
            String returnType = method.getReturnType().getSimpleName();
            //遍历参数String p0 , String p1 这样拼接
            for (Class arg : args) {
                argsContent += arg.getSimpleName() + " p" + i + ",";

                returnArgs += "p" + i + ",";
                i++;
            }
            if (argsContent.length() > 0) {
                //把最后多余的逗号去掉
                argsContent = argsContent.substring(0, argsContent.lastIndexOf(",") - 1);
                returnArgs = returnArgs.substring(0, returnArgs.lastIndexOf(",") - 1);
            }
            if (!returnType.equals("void")) {
                methodContent += line + tab + "public " + returnType + " " + method.getName() + "(" + argsContent + ")"
                        + "{" + line + tab + "try{" + line + "Method method = Class.forName(" + "\"" + target.getName() + "\"" + ").getDeclaredMethod(" + "\"" + method.getName() + "\"" + ");"
                        + line + line + tab + "return (String)h.invoke(method);"
                        + line + tab + "}"
                        + line + "catch (Exception e) {\n" +
                        "            e.printStackTrace();\n" +
                        "        }" + line + "return null;" + line + tab + "}";
            } else {
                methodContent += line + tab + "public " + returnType + " " + method.getName() + "(" + argsContent + ")"
                        + "{" + line + tab + "try{" + line + "Method method = Class.forName(" + "\"" + target.getName() + "\"" + ").getDeclaredMethod(" + "\"" + method.getName() + "\"" + ");"
                        + line + line + tab + "(String)h.invoke(method);"
                        + line + tab + "}"
                        + line + "catch (Exception e) {\n" +
                        "            e.printStackTrace();\n" +
                        "        }" + line + "return null;" + line + tab + "}";
            }


            i++;
        }
        content = packageContent + importContent + classContent + "{" + line + tab + constructContent
                + line + tab + "private " + myHandlerName + " h;" + methodContent + line + "}";
        Object myProxy = null;

        try {
            File file = new File("D:\\com\\zzh\\myProxy.java");

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
            //生成class文件
            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(null, null, null);
            Iterable units = fileManager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = javaCompiler.getTask(null, fileManager, null, null, null, units);
            task.call();
            fileManager.close();
            //加载class文件
            URL[] urls = new URL[]{new URL("file:D:\\\\")};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class<?> aClass = urlClassLoader.loadClass("com.zzh.myProxy");
            Constructor<?> constructor = aClass.getConstructor(myHandler.getClass());
            myProxy = constructor.newInstance(myHandler);

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
        a proxy = (a) proxy(a.class, new myHandler(new c()));
        System.out.println("method return: " + proxy.say());

    }
}
