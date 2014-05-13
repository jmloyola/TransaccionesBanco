import java.io.*;
import java.util.*;
import java.util.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SolapamientoB {

    private SolapamientoB() {}

    public static void main(String[] args) {
	try {
	    
	    Registry registry = LocateRegistry.getRegistry("localhost",3001);
	    InterfazServer stub = (InterfazServer) registry.lookup("Server");
		
		System.out.println("Valores iniciales de las cuentas '1', '2', y '3':");
		System.out.println("Cuenta 1 ----> " + stub.consultarSaldo(1));
		System.out.println("Cuenta 2 ----> " + stub.consultarSaldo(2));
		System.out.println("Cuenta 3 ----> " + stub.consultarSaldo(3));
		System.out.println();
		System.out.println("Dinero total en el banco: " + stub.saldoTotal());
		System.out.println();
		
		
		System.out.println("Solapamiento B.");
		System.out.println("Las operaciones de las transacciones se ejecutan de manera concurrente, pero existe un problema de actualizaciones perdidas");
		System.out.println();
		System.out.println("\tT\t\t\tU");
		System.out.println();
		System.out.println("X=consultarSaldo(1);");
		System.out.println("\t\t\tY=consultarSaldo(1);");
		System.out.println("\t\t\tdepositar(1,Y*1.1);");		
		System.out.println("depositar(1,X*1.1);");
		System.out.println("extraer(2,X/10);");
		System.out.println("\t\t\textraer(3,Y/10);");
		System.out.println();
		System.out.println();
		
		int x = stub.consultarSaldo(1);

		int y = stub.consultarSaldo(1);
		stub.depositar(1,(int)(y*1.1));
		
		stub.depositar(1,(int)(x*1.1));
		stub.extraer(2,(int)(x/10));
		
		stub.extraer(3,(int)(y/10));
		
		System.out.println("Valores con los que resultan las cuentas '1', '2', y '3':");
		System.out.println("Cuenta 1 ----> " + stub.consultarSaldo(1));
		System.out.println("Cuenta 2 ----> " + stub.consultarSaldo(2));
		System.out.println("Cuenta 3 ----> " + stub.consultarSaldo(3));
		System.out.println();
		System.out.println("Dinero total en el banco: " + stub.saldoTotal());
		
		
	    	 		
	} catch (Exception e) {
	    System.err.println("Excepcion en transaccion A: " + e.toString());
	    e.printStackTrace();
	}
    }
}
