package ws.proxy;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public @interface MyProxyInterface {
    public String my();
}


class t{
    public static void main(String[] args) throws Exception {
        MyProxyInterface m=(MyProxyInterface) Proxy.newProxyInstance(t.class.getClassLoader(), new Class[]{MyProxyInterface.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("my proxy");
                return null;
            }
        });
        m.my();

        Constructor declaredConstructor= m.getClass().getDeclaredConstructor(InvocationHandler.class);
        m=(MyProxyInterface)declaredConstructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("my proxy111");
                return null;
            }
        });

        m.my();
        /* System.out.println(m.getClass().getName());
        byte[] b=ProxyGenerator.generateProxyClass(
                "my", new Class[]{MyProxyInterface.class}, 1);
        FileOutputStream a=new FileOutputStream(new File("C:\\Users\\ws\\Desktop\\1.txt"));
        a.write(b);
        a.flush();
        a.close();*/

    }
}