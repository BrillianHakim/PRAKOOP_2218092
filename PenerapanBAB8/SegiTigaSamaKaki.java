
package PenerapanBAB8;

public class SegiTigaSamaKaki extends BangunRuang implements Keliling{
    public double alas,tinggi;
    public double a,b,c;
    @Override
    double hitungluas() {
        return ((alas*tinggi)/2);
    }

    @Override
    void tampilHasil() {
        System.out.println("Luas Segitiga :"+hitungluas());
        System.out.println("Keliling Segitiga:"+hitungKeliling());
    }

    @Override
    public double hitungKeliling() {
        return (a+b+c);
    }
    
}
