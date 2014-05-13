import java.rmi.RemoteException;
import java.io.Serializable;

public interface InterfazCuenta extends Serializable {
    
    public void imprimir();		
	
	public int getIdentificadorCuenta();
	
	public void setIdentificadorCuenta(int identificadorCuenta);
	
	public int getSaldoCuenta();
	
	public void setSaldoCuenta(int saldoCuenta);
}