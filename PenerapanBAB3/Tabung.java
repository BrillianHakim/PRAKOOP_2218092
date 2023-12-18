
package PenerapanBAB3;

public class Tabung extends Lingkaran{
    int t;
    double volume, LuasPermukaan;

    public Tabung(){
        t=20;
    }
    void Keterangan(){
        Deskripsi();
        System.out.println("Menghitung Volume Tabung");
    }
    
    double HitvolumeTabung(){
        volume = ((phi * r*r)*t);
        return volume;
    }

}