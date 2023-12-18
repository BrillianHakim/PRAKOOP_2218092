/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package  PenerapanBAB7;

/**
 *
 * @author WINDOWS 10
 */
public class Driverclass {
    public static void main(String[] args) {
        
    
    Manusia manusia = new Manusia();
    
    Buah apel = new Apel();
    Buah pisang = new Pisang();
    
    manusia.makanBuah(apel);
    manusia.makanBuah(pisang);
}
}