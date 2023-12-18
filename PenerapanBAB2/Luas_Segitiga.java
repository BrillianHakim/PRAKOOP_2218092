package PenerapanBAB2;

public class Luas_Segitiga {
    int alas, tinggi;
    double luas;
    //menginisialisasi nilai alas dengan construktor
    public Luas_Segitiga(){
        this.alas = 10;
    }
    //method perhitungan
    public double Luas(){
        luas=(alas*tinggi)/2;
        return luas;
    }
}
