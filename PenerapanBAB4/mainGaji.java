/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PenerapanBAB4;

/**
 *
 * @author WINDOWS 10
 */
public class mainGaji {
    public static void main(String[] args) {
        pembayaranGaji gaji = new pembayaranGaji();
        gaji.setInput("G1234");
        System.out.println("Rekening Yang Anda Masukkan : "+gaji.getInput());
        System.out.println("Jumlah Saldo Anda : "+gaji.cekSaldo());
        
    }
}
