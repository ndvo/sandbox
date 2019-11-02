import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;


public class Main {

    public static void main (String[] args) {
        Security.addProvider(new BouncyCastleProvider());
        try {
            // Ana e Beto geram uma chave criptográfica
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "BC");
            SecureRandom sR = new SecureRandom();
            keyGenerator.init(256, sR);
            SecretKey k = keyGenerator.generateKey();

            // Para facilitar a visualização vamos imprimir como Base64
            System.out.println(String.format("Chave: %s", b64(k.getEncoded())));

            // Ana encripta a mensagem usando AES
            String mensagem = "Mensagem Secreta";
            Cipher encriptador = Cipher.getInstance("AES/CTR/PKCS7Padding", "BC");
            encriptador.init(Cipher.ENCRYPT_MODE, k);
            byte[] criptograma = encriptador.doFinal(mensagem.getBytes());
            System.out.println(String.format("Mensagem encriptada: %s", b64(criptograma)));
            byte[] iv = encriptador.getIV();

            // Ana e Beto geram uma chave criptográfica para utilizar na autenticação das mensagens
            String macAlgorithm = "HMACRIPEMD160";

            KeyGenerator macKeyGenerator = KeyGenerator.getInstance(macAlgorithm);
            macKeyGenerator.init(256, new SecureRandom());
            SecretKey macKey = macKeyGenerator.generateKey();

            // Ana inicializa o gerador de tag de autenticação mac
            Mac macAna = Mac.getInstance(macAlgorithm);
            macAna.init(macKey);
            // Ana inclui o criptograma gerado no autenticador de mensagem e gera a tag de autenticação
            macAna.update(criptograma);
            byte[] tagAutenticacao = macAna.doFinal();


            // Beto verifica a tag de autenticação
            Mac macBeto = Mac.getInstance(macAlgorithm);
            macBeto.init(macKey);
            macBeto.update(criptograma);
            byte[] tagParaVerificacao = macBeto.doFinal();

            // Beto decripta a mensagem usando AES, apenas se a autenticação for bem sucedida
            if (Arrays.equals(tagAutenticacao, tagParaVerificacao)) {
                System.out.println("Verificação da tag de autenticação bem sucedida.");
                Cipher d = Cipher.getInstance("AES/CTR/PKCS7Padding", "BC");
                d.init(Cipher.DECRYPT_MODE, k, new IvParameterSpec(iv));
                byte[] decripted = d.doFinal(criptograma);
                System.out.println(String.format("Decriptado: %s", new String(decripted)));
            }

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

    }

    private static String b64(byte[] b) {
        Base64.Encoder b64Enc = Base64.getEncoder();
        return new String(b64Enc.encode(b));
    }

}

