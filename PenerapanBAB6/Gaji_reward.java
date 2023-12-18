package PenerapanBAB6;

public class Gaji_reward extends Gaji_abs_reward{
  double anak,tunj_anak,gajipokok,tunj_bonus,menit;
  double waktu;

    @Override
    double tunjanganAnak() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    double tunj_anak(){
        if(anak==1){
            tunj_anak=0.10*gajipokok;
        }else if(anak>1){
            tunj_anak=0.20*gajipokok;
        }else{
            tunj_anak=0;
        }
        return tunj_anak;
    }
    double Lembur(){
        waktu=(menit/60);
        return (waktu*25000);
    }
    
}
