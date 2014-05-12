import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfazServer extends Remote {
    void depositar(int identificadorCuenta, int montoDepositar) throws RemoteException;
    void extraer(int identificadorCuenta, int montoExtraer) throws RemoteException;
    int consultarSaldo(int identificadorCuenta)throws RemoteException;
}
