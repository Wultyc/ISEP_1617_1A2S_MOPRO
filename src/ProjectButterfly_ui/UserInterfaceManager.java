package ProjectButterfly_ui;

import java.util.ArrayList;
import ProjectButterfly_core.*;
import java.io.IOException;
import java.util.Collections;

public class UserInterfaceManager extends UserInterface {

    //Informação Guardada
    private ArrayList<Person> person_data = Main.pd.getPerson_data();
    private ArrayList<Company> companys_data = Main.pd.getCompanys_data();
    private ArrayList<Project> projects_data = Main.pd.getProjects_data();
    private ArrayList<Users> users_data = Main.pd.getUsers_data();

    public static UserInterfaceUtils utils = new UserInterfaceUtils();

    public UserInterfaceManager() {

    }

    public void startUI() {
        //Onde vai ficar a parte de execução do programa
        menu();
    }

    public void menu() {
        int option = utils.showMenu("Menu Inicial", "Bem-vindo", new String[]{"Editar Informações", "Procurar Projeto", "Informações de projetos", "Ver Perfil"});
        switch (option) {
            case 0:
                writeMenu();
                break;
            case 1:
                searchRegist();
                break;
            case 2:
                listRegist();
                break;
            case 3:
                profile();
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

    public void writeMenu() {
        int option = utils.showMenu("Alvo", "Onde pretende efetuar esta ação", new String[]{"Editar Informações Pessoais", "Editar Informações da Empresa", "Editar Informações de Projetos"});
        if (option != -1) {
            updateRegist(option);
        } else {
            menu();
        }
    }

    public void updateRegist(int target) {

        String readedAns = "";
        switch (target) {
            case 0:
                /*Atualiza Pessoa*/
                int ans = 0,
                 id = 0;
                id = Main.user.getUser_profile();
                String readedans = "";
                ans = utils.showConfirmationQuestion("Alterar dados","Pretende alterar o seu email de acesso?");
                
                if(ans == 0) {
                    readedans = utils.showQuestion(utils.QuestionMessage, "Aletar o email","Novo email: ");
                    Main.authorized_action.updateUserData(Main.authorized_action.UserEmail, id, readedans);
                    utils.printMessage(utils.QuestionMessage,"Alterar dados","Email atualizado com sucesso!");
                }

                ans = utils.showConfirmationQuestion("Mudar a password", "Pretende alterar a sua password?");
                if (ans == utils.Yes) {
                    readedAns = utils.readNewPassword();
                    Main.authorized_action.updateUserData(Main.authorized_action.Userpw, id, readedAns);
                    utils.printMessage(utils.InfoMessage, "Password", "Password atualizada com sucesso!");
                }
                /*Fim Atualiza Pessoa*/
                break;
            case 1:
                /*Atualiza Empresa*/
                int user_id = Main.user.getUser_profile();
                Manager profile = (Manager) person_data.get(user_id);
                int comp_id = profile.getEmployer();

                int option = utils.showQuestionWithMenu(utils.QuestionMessage, "Atualizar Empresa", "O que pertende alterar?", new String[]{"Morada", "Contacto Telefonico", "Website"});

                switch (option) {
                    case 0:
                        readedAns = utils.showQuestion(utils.QuestionMessage, "Atualizar Empresa", "Nova morada:");
                        Main.authorized_action.updateCompanyInfo(comp_id, Main.authorized_action.CompanyAdress, readedAns);
                        break;
                    case 1:
                        readedAns = utils.showQuestion(utils.QuestionMessage, "Atualizar Empresa", "Novo contacto telefonico:");
                        Main.authorized_action.updateCompanyInfo(comp_id, Main.authorized_action.CompanyTlf, readedAns);
                        break;
                    case 2:
                        readedAns = utils.showQuestion(utils.QuestionMessage, "Atualizar Empresa", "Nova Website:");
                        Main.authorized_action.updateCompanyInfo(comp_id, Main.authorized_action.CompanyWebSite, readedAns);
                        break;
                }
                /*Fim Atualiza Empresa*/
                break;

            case 2:
                /*Atualiza Projeto*/
                int i = 0,
                 ip = 0,
                 lenP = 0;

                for (Project p : projects_data) {
                    if (p.getManager() == Main.user.getUser_profile()) {
                        lenP++;
                    }
                }

                String[] projects = new String[lenP];
                int[] projectsListPositions = new int[lenP];

                for (Project p : projects_data) {
                    if (p.getState() == 0) {
                        projects[ip] = p.getProject_number() + " - " + p.getTitle();
                        projectsListPositions[ip] = i;
                        i++;
                    }
                }
                int proj_id = utils.showQuestionWithMenu(utils.InfoMessage, "Selecione um Projeto", "Selecione o projeto a atualizar", projects);
                Project p = projects_data.get(proj_id);

                String text = Main.authorized_action.showProjectInfo(proj_id) + "\n\nInsira mais anotações:";

                String update = utils.showQuestion(utils.QuestionMessage, "Nova anotação", text);

                Main.authorized_action.updateProject(p.getProject_number(), update);

                utils.printMessage(utils.InfoMessage, "Atualizar Projeto", "Projeto atualizado com sucesso!");

                /*Fim Atualiza Projeto*/
                break;
        }
        menu();
    }

    public void searchRegist() {
        String search_key = "", output = "Resultados da Pesquisa:";
        boolean finded = false;
        search_key = utils.showQuestion(utils.QuestionMessage, "Pesquisa", "Escreva uma palavra para pesquisar");

        /*Pesquisa Projeto*/
        for (Project p : projects_data) {
            if (p.getManager() == Main.user.getUser_profile()) {
                finded = finded || p.getProject_number().contentEquals(search_key); //Procura o nome
                finded = finded || p.getTitle().contains(search_key); //Procura o NIPC

                if (finded) {
                    output += "\n(" + p.getProject_number() + ") " + p.getTitle();
                }
            }
        }
        /*Fim Pesquisa Projeto*/

        utils.printMessage(utils.InfoMessage, "Resultados da Pesquisa", output);
        menu();
    }

    public void listRegist() {
        String output = "", title = "";
        int i = 0;
        /*Lista Projetos*/

        int advisor_profile = Main.user.getUser_profile();

        ArrayList<Project> advisor_projects = new ArrayList<Project>();

        for (Project p : projects_data) {
            if (p.getAdvisor() == advisor_profile) {
                advisor_projects.add(p);
            }
        }
        sortByClassification sort_by_classification = new sortByClassification();

        Collections.sort(advisor_projects, sort_by_classification);

        for (Project p : advisor_projects) {
            output += "\n(" + p.getProject_number() + ") " + p.getTitle();
        }
        title = "Resultado da Listagem";
        /*Fim Informações*/

        utils.printMessage(utils.InfoMessage, title, output);
        menu();
    }

    public void profile() {
        int user_id = Main.user.getUser_profile();
        String output = Main.authorized_action.showProfile(user_id);
        utils.printMessage(utils.InfoMessage, "Perfil", output);
        menu();
    }

}
