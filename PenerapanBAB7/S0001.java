/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PenerapanBAB7;

/**
 *
 * @author WINDOWS 10
 */
public class S0001 extends Pembayaran{
    public String nama, alamat;
    public int saldo;
    public String kode;
    double diskon,bayar,total;
    public String InputPinKredit;
    
    public S0001(){
        this.nama="Zulia Iza";
        this.kode="S0001";
        this.saldo=50000000;
        this.alamat="Dampit";
    }
    public double hasil(){
        return diskon;
    }
    double cekKode(String input){
     if(input.compareTo(kode)==0){
         diskon = 0.1;
     }   else{
         diskon = 0 ;
     }
     return diskon;
    }
    double hitPembayaran(double bayar){
        diskon=bayar*diskon;
        total=bayar-diskon;
        return total;
    }
    double potSaldo(){
        return this.saldo-total;
    }
    void tampilkanMember(){
        System.out.println("Member S0001 dengan diskon 10%");
    }
    void jenisPembayaran(Cash cash) {
        if (bayar >= total) {
            System.out.println("Kembalian : " +cash.kembalian(total, bayar));
        } else if (bayar <= total) {
            System.out.println("uang anda kurang");
        } else {
            System.out.println("Pembayaran Berhasil");
        }
    }
    void jenisPembayaran(Emoney emoney) {
        emoney.scanQris();
}
    void jenisPembayaran(Kreadit kredit) {
        kredit.cekKartuKredit(kode, InputPinKredit);
}
}