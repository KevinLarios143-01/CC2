// método provisional para comando CLIST
// recibe de parámetro nombre de usuario que ejecutó comando (uc)
// y el registrado (u) para validar que haya lista de contactos para ese usuario
public boolean lsContact(String uc, String u){
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
