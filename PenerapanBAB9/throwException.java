/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PenerapanBAB9;

/**
 *
 * @author WINDOWS 10
 */
import java.util.Scanner;
public class throwException {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int jmlKaki;
        System.out.println("Berapa Jumlah Kaki Kerbau ?");
        try{
            System.out.println("Jumlah Kaki : ");
            jmlKaki=input.nextInt();
            if(jmlKaki!=4){
                throw new NullPointerException("Terjadi Kesalahan Nih!");
                
            }else{
                System.out.println("Benar Jumlah kaki Kerbau 4");
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }
}
