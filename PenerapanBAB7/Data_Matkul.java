/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package  PenerapanBAB7;

/**
 *
 * @author 62821
 */
public class Data_Matkul {
    String kode_mk, nama_mk, dosen_pengampu;
    String jml_sks;

    // Konstruktor tanpa parameter
    public Data_Matkul() {
        this.jml_sks = "";  // Nilai default atau dapat disesuaikan
        this.kode_mk = "";
        this.nama_mk = "";
        this.dosen_pengampu = "";
    }

    // Konstruktor dengan parameter
    public Data_Matkul(String jml_sks, String kode_mk, String nama_mk, String dosen_pengampu) {
        this.jml_sks = jml_sks;
        this.kode_mk = kode_mk;
        this.nama_mk = nama_mk;
        this.dosen_pengampu = dosen_pengampu;
    }
}

