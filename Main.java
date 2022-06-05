import java.util.Random;
import javax.swing.JOptionPane;



class Oraculo {

  String nome;
  Guerreiro guerreiro;

  void setNome(String nome) {
    this.nome = nome;
  }

  int setVidas() {
    Random num = new Random();
    return num.nextInt(4) + 9;
  }

  String prologoIntroducao() {
    return "Eu sou o oráculo " + this.nome + ", você tem " + guerreiro.getVidas() + " vidas.";
  }

  int loadLevel1() {
    Random num = new Random();
    return num.nextInt(100) + 1; 
    
  }

  int loadLevel2(int opcao) {
    Random num = new Random();

    int numOraculo = num.nextInt(6);
    int numGuerreiro = num.nextInt(6);
    int soma = numGuerreiro + numOraculo;

    if (opcao == 1 && soma % 2 > 0) {
      return 1;

    } else if (opcao == 2 && soma % 2 == 0) {
      return 1;

    } else if (opcao == 1 || opcao == 2) {
      return 0;

    } else {
      return 2;
    }
  }

  String prologoVencedor() {
    return this.nome + " diz: " + this.guerreiro.getNome() + ", você me derrotou :(";
  }

  String prologoPerdedor() {
    return this.nome + " diz: " + this.guerreiro.getNome() + ", você perdeu hahahahha";
  }

  boolean decidirVidaExtra(String misericordia) {
    String pedido = misericordia.trim();
    int espacos = 0;

    for (int i = 0; i < pedido.length(); i++) {
      String letra = Character.toString(pedido.charAt(i));

      if (letra.equals(" ")) {
        espacos++;
      }
    }

    if (espacos >= 5) {
      return true;
    } else {
      return false;
    }
  }
}






class Guerreiro {

  String nome;
  int vidas;

  void setNome(String nome) {
    this.nome = nome;
  }

  int getVidas() {
    return this.vidas;
  }

  String getNome() {
    return this.nome;
  }

  String vidaExtra() {
    return JOptionPane.showInputDialog("Digite seu pedido de misericórdia:");
  }
}




public class Main {

  public static void main(String[] args) throws Exception {

    Guerreiro guerreiro1 = new Guerreiro();
    Oraculo oraculoMaster = new Oraculo();

    oraculoMaster.setNome("Ronaldo");
    oraculoMaster.guerreiro = guerreiro1;
    guerreiro1.vidas = oraculoMaster.setVidas();

    JOptionPane.showMessageDialog(null, oraculoMaster.prologoIntroducao());

    guerreiro1.setNome(JOptionPane.showInputDialog("Digite seu nome:"));
    

    JOptionPane.showMessageDialog(null, "\nComo Jogar:\n\nPrimeiro Level\n\n- No primeiro level seu objetivo é acertar o segredo da porta. \n- Caso acabem suas vidas você poderá pedir ao oráculo por uma vida extra.\n- Acertando o segredo da porta, você passará para o segundo level. \n\nSegundo Level\n\n- O segundo level será um jogo de ímpar ou par com o oráculo.");


    int randomNum = oraculoMaster.loadLevel1();

    while(guerreiro1.getVidas() > 0) {

      while(true){

       int numChave = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o segredo da porta (número de 0 a 100):"));

    if (numChave > 100 || numChave < 0) {
      JOptionPane.showMessageDialog(null,"Número Inválido... Tente Novamente!");
      }

    else{
      if (randomNum < numChave) {
        guerreiro1.vidas--;
        JOptionPane.showMessageDialog(null, "A chave digitada foi: " + numChave + "\nDica do oráculo Ronaldo: O segredo da porta é menor que a chave\nVocê ainda tem: " + guerreiro1.vidas + " vidas.");
        
      } 
      
      else if (randomNum > numChave) {
         guerreiro1.vidas--;
         JOptionPane.showMessageDialog(null, "A chave digitada foi: " + numChave + "\nDica do oráculo Ronaldo: O segredo da porta é maior que a chave\nVocê ainda tem: " + guerreiro1.vidas + " vidas.");
        
      }

      else {
        JOptionPane.showMessageDialog(null,"Parabéns! Você acertou o segredo da porta!");
        guerreiro1.vidas = -1;
      }
    break;
    }
    }
    }

    if (guerreiro1.vidas == 0){

    String misericordia = guerreiro1.vidaExtra();
    oraculoMaster.decidirVidaExtra(misericordia);

    if (oraculoMaster.decidirVidaExtra(misericordia) == true) { 

       int numChave2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o segredo da porta:"));

       if (randomNum == numChave2) {
         JOptionPane.showMessageDialog(null,"Parabéns, você acertou o segredo da porta!");
         guerreiro1.vidas--;
       }

        else{
      JOptionPane.showMessageDialog(null,oraculoMaster.prologoPerdedor());
      JOptionPane.showMessageDialog(null,"Fim De Jogo.");
       }
    }

    else{
      JOptionPane.showMessageDialog(null,oraculoMaster.prologoPerdedor());
       JOptionPane.showMessageDialog(null,"Fim De Jogo.");
    }
    }
    
    
    if (guerreiro1.getVidas() == -1) { 

      JOptionPane.showMessageDialog(null,"Você passou para o level 2!");

      while(true){    
        
      int opcao = Integer.parseInt(JOptionPane.showInputDialog("Escolha [1] para ímpar ou [2] para par:"));

      if (oraculoMaster.loadLevel2(opcao) == 1){
      JOptionPane.showMessageDialog(null,oraculoMaster.prologoVencedor());
      break;
      }

      else if (oraculoMaster.loadLevel2(opcao) == 0){
      JOptionPane.showMessageDialog(null,oraculoMaster.prologoPerdedor());
      break;
      }

      else {
      JOptionPane.showMessageDialog(null, "Opção inválida...Escolha novamente!");
      }
      }
    } 
    }
  }