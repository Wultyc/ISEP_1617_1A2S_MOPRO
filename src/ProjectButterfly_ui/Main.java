package ProjectButterfly_ui;

import java.io.IOException;
import ProjectButterfly_core.PersistentData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author J. Gabriel Azevedo 1160929
 * @author André Oliveira 1161260
 *
 * @version 1.0
 *
 * @license Ler o ficheiro license.txt junto com este projeto
 */
public class Main {

    //Constantes informativas
    protected static final String Project_Name = "Project Butterfly";

    private static String appFilePath = System.getProperty("user.home") + File.separator + ".ProjectButterfly.dat";

    //Informação guardada
    protected static PersistentData pd; //Cria o objeto que vai receber a informação

    //Ações autorizadas
    protected static Features authorized_action;

    //Interface de utilizador
    protected static UserInterface ui;

    //Obejto de login
    protected static Login user;

    //Codigo principal da apliacção
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        //Informação persistente
        loadData(); //carrega a informação guardada numa sessão anterior pd
        //pd = new PersistentData();

        authorized_action = new Features(); //Cria um obejto de ações autorizadas

        //Inicia sessão
        user = new Login();

        //Cria a interface de utilizador
        switch (user.getUser_level()) {
            case 0:
                ui = new UserInterfaceStudent();
                break;
            case 1:
                ui = new UserInterfaceAdvisor();
                break;
            case 2:
                ui = new UserInterfaceManager();
                break;
            case 3:
                ui = new UserInterfaceSU();
                break;
        }

        //Inicia a UI
        ui.startUI();
    }

    public static void saveData() throws FileNotFoundException, IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(appFilePath));
        try {
            out.writeObject(pd);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void loadData() throws IOException {

        pd = new PersistentData();

        try {
            FileInputStream fileIn = new FileInputStream(appFilePath);
            ObjectInputStream inStream = new ObjectInputStream(fileIn);
            pd = (PersistentData) inStream.readObject();

            inStream.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException ex) {
            
        }
    }
}
