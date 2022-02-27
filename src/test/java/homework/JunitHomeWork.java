package homework;

import org.junit.jupiter.api.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JunitHomeWork {
    static Class clazz;

    public static void main(String[] args) throws Exception {

        clazz = HomeWorkSimpleTest.class;

        List<Method> testMethods = new ArrayList<>();
        List<Method> beforeAllMethods = new ArrayList<>();
        List<Method> beforeEachMethods = new ArrayList<>();
        List<Method> afterAllMethods = new ArrayList<>();
        List<Method> afterEachMethods = new ArrayList<>();


        for (Method method : clazz.getDeclaredMethods()) {
            Test testMethodAnnotation = method.getAnnotation(Test.class);
            if (testMethodAnnotation != null) {
                testMethods.add(method);
            }

            BeforeAll beforeAllMethodAnnotation = method.getAnnotation(BeforeAll.class);
            if (beforeAllMethodAnnotation != null) {
                beforeAllMethods.add(method);
            }

            BeforeEach beforeEachMethodAnnotation = method.getAnnotation(BeforeEach.class);
            if (beforeEachMethodAnnotation != null) {
                beforeEachMethods.add(method);
            }

            AfterAll afterAllMethodAnnotation = method.getAnnotation(AfterAll.class);
            if (afterAllMethodAnnotation != null) {
                afterAllMethods.add(method);
            }

            AfterEach afterEachMethodAnnotation = method.getAnnotation(AfterEach.class);
            if (afterEachMethodAnnotation != null) {
                afterEachMethods.add(method);
            }
        }

        //Выполнение методов BeforeAll
        callMethods(beforeAllMethods);


        for (Method m : testMethods) {
            //Выполнение методов BeforeEach
            callMethods(beforeEachMethods);

            //Выполнение методов Test
            try {
                m.invoke(clazz.getConstructor().newInstance());
            } catch (InvocationTargetException e) {
                if (e.getCause() instanceof AssertionError) {
                    System.out.println("        Test failed: " + m.getName());
                    //Выполнение методов AfterEach
                    callMethods(afterEachMethods);
                } else {
                    System.out.println("        Test broken: " + m.getName());
                    //Выполнение методов AfterEach
                    callMethods(afterEachMethods);
                }
                continue;
            }
            System.out.println("        Test passed: " + m.getName());
            //Выполнение методов AfterEach
            callMethods(afterEachMethods);
        }

        //Выполнение методов AfterAll
        callMethods(afterAllMethods);
    }

    //Функция которая выполняет список переданных в нее методов
    static void callMethods(List<Method> list) throws Exception {
        for (Method am : list) {
            try {
                am.invoke(clazz.getConstructor().newInstance());
            } catch (InvocationTargetException e) {
                if (e.getCause() instanceof AssertionError) {
                    System.out.println("JunitCore Error in: " + am.getName());
                } else {
                    System.out.println("JunitCore Error in: " + am.getName());
                }
            }
        }
    }
}

