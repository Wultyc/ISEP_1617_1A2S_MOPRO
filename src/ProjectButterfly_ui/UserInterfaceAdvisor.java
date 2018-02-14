package ProjectButterfly_ui;

import java.util.ArrayList;
import ProjectButterfly_core.*;
import java.io.IOException;
import java.util.Collections;

public class UserInterfaceAdvisor extends UserInterface {

    //Informação Guardada
    private ArrayList<Person> person_data = Main.pd.getPerson_data();
    private ArrayList<Company> companys_data = Main.pd.getCompanys_data();
    private ArrayList<Project> projects_data = Main.pd.getProjects_data();
    private ArrayList<Users> users_data = Main.pd.getUsers_data();

    public static UserInterfaceUtils utils = new UserInterfaceUtils();

    public UserInterfaceAdvisor() {

    }

    public void startUI() {
        //Onde vai ficar a parte de execução do programa
        menu();
    }

    public void menu() {
        int option = utils.showMenu("Menu Inicial", "Bem-vindo", new String[]{"Editar", "Procurar", "Informações", "Ver Perfil"});
        switch (option) {
            case 0:
                writeMenu();
                break;
            case 1:
                option = utils.showMenu("Menu Pesquisa", "Onde quer pesquisar", new String[]{"Perfil Pessoal", "Projetos"});
                searchRegist(option);
                break;
            case 2:
                option = utils.showMenu("Menu Listagem", "O que pertende listar", new String[]{"Pessoas", "Empresas", "Projetos", "Perfis/Estatisticas"});
                listRegist(option);
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
        int option = utils.showMenu("Alvo", "Onde pretende efetuar esta ação", new String[]{"Editar Informações Pessoais", "Editar de Projetos"});
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
                ans = utils.showConfirmationQuestion("Mudar a password", "Pretende alterar a sua password?");
                if (ans == utils.Yes) {
                    readedAns = utils.readNewPassword();
                    Main.authorized_action.updateUserData(Main.authorized_action.Userpw, id, readedAns);
                    utils.printMessage(utils.InfoMessage, "Password", "Password atualizada com sucesso!");
                }
                /*Fim Atualiza Pessoa*/
                break;

            case 1:
                /*Atualiza Projeto*/
                int i = 0,
                 ip = 0,
                 lenP = 0;

                for (Project p : projects_data) {
                    if (p.getAdvisor() == Main.user.getUser_profile()) {
                        lenP++;
                    }
                }

                String[] projects = new String[lenP];
                int[] projectsListPositions = new int[lenP];

                for (Project p : projects_data) {
                    if (p.getAdvisor() == Main.user.getUser_profile()) {
                        projects[ip] = p.getProject_number() + " - " + p.getTitle();
                        projectsListPositions[ip] = i;
                        ip++;
                    }
                    i++;
                }
                int proj_id = utils.showQuestionWithMenu(utils.InfoMessage, "Selecione um Projeto", "Selecione o projeto a atualizar", projects);
                Project p = projects_data.get(proj_id);

                String text = Main.authorized_action.showProjectInfo(proj_id) + "\n\nInsira mais anotações:";

                int option = utils.showMenu("Atualizar Projeto", text, new String[]{"Adicionar Nota", "Fechar projeto"});

                if (option == 0) {
                    String update = utils.showQuestion(utils.QuestionMessage, "Nova anotação", "Inisira uma anotação");
                    Main.authorized_action.updateProject(p.getProject_number(), update);
                    text = "atualizado";
                } else if (option == 1) {
                    float update = (float) utils.showQuestionDouble(utils.QuestionMessage, "Nova anotação", "Inisira uma anotação");
                    Main.authorized_action.finalizeProject(p.getProject_number(), update);
                    text = "finalizado";
                }

                utils.printMessage(utils.InfoMessage, "Atualizar Projeto", "Projeto " + text + " com sucesso!");

                /*Fim Atualiza Projeto*/
                break;
        }
        menu();
    }

    public void searchRegist(int target) {
        String search_key = "", output = "Resultados da Pesquisa:";
        boolean finded = false;
        search_key = utils.showQuestion(utils.QuestionMessage, "Pesquisa", "Escreva uma palavra para pesquisar");
        switch (target) {
            case 0:
                /*Pesquisa Pessoa*/
                for (Person p : person_data) {
                    finded = finded || p.getName().contentEquals(search_key); //Procura o nome
                    finded = finded || p.getEmail().contains(search_key); //Procura o email
                    finded = finded || p.getCC().contains(search_key); //Procura o email
                    finded = finded || p.getNIF().contains(search_key); //Procura o email

                    if (finded) {
                        output += "\n" + p.getName() + " (" + p.getEmail() + ")";
                    }
                }
                /*Fim Pesquisa Pessoa*/
                break;
            case 1:
                /*Pesquisa Empresa*/
                for (Company c : companys_data) {
                    finded = finded || c.getName().contentEquals(search_key); //Procura o nome
                    finded = finded || c.getNipc().contains(search_key); //Procura o NIPC
                    finded = finded || c.getAdress().contains(search_key); //Procura o Morada
                    finded = finded || c.getTlf().contains(search_key); //Procura o tlf
                    finded = finded || c.getWebsite().contains(search_key); //Procura o email

                    if (finded) {
                        output += "\n" + c.getName() + " (" + c.getTlf() + " | " + c.getWebsite() + ")";
                    }
                }
                /*Fim Pesquisa Empresa*/
                break;
            case 2:
                /*Pesquisa Projeto*/
                for (Project p : projects_data) {
                    finded = finded || p.getProject_number().contentEquals(search_key); //Procura o nome
                    finded = finded || p.getTitle().contains(search_key); //Procura o NIPC

                    if (finded) {
                        output += "\n(" + p.getProject_number() + ") " + p.getTitle();
                    }
                }
                /*Fim Pesquisa Projeto*/
                break;
        }
        utils.printMessage(utils.InfoMessage, "Resultados da Pesquisa", output);
        menu();
    }

    public void listRegist(int target) {
        String output = "", title = "";
        int i = 0;
        switch (target) {
            case 0:
                /*Lista Pessoas*/
                int persons = utils.showMenu("Listar pessoas", "Selecione um estuto", new String[]{"Estudantes", "Orientadore", "Supervisores", "Todos"});

                for (Person p : person_data) {
                    Users u = users_data.get(i);
                    if (persons == 3 || u.getPermissions() == persons) {
                        output += "\n" + p.getName() + " (" + p.getEmail() + ")";
                    }
                    i++;
                }
                title = "Resultado da Listagem";

                /*Fim Fim Lista Pessoas*/
                break;
            case 1:
                /*Lista Empresas*/
                for (Company c : companys_data) {
                    output += "\n" + c.getName() + " (" + c.getTlf() + " | " + c.getWebsite() + ")";
                }
                title = "Resultado da Listagem";
                /*Fim Lista Empresas*/
                break;
            case 2:
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
                /*Fim Fim Lista Projetos*/
                break;
            case 3:
                /*Informações*/
                int info_type = utils.showMenu("Informações", "", new String[]{"Perfil de utilizador", "Perfil de Empresa", "Informações de Projeto", "Estatisticas Gerais"});

                switch (info_type) {
                    case 0:
                        String[] people = Main.authorized_action.showPeople();
                        int person_id = utils.showQuestionWithMenu(utils.QuestionMessage, "Perfis dos utilizadores", "Indique o utiliador que pertende ver", people);
                        output = Main.authorized_action.showProfile(person_id);
                        title = "Perfil";
                        break;
                    case 1:
                        String[] company = Main.authorized_action.showCompany();
                        int company_id = utils.showQuestionWithMenu(utils.QuestionMessage, "Perfis das Empresas", "Indique a empresa que pertende ver", company);
                        output = Main.authorized_action.showCompanysInfo(company_id);
                        title = "Perfil de Empresa";
                        break;
                    case 2:
                        String[] project = Main.authorized_action.showProjects();
                        int project_id = utils.showQuestionWithMenu(utils.QuestionMessage, "Ficheiro do Projeto", "Indique o projeto que pertende ver", project);
                        output = Main.authorized_action.showProjectInfo(project_id);
                        title = "Ficheiro do Projeto";
                        break;
                    case 3:
                        int available_project = 0,
                         assigned_project = 0,
                         active_projects = 0,
                         n_company = companys_data.size(),
                         comp_more_projs = 0;
                        int[] n_projects = new int[n_company];
                        float perc_available = 0,
                         perc_assigned = 0;

                        for (Project p : projects_data) {
                            switch (p.getState()) {
                                case 0:
                                    available_project++;
                                case 1:
                                    assigned_project++;
                            }
                            int proj_company = p.getCompany();
                            n_projects[proj_company]++;
                            if (n_projects[proj_company] > n_projects[comp_more_projs]) {
                                comp_more_projs = proj_company;
                            }
                        }
                        active_projects = available_project + assigned_project;
                        // Percentagem de projetos disponiveis
                        perc_available = (available_project / active_projects) * 100;
                        // Percentagem de projetos atribuidos
                        perc_assigned = (assigned_project / active_projects) * 100;
                        output = "Projetos ativos" + active_projects + "\n"
                                + "A precentagem de projetos disponiveis é:" + perc_available + "\n"
                                + "A precentagem de projetos atribuidoss é:" + "\n" + perc_assigned
                                + "Empresa com mais estágios:" + "\t" + companys_data.get(comp_more_projs).getName();

                        title = "Estatiscas";
                        break;
                }
                /*Fim Informações*/
                break;
        }
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
