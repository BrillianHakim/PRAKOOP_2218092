/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PenerapanBAB9;

/**
 *
 * @author WINDOWS 10
 */
public class throwsException {
    static void Error() throws ClassCastException{
        System.out.println("Ada Yang Error Ni!");
        throw new ClassCastException("Error Sudah Saya TAngkap");
        
    }
    public static void main(String[] args) {
        try{
            throwsException.Error();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
