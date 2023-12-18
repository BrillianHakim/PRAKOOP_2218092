/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PenerapanBAB9;

/**
 *
 * @author WINDOWS 10
 */
public class Login {
        private String username,password;
    public String nama;
    
    public Login(){
        nama = "Bintang Brillian Hakim";
        username = "Hakim";
        password ="Zulia Cantik";
        
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    
    //
    boolean CekLogin(String Username,String password){
        if(username.equals(getUsername())&&password.equals(getPassword())){
        return true;
    }
    return false;
    
}
}
