import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Ingrese IP del servidor: ");
        String ip = br.readLine();
        try{
            Socket socketServer = new Socket(ip, Servidor.puerto);
            InputStreamReader isr = new InputStreamReader(socketServer.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            PrintWriter out = new PrintWriter(socketServer.getOutputStream(),true);

            System.out.print("Ingrese usuario: ");
            String usuario = br.readLine();
            out.println(usuario);
            System.out.print("Ingrese password: ");
            String pwd = br.readLine();
            out.println(pwd);
            System.out.println("\n" + in.readLine() + "\n" + in.readLine());
            out.println("CLIST: "+usuario);
            out.println("CLIENT: GETNEWMAILS " + usuario);


            in.close();
            out.close();
            socketServer.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
