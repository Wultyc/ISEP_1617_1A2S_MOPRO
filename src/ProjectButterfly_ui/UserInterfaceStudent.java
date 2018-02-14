package ProjectButterfly_ui;

import java.util.ArrayList;
import ProjectButterfly_core.*;
import java.io.IOException;
import java.util.Collections;

public class UserInterfaceStudent extends UserInterface {

    //Informação Guardada
    private ArrayList<Person> person_data = Main.pd.getPerson_data();
    private ArrayList<Company> companys_data = Main.pd.getCompanys_data();
    private ArrayList<Project> projects_data = Main.pd.getProjects_data();
    private ArrayList<Users> users_data = Main.pd.getUsers_data();

    public static UserInterfaceUtils utils = new UserInterfaceUtils();

    public UserInterfaceStudent() {

    }

    public void startUI() {
        //Onde vai ficar a parte de execução do programa
        menu();
    }

    public void menu() {
        int option = utils.showMenu("Menu Inicial", "Bem-vindo", new String[]{"Ver Informações do Projeto", "Ver Perfil", "Atualizar Dados Pessoais"});
        switch (option) {
            case 0:
                view_project();
                break;
            case 1:
                profile();
                break;
            case 2:
                update_profile();
                break;
            case -1:
                try {
                    Main.saveData();
                    utils.printMessage(utils.InfoMessage, "Sucesso", "Dados guardados com sucesso");
                } catch (IOException ex) {
                    utils.showQuestionWithMenu(utils.ErrorMessage, "Erro", "Erro a gravar o ficheiro de backup.\nPretende manter o programa aberto ou descartar as alterações feitas", new String[]{"Manter a aplicação aberta", "Descartar alterações"});
                }
                break;
        }
    }

    public void view_project() {
        int i = 0, s_id = Main.user.getUser_profile();
        String output = "";
        for(Project p : projects_data){
            if(p.getStudent() == s_id && p.getState() == 1){
                output = Main.authorized_action.showProjectInfo(i);
                break;
            }else{
                output = "Não tem nenhum projeto ativo no momento.";
            }
            i++;
        }
        utils.printMessage(utils.InfoMessage,"Projeto",output);
    }

    public void update_profile() {
        int ans = 0,
                id = 0;
                id = Main.user.getUser_profile();
        String readedans = "";
        ans = utils.showConfirmationQuestion("Alterar dados", "Pretende alterar a sua morada?");

        if (ans == 0) {
            readedans = utils.showQuestion(utils.QuestionMessage, "Aletar o morada","Nova morada: ");
            Main.authorized_action.updateUserData(Main.authorized_action.UserAdress, id, readedans);
            utils.printMessage(utils.InfoMessage,"Aletar o morada","Morada atualizado com sucesso!");
        }

        ans = utils.showConfirmationQuestion("Mudar a password", "Pretende alterar a sua password?");
        if (ans == utils.Yes) {
            readedans = utils.readNewPassword();
            Main.authorized_action.updateUserData(Main.authorized_action.Userpw, id, readedans);
            utils.printMessage(utils.InfoMessage, "Password", "Password atualizada com sucesso!");
        }
    }

    public void profile() {
        int user_id = Main.user.getUser_profile();
        String output = Main.authorized_action.showProfile(user_id);
        utils.printMessage(utils.InfoMessage, "Perfil", output);
        menu();
    }
}
