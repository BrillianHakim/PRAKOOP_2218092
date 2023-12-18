package PenerapanBAB1;

public class Mahasiswa {
    String nim, nama, prodi, angktn,alamat,kelamin;
    void dataNIM(String Nim){
        this.nim = Nim;
    }    
    void dataNama(String Nama){
        this.nama = Nama;
    }
    void dataProdi(String Prodi){
        this.prodi = Prodi;
    }
    void dataAngkatan(String Angktn){
        this.angktn = Angktn;
    }
    void dataAlamat(String Alamat){
        this.alamat=Alamat;
    }
    void dataJenisKelamin(String JenisKelamin){
        this.kelamin=JenisKelamin;
    }
    String cetakNIM(){
        return nim;
    }
    String cetakNama(){
        return nama;
    }
    String cetakProdi(){
        return prodi;
    }
    String cetakAngkatan(){
        return angktn;
    }
    String cetakAlamat(){
        return alamat;
    }
    String cetakJenisKelamin(){
        return kelamin;
    }
}