import sun.rmi.registry.RegistryImpl_Skel;

import java.io.ObjectInputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class UserServer {
    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.createRegistry(2222);
        User user = new UserImpl();
        registry.rebind("HelloRegistry", user);
        System.out.println("rmi start at 2222");
//        ObjectInputStream
//        RegistryImpl_Skel
    }
}
