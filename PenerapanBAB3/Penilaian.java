/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PenerapanBAB3;

/**
 *
 * @author WINDOWS 10
 */

public class Penilaian {
    String NIM,nama,kode_mk;
    int NP1,NP2,NP3,NilaiPrak,UTS,UAS;
    
    double nilaiproses(){
        return ((NP1*0.1) + (NP2*0.2) + (NP3*0.1)+(UTS*0.2)+(NilaiPrak*0.4));
    }
    double nilaiakahir(){
        return (nilaiproses()*0.6)+ (UAS*0.3);
    }
    double tampilNA(){
    return nilaiakahir();
}
    double nilaikeaktifan(){
        return 0;
    }
}
