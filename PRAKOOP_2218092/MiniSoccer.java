package PRAKOOP_2218092;

public class MiniSoccer extends LapanganFutsal{
    private int bayar;
    
//construck
public MiniSoccer(){
    hargaPerJam = 300000;
    pilihLapangan = "Mini Soccer";
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
    //Overriding
    String cekNamaLapangan(){
        return "Lapangan Futsal Ukuran Kecil ";
    }
    @Override
    public String Status(){
    return "Lapangan Tersedia";
    }
    @Override
    public String Status(String Nomer){
    return "Lapangan Penuh";
    }   
    //Override dari kelas LapanganFutsal
        @Override
    public String LoketByr(){
        return "Loket C";
    } 
    //Method Dari Interface
    @Override
    public String jenisLantai() {
        return "Sintetis";
    }
    
}