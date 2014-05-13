import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
	
public class Server implements InterfazServer {

	private Cuenta cuentas[];
      
    public Server() throws java.rmi.RemoteException{
		cuentas = new Cuenta[5];
		cuentas[0] = new Cuenta(1,200);
		cuentas[1] = new Cuenta(2,50);
		cuentas[2] = new Cuenta(3,150);
		cuentas[3] = new Cuenta(4,300);
		cuentas[4] = new Cuenta(5,175);
	}
	
	private Cuenta buscarCuenta(int identificadorCuenta){
		for (int i=0; i<cuentas.length; i++){
			if (cuentas[i].getIdentificadorCuenta() == identificadorCuenta){
				return cuentas[i];
			}
		}
		return null; // no encontro dicha cuenta
	}
	
	private int darPosicionCuenta(int identificadorCuenta){
		for (int i=0; i<cuentas.length; i++){
			if (cuentas[i].getIdentificadorCuenta() == identificadorCuenta){
				return i;
			}
		}
		return -1; // no encontro dicha cuenta
	}

    public void depositar(int identificadorCuenta, int montoDepositar) throws java.rmi.RemoteException{
		System.out.println("Comienza deposito de monto en la cuenta " + identificadorCuenta + " ...");
		
		int posicionCuenta = darPosicionCuenta(identificadorCuenta);
		
		if (posicionCuenta != -1){
			// Modificado para poder ver los problemas con los solapamientos de transacciones.
			/*int saldoCuenta = cuentas[posicionCuenta].getSaldoCuenta();
			saldoCuenta += montoDepositar;
			cuentas[posicionCuenta].setSaldoCuenta(saldoCuenta);*/
			cuentas[posicionCuenta].setSaldoCuenta(montoDepositar);
		}
		else{
			System.out.println("ERROR");
			System.out.println();
		}
					
    }
    
    public void extraer(int identificadorCuenta, int montoExtraer) throws java.rmi.RemoteException{
		System.out.println("Comienza extracion de monto de la cuenta " + identificadorCuenta + " ...");		        
		
		int posicionCuenta = darPosicionCuenta(identificadorCuenta);
		
		if (posicionCuenta != -1){
			int saldoCuenta = cuentas[posicionCuenta].getSaldoCuenta();
			saldoCuenta -= montoExtraer;
			cuentas[posicionCuenta].setSaldoCuenta(saldoCuenta);
		}
		else{
			System.out.println("ERROR");
			System.out.println();
		}
    }
    
    public int consultarSaldo(int identificadorCuenta)throws java.rmi.RemoteException{
        System.out.println("Consultando saldo de cuenta " + identificadorCuenta + " ...");
		
		int posicionCuenta = darPosicionCuenta(identificadorCuenta);
		
		if (posicionCuenta != -1){
			return cuentas[posicionCuenta].getSaldoCuenta();
		}
		else{
			System.out.println("ERROR");
			System.out.println();
			return -1;
		}
    }
	
	public int saldoTotal(){
		int total = 0;
		for (int i=0; i<cuentas.length; i++){
			total += cuentas[i].getSaldoCuenta();
		}
		return total;
	}
	
    public static void main(String args[]) {

		try {		
			Server obj = new Server();
			InterfazServer stub = (InterfazServer) UnicastRemoteObject.exportObject(obj, 0);

			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry("localhost",3001);
			registry.bind("Server", stub);

			System.err.println("SERVIDOR LISTO Y ESPERANDO ...");
		} catch (Exception e) {
			System.err.println("Server exception ERROR: " + e.toString());
			e.printStackTrace();
		}
    }
}
