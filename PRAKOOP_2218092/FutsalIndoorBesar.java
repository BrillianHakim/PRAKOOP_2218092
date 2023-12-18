package PRAKOOP_2218092;

public class FutsalIndoorBesar extends LapanganFutsal{
private int bayar;
//construck
public FutsalIndoorBesar(){
    hargaPerJam = 150000;
    pilihLapangan = "Futsal Besar";
}
//setter and getter
    public int getLamaWaktu() {
        return lamaWaktu;
    }

    public void setLamaWaktu(int lamaWaktu) {
        this.lamaWaktu = lamaWaktu;
    }
@Override
    public String getPilihLapangan() {
        return pilihLapangan;
    }

@Override
    public void setPilihLapangan(String pilihLapangan) {
        this.pilihLapangan = pilihLapangan;
    }
    int Bayar(){
        bayar = hargaPerJam * lamaWaktu;
        return bayar;
    }
    //Overloading
    public int membayar(int bayIr){
        bayar=bayIr-Bayar();
        return bayar;
    }
    public double membayar(int bayar,int bayar1){
        return bayar-hargaPerJam;
    }
    
    //Overriding
    String cekNamaLapangan() {
        return "Lapangan Futsal Ukuran Besar";
    }
    @Override
    public String Status(){
    return "Lapangan Tersedia";
    }
    @Override
    public String Status(String Nomer){
    return "Lapangan Tidak Tersedia";
    }
//Override dari kelas LapanganFutsal
    @Override
    public String LoketByr(){
        return "Loket B";
    }    
    
}