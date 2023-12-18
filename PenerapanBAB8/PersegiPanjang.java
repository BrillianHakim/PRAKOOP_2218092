/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PenerapanBAB8;

/**
 *
 * @author WINDOWS 10
 */
public class PersegiPanjang extends BangunRuang implements Keliling,SimetriBangunDatar{
    public double panjang,lebar;

    @Override
    double hitungluas() {
        return (panjang*lebar);
    }

    @Override
    public void tampilHasil() {
        System.out.println("luas persegi panjang =" + Double.toString(hitungluas()));
        System.out.println("keliling persegi panjang =" + Double.toString(hitungKeliling()));
        System.out.println("banyak simetri putar = " + hitungSimetriPutar());
    }

    @Override
    public double hitungKeliling() {
        return (2*(panjang*lebar));
    }

    @Override
    public double hitungSimetriPutar() {
        return 2.0;
    }
}
