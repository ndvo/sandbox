public class Main {

  public static void main(String[] args) {
    System.out.println("ok");

    String textoClaro = "Minha mensagem secreta.";
    String chaveSecreta = "SenhaSuficientementeGrande,masSemEntropia.";

    String criptograma = testeEncriptar(textoClaro, chaveSecreta);
    imprime("Criptograma", criptograma);


    String textoClaroRecuperado = testeDecriptar(criptograma, chaveSecreta);
    imprime("Recuperado", textoClaroRecuperado);

  }

  public static String testeEncriptar(String textoClaro, String chaveSecreta){
    CifradorVigenere cifrador = new CifradorVigenere(chaveSecreta);
    String criptograma = cifrador.encripta(textoClaro);
    return criptograma;
  }

  public static String testeDecriptar(String criptograma, String chaveSecreta){
    CifradorVigenere cifrador = new CifradorVigenere(chaveSecreta);
    String textoClaroRecuperado = cifrador.decripta(criptograma);
    return textoClaroRecuperado;
  }

  public static void imprime(String chave, String valor){
    System.out.println(String.format("%s: %s", chave, valor));
  }
}

