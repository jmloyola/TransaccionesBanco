import java.rmi.RemoteException;



public class Cuenta implements InterfazCuenta {

    private int identificadoCuenta;
    private int saldoCuenta;

    public Cuenta(int identificadoCuenta, int saldoCuenta) {
        this.identificadoCuenta = identificadoCuenta;
        this.saldoCuenta = saldoCuenta;
    }
    
    public int getIdentificadorCuenta() {
        return identificadoCuenta;
    }

    public void setIdentificadorCuenta(int identificadoCuenta) {
        this.identificadoCuenta = identificadoCuenta;
    }

    public int getSaldoCuenta() {
        return saldoCuenta;
    }

    public void setSaldoCuenta(int saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }
       
    public void imprimir(){
	System.out.format("Numero cuenta: %d ---------- Saldo: %d", this.identificadoCuenta, this.saldoCuenta);    
	System.out.println();
    }
	
}