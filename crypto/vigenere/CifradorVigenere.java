class CifradorVigenere {
  private String chave;
  private String alfabeto = "aáâãàbcçdeéêfghiíjklmnoóôõpqrstuúüvwxyzAÁÂÃÀBCÇDEÉÊFGHIÍJKLMNOÓÔÕPQRSTUÚÜVWXYZ .,;_-?!$%&@(){}1234567890";

  private String chaveExpandida;

  CifradorVigenere(String chaveSecreta){
    this.chave = chaveSecreta;
  }


  public String encripta(String textoClaro){
    this.chaveExpandida = expandeChave(textoClaro);
    StringBuilder criptograma = new StringBuilder();
    for (int i = 0; i < textoClaro.length(); i++){
      criptograma.append(
          this.charClaroParaCripto(
            textoClaro.charAt(i),
            this.chaveExpandida.charAt(i)
          )
        );
    }
    this.chaveExpandida = "";
    return criptograma.toString();
  }

  public String decripta(String criptograma){
    this.chaveExpandida = expandeChave(criptograma);
    StringBuilder textoClaroRecuperado = new StringBuilder();
    for (int i = 0; i < criptograma.length(); i++){
      textoClaroRecuperado.append(
          this.charCriptoParaClaro(
            criptograma.charAt(i),
            this.chaveExpandida.charAt(i)
          )
        );
    }
    this.chaveExpandida = "";
    return textoClaroRecuperado.toString();
  }

  /**
   * Retorna um caractere substituído pela posição adicionada com a chave.
   */
  private char charClaroParaCripto(char claro, char chave){
    int posicaoOriginal = this.alfabeto.indexOf(claro);
    int posicaoChave = this.alfabeto.indexOf(chave);
    int novaPosicao = (posicaoOriginal + posicaoChave) % this.alfabeto.length();
    return this.alfabeto.charAt(novaPosicao);
  }

  /**
   * Retorna um caractere substituído pela posição subtraída com a chave.
   */
  private char charCriptoParaClaro(char cripto, char chave){
    int posicaoOriginal = this.alfabeto.indexOf(cripto);
    int posicaoChave = this.alfabeto.indexOf(chave);
    int novaPosicao = (posicaoOriginal - posicaoChave) % this.alfabeto.length();
    // novaPosicao pode ser negativa
    if (novaPosicao < 0)
      novaPosicao += this.alfabeto.length();
    return this.alfabeto.charAt(novaPosicao);
  }


  private String expandeChave(String textoClaro){
    StringBuilder chaveExpandida = new StringBuilder(this.chave);
    while (chaveExpandida.length() < textoClaro.length())
      chaveExpandida.append(this.chave);
    return chaveExpandida.toString();

  }
}
