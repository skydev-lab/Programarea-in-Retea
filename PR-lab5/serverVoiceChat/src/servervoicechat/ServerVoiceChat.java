 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servervoicechat;

/**
 *
 * @author danch
 */
public class ServerVoiceChat {

    /**
     * @param args the command line arguments
     */
    public static boolean calling = false;
    public static void main(String[] args) {
        // TODO code application logic here
        server_fr fr = new server_fr();
        fr.setVisible(true);
    }
    
}
