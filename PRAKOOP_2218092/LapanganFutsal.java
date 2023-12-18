package PRAKOOP_2218092;

//Membuat Class  
public class LapanganFutsal  extends ClassAbstrack {   
    //Atribut
    String namaPemesan;
    String nomerPenyewa,tanggalPenyewaan,lama;
    String pembayaran,pilihLapangan, Nomer,nomerlap;
    int lamaWaktu,hargaPerJam,total;
    String jenisLantai;
    
    //Method Class Interface 
    @Override
    public String jenisLantai() {
        return "Vinyl";
    }

    //Membuat Constructor
    public LapanganFutsal(){
        this.hargaPerJam=100000;
        this.lamaWaktu=lamaWaktu;
        this.pilihLapangan=pilihLapangan;
        this.nomerlap=nomerlap;
    }
    //Method Abstack Method Abstrack
    @Override
    public String Status(){
     return "Lapangan Tidak Tersedia";
    }
    public String Status(String Nomer){
    return "Lapangan Tersedia";
    }
    //Setter And Getter
    public String getPilihLapangan() {
        return pilihLapangan;
    }
    public void setPilihLapangan(String pilihLapangan) {
        this.pilihLapangan = pilihLapangan;
    }
    public String pilihLapangan(){
    return pilihLapangan;
    }
    //Method Polimorphisme
        @Override
    public String LoketByr(){
        return "Loket A";
    } 
    //Method
    public int total(){
        this.total= lamaWaktu*hargaPerJam;
        return total;
    }
    void Setjudul(){
        System.out.println("<>===<> HAKIM FUTSAL <>===<>");
        System.out.println("");
    }

    void namaPemesan(String Nama){
        this.namaPemesan = Nama;
    }
    void nomerPenyewa(String Nomer){
        this.nomerPenyewa = Nomer;
    }
    void tanggalPenyewaan(String Tanggal){
        this.tanggalPenyewaan = Tanggal;
    }

    void metodePembayaran(String Bayar){
        this.pembayaran = Bayar;
    }
    void nolap(String nolap){
        this.nomerlap = nolap;
    }

    String cetakNama(){
    return namaPemesan;
}

    String cetakTanggal(){
    return tanggalPenyewaan;
}

    String cetakPilih(){
    return nomerlap;
}

    //Method untuh mengecek pilihlapangan yang dipilih
    boolean Cek(String pilihLapangan){
        if(pilihLapangan.equals(getPilihLapangan())){
        Status();
        return true;
    }
    Status(Nomer);
    return false;
  }
}
    