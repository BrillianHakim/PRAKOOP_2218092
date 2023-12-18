/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PenerapanBAB9;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author WINDOWS 10
 */
public class Lat_Exception {
    public static void main(String[] args) {
        try{
            int a,b,hasil;
            //Membuat Scanner Baru
            Scanner keyboard = new Scanner(System.in);
            System.out.println("======Program Pembagian=======");
            System.out.println("Masukkan Angka 1 = ");
            a = Integer.parseInt(keyboard.next());
            System.out.println("Masukkan Angka Ke 2 = ");
            b = Integer.parseInt(keyboard.next());
            hasil=a/b;
            System.out.println(Integer.toString(hasil));
            
            }catch(ArithmeticException c){
                JOptionPane.showMessageDialog(null, "Nilai Pembagi tidak boleh 0");
            }catch(NumberFormatException d){
                 JOptionPane.showMessageDialog(null, "Inputan yang anda masukkan huruf bukan angka");
            }finally{
            System.out.println("Terima KAsih sudah menjalankan program");
        }
        }
    }

