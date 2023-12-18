/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package  PenerapanBAB7;

/**
 *
 * @author WINDOWS 10
 */
public class Cash implements dapatKembalian{
    public double kembalian(double total , double jmlUang) {
    double jmlKembalian;
    jmlKembalian = jmlUang - total;
    return jmlKembalian;
}
}
