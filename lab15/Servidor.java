import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

public class Servidor{
    static Hashtable<String,String> usuarios = new Hashtable<String,String>();

    public static void usu(){
        usuarios.put("javier", "javier123");
        usuarios.put("fernando", "fernando123");
        usuarios.put("kevin", "kevin123");
        usuarios.put("erick", "erick123");
    }
    static int puerto = 1200;
    static HashMap<String,String> band = new HashMap<String,String>();
    public static void main(String[] args) throws Exception{
        usu();
        try{
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Server corriendo en el puerto " + puerto + ", esperando conexion...");
            Socket socketClient = servidor.accept();
            System.out.println("Conexion aceptada!");

            InputStreamReader isr = new InputStreamReader(socketClient.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            PrintWriter out = new PrintWriter(socketClient.getOutputStream(),true);

            String usuario = in.readLine();
            String pwd = in.readLine();
            System.out.println("Cliente: LOGIN " + usuario + " " + pwd);
            if(usuarios.containsKey(usuario)){
                if(usuarios.get(usuario).equals(pwd)){
                    System.out.println("Servidor: OK LOGIN");
                    out.println("Conexion con servidor exitosa!");
                    out.println("Sesion iniciada para " + usuario);
                }
                else{
                    System.out.println("Servidor: LOGIN fallido");
                    out.println("Usuario y/o password incorrecto");
                    System.exit(0);
                }
            }
            System.out.println("\n" + in.readLine());
            lsContact(usuario,usuario);
            System.out.println("\n" + in.readLine());
            Bandeja();
            System.out.println();
            in.close();
            out.close();
            socketClient.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    //Método de prueba de la Bandeja de correos con fines de presentación del laboratorio 15
    // Se está desarrollando la implemantación real
    public static void Bandeja(){
      band.put("adolfo@bac.gt","adolfo@bac.gt  \"SOLICITUD DE BAC\" \"Se le solicita la información para la tarjeta de crédito\"");
      band.put("services@claro.gt","services@claro.gt  \"VENCIMIENTO DE LINEA\" \"El pago de su línea telefónica ha vencido\"");
      for(String mails: band.values()){
        System.out.println("Servidor:  OK GETNEWMAILS "+mails);
      }

    }
    // método provisional para comando CLIST
    // recibe de parámetro nombre de usuario que ejecutó comando (uc)
    // y el registrado (u) para validar que haya lista de contactos para ese usuario
    public static boolean lsContact(String uc, String u){
      if (uc != u) return false;
      else {
      LinkedList<String> lista_contactos = new LinkedList<String>();
      // número de contactos agregados a la lista del usuario
      int contactos_in = (int)(Math.random()*(10-2+1)+2);

      //añadir usuarios a lista
      int c = 0;
      while (c < contactos_in) {
        lista_contactos.add("u" + Integer.toString(c+1));
        c++;
      }
      int t = lista_contactos.size();

      //desplegar usuarios
      String str = "OK CLIST ";
      System.out.println(str + lista_contactos.get(0) + "@server");
      for (int i = 1; i < t-1; i++) {
        str += lista_contactos.get(i) + "@server" + Integer.toString(i+1);
        System.out.println(str);
        str = "OK CLIST ";
      }
      System.out.println(str + lista_contactos.get(t-1) + "@server" + Integer.toString(t) + " *");
      return true;
    }
  }
}
