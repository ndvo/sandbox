import javax.crypto.*;
import java.security.*;
import java.util.Base64;


public class Main {

    public static void main (String[] args) {
        try {
            // Ana e Beto geram uma chave criptográfica
            KeyGenerator g = KeyGenerator.getInstance("AES");
            SecureRandom sR = new SecureRandom();
            g.init(256, sR);
            SecretKey k = g.generateKey();

            // Para facilitar a visualização vamos imprimir como Base64
            System.out.println(String.format("Chave: %s", b64(k.getEncoded())));

            // Ana encripta a mensagem usando AES
            String m = "Mensagem Secreta";
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, k);
            byte[] r = c.doFinal(m.getBytes());
            System.out.println(String.format("Mensagem encriptada: %s", b64(r)));

            // Beto decripta a mensagem usando AES
            Cipher d = Cipher.getInstance("AES");
            d.init(Cipher.DECRYPT_MODE, k);
            byte[] decripted = d.doFinal(r);
            System.out.println(String.format("Decriptado: %s", new String(decripted)));

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }

    }

    private static String b64(byte[] b) {
        Base64.Encoder b64Enc = Base64.getEncoder();
        return new String(b64Enc.encode(b));
    }

}

