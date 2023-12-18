package PenerapanBAB4;

public class pembayaranGaji {
    private String nomerRekening;
    private String input;
    private double saldo;
    
    public pembayaranGaji(){
        this.nomerRekening = "G1234";
        this.saldo = 1000000;
    }
    public String getNomerRekening(){
        return nomerRekening;
    }
    public void setNomerRekening(){
        this.nomerRekening=nomerRekening;
    }
    public double getSaldo(){
        return saldo;
    }
    public void setSaldo(double saldo){
        this.saldo=saldo;
    }
    public String getInput(){
        return input;
    }
    public void setInput(String input){
        this.input=input;
    }
    public double cekSaldo(){
        if(getNomerRekening().equals(getInput())){
            return getSaldo();
        }else{
            return 0;
        }
    }
    
}
