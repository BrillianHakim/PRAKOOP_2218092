/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PenerapanBAB4;

/**
 *
 * @author WINDOWS 10
 */
public class Keaktifan extends Penilaian{
   int nilai_keaktifan;

    public Keaktifan() {
        this.nilai_keaktifan = 0;
       
    }
  @Override
    double nilaikeaktifan(){
        return ((nilai_keaktifan*0.1)+nilaiakahir());
}
}