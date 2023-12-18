package PenerapanBAB3;

public class Lingkaran {
   //atribut 
    int r;
    double phi , luas;
    
    //constructor
    public Lingkaran() {
        this.r = 7;
        this.phi =3.14;
    }
    //method 
    void Deskripsi(){
        System.out.println("Ini Adalah HAsil Perhitungan");
        
    }
    //method untuk menghitung luas
    double HitLuasLingkaran(){
        luas=(phi*r*r);
        return luas;
    }
}
