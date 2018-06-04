package com.yueshen.testmodel;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestReflection {

    @Test
    public void main() {

    }

    public void Test1() {
        TestBeanClass testBeanClass = new TestBeanClass();

        Class cls = testBeanClass.getClass();

        //获取类的属性
        System.out.println("获取类的属性");
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Modifier.toString(f.getModifiers()) + " ");// 获得属性的修饰符，例如public，static等等
            stringBuilder.append(f.getType().getSimpleName() + " ");// 属性的类型的名字
            stringBuilder.append(f.getName());// 属性的名字+回车

            System.out.println(stringBuilder.toString());
        }

        System.out.println("获取类的方法");

//        Method[] methods = cls.getMethods();//获取全部方法，包括父类
        Method[] methods = cls.getDeclaredMethods();//获取自己类的全部方法
        for (Method m : methods) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Modifier.toString(m.getModifiers()) + " ");
            stringBuilder.append(m.getReturnType().getSimpleName() + " ");
            int paramLength = m.getParameterTypes().length;
            if (paramLength > 0) {
                Class[] classes = m.getParameterTypes();
                stringBuilder.append("(");
                for (Class c : classes) {

                }
                stringBuilder.append(")");
            }
            stringBuilder.append(m.getName());
            System.out.println(stringBuilder.toString());
        }
    }
}
