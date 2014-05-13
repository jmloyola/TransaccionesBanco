import java.io.*;
import java.util.*;
import java.util.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SolapamientoC {

    private SolapamientoC() {}

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
		System.out.println("Las operaciones de las transacciones se ejecutan de manera concurrente, pero existe un problema de recuperaciones inconsistentes");
		System.out.println();
		System.out.println("\tV\t\t\tW");
		System.out.println();
		System.out.println("extraer(1,100);");
		System.out.println("\t\t\tY=consultarSaldo(1);");
		System.out.println("\t\t\tY=Y+consultarSaldo(2);");		
		System.out.println("\t\t\tY=Y+consultarSaldo(3);");		
		System.out.println("\t\t\tY=Y+consultarSaldo(4);");		
		System.out.println("\t\t\tY=Y+consultarSaldo(5);");		
		System.out.println("X=consultarSaldo(2);");
		System.out.println("depositar(2,X+100);");
		System.out.println();
		System.out.println();
		
		stub.extraer(1,100);

		int y = stub.consultarSaldo(1);		
		y += stub.consultarSaldo(2);
		y += stub.consultarSaldo(3);
		y += stub.consultarSaldo(4);
		y += stub.consultarSaldo(5);		
		
		System.out.println("Dinero total en el banco (dentro de la transaccion W): " + y);
		System.out.println();
		System.out.println();
		
		int x = stub.consultarSaldo(2);
		stub.depositar(2,x+100);		
		
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
