import java.rmi.RemoteException;
import java.io.Serializable;

public interface InterfazArticuloVenta extends Serializable {
    
    public void imprimir();		
	
	public int getIdentificadorCuenta();
	
	public void setIdentificadorCuenta();
	
	public int getSaldoCuenta();
	
	public void setSaldoCuenta();
}