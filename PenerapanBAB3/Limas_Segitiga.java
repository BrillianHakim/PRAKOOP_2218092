/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PenerapanBAB3;

/**
 *
 * @author WINDOWS 10
 */
public class Limas_Segitiga extends Segitiga{
    double tinggiLimas,vol;
    double volumeLimas(){
        vol = ((0.333*luas())*tinggiLimas);
        return vol;
    }
}
