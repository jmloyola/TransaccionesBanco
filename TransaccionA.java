import java.io.*;
import java.util.*;
import java.util.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class TransaccionA {

    private TransaccionA() {}

    public static void main(String[] args) {
	try {
	    
	    Registry registry = LocateRegistry.getRegistry("localhost",3001);
	    InterfazServer stub = (InterfazServer) registry.lookup("Server");
		
		System.out.println("Transaccion A.");
		System.out.println();
		
	    	 		
	} catch (Exception e) {
	    System.err.println("Excepcion en transaccion A: " + e.toString());
	    e.printStackTrace();
	}
    }
}
