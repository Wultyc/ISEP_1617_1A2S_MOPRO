package ProjectButterfly_ui;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class UserInterfaceUtils {

    //Atributos
    String modelationLine = ""; //Comando utilizado para limpar a janela da consola
    int line_len = 135;

    //Constantes de apoio
    final int ErrorMessage = JOptionPane.ERROR_MESSAGE;
    final int InfoMessage = JOptionPane.INFORMATION_MESSAGE;
    final int QuestionMessage = JOptionPane.QUESTION_MESSAGE;

    final int Yes = 0;
    final int No = 0;

    //Objeto de leitura
    Scanner in = new Scanner(System.in);

    public UserInterfaceUtils() {
        for (int i = 0; i < line_len; i++) {
            modelationLine += "=";
        }
    }

    public int showMenu(String title, String msg, String[] options) {
        int output = 0, n_button = 0;
        output = JOptionPane.showOptionDialog(null, msg, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        return output;
    }

    public String readEmail() {
        String msg = "Introduza o seu email";
        String output = showQuestion(InfoMessage, "Email", msg);
        while (output.length() < 1 || output.equals(null)) {
            printMessage(ErrorMessage, "Erro", "Introduza um email válido");
            output = showQuestion(InfoMessage, "Email", msg);
        }
        return output;
    }

    public String readPassword() {
        String output = showQuestion(InfoMessage, "Password", "Introduza a sua password");
        while (output.length() < 1 || output.equals(null)) {
            printMessage(ErrorMessage, "Erro", "Introduza uma password válida");
            output = showQuestion(InfoMessage, "Password", "Introduza a sua password");
        }
        return output;
    }

    /**
     *
     * @about Lê e confima uma password
     * @return pw
     */
    public String readNewPassword() {
        String pw = "", pw_rep = "";
        while (!pw.equals(pw_rep) || pw.length() == 0) {
            pw = showQuestion(line_len, "Password", "Introduza a password:");

            pw_rep = showQuestion(line_len, "Repetir a Password", "Re-introduza a password:");

            if (!pw.equals(pw_rep)) {
                printMessage(ErrorMessage, "Erro", "As passwords nao coincidem!");
            } else if (pw.length() == 0) {
                printMessage(ErrorMessage, "Erro", "A password nao nao pode estar em branco!");
            }
        }
        return pw;
    }

    public void printMessage(int type, String title, String msg) {
        JOptionPane.showMessageDialog(null, msg, title, type);
    }

    /**
     * @about Imprime uma pergunta e mostra ao utilizador e lê a resposta
     * @param question
     * @param outputCompatibilityData
     * @return output
     */
    public String showQuestion(int type, String title, String msg) {
        String output = "";
        output = JOptionPane.showInputDialog(null, msg, title, type);
        cancelOpertion(output);
        return output;
    }

    /**
     * @about Imprime uma pergunta e mostra ao utilizador e lê a resposta
     * @param type
     * @param title
     * @param msg
     * @return output
     */
    public int showQuestionInt(int type, String title, String msg) {
        int output = 0;
        boolean validated = false;
        String readedString;

        readedString = JOptionPane.showInputDialog(null, msg, title, type);

        do {
            try {
                output = Integer.parseInt(readedString);
                validated = true;
            } catch (NumberFormatException e) {
                printMessage(ErrorMessage, "Erro!", "Insira um valor inteiro!");
            }
        } while (validated == false);

        return output;
    }

    /**
     * @throws java.io.IOException
     * @about Imprime uma pergunta e mostra ao utilizador e lê a resposta
     * @param type
     * @param title
     * @param msg
     * @return output
     */
    public double showQuestionDouble(int type, String title, String msg) {
        double output = 0;
        boolean validated = false;
        String readedString;

        readedString = JOptionPane.showInputDialog(null, msg, title, type);

        do {
            try {
                output = Double.parseDouble(readedString);
                validated = true;
            } catch (NumberFormatException e) {
                printMessage(ErrorMessage, "Erro!", "Insira um valor inteiro!");
            }
        } while (validated == false);

        return output;
    }

    /**
     * @about Imprime uma pergunta e mostra ao utilizador as opções disponiveis
     * para a resposta
     * @param question
     * @param options
     * @return
     */
    public int showQuestionWithMenu(int type, String title, String msg, String[] options) {
        int i = 0, output = -1;
        String readed_ask = (String) JOptionPane.showInputDialog(null, msg, title, type, null, options, options[0]);
        cancelOpertion(readed_ask);
        for(String s : options){
            if(s.equals(readed_ask)){
                output = i;
                break;
            }
            i++;
        }
        
        cancelOpertion(output);
        
        return output;
    }

    public int showConfirmationQuestion(String title, String msg) {
        int output = JOptionPane.showOptionDialog(null, msg, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Sim", "Não"}, "Sim");
        
        cancelOpertion(output);
        
        return output;
    }
    
    private void cancelOpertion(int input){
        if(input == -1){
            Main.ui.menu();
        }
    }
    
    private void cancelOpertion(String input){
        if(input.equals(null)){
            Main.ui.startUI();
        }
    }
}
