package ProjectButterfly_ui;

import java.util.ArrayList;
import ProjectButterfly_core.*;
import java.io.IOException;

public class UserInterfaceSU extends UserInterface {

    //Informação Guardada
    private ArrayList<Person> person_data = Main.pd.getPerson_data();
    private ArrayList<Company> companys_data = Main.pd.getCompanys_data();
    private ArrayList<Project> projects_data = Main.pd.getProjects_data();
    private ArrayList<Users> users_data = Main.pd.getUsers_data();

    public static UserInterfaceUtils utils = new UserInterfaceUtils();

    public UserInterfaceSU() {

    }

    public void startUI() {
        //Onde vai ficar a parte de execução do programa
        menu();
    }

    public void menu() {
        int option = utils.showMenu("Menu Inicial", "Bem-vindo", new String[]{"Inserir/Editar", "Procurar", "Informações", "Ver Perfil"});
        switch (option) {
            case 0:
                writeMenu();
                break;
            case 1:
                option = utils.showMenu("Menu Pesquisa", "Onde quer pesquisar", new String[]{"Pessoas", "Empresas", "Projetos"});
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
        int option = utils.showMenu("Inserir/Editar", "Onde quer fazer", new String[]{"Novo", "Atualizar"});
        int option2 = utils.showMenu("Alvo", "Onde pretende efetuar esta ação", new String[]{"Pessoas", "Empresas", "Projetos"});
        switch (option) {
            case 0:
                newRegist(option2);
                break;
            case 1:
                updateRegist(option2);
                break;
            default:
                menu();
        }
    }

    public void newRegist(int target) {
        switch (target) {
            case 0:
                /*Nova Pessoa*/
                String name = "",
                 bornDate = "",
                 cc = "",
                 nif = "",
                 email = "",
                 pw = "";
                int day = 0,
                 month = 0,
                 year = 0,
                 user_type = -1;

                name = utils.showQuestion(utils.InfoMessage, "Nome", "Introduza o nome do utilizador:");

                while (bornDate.length() == 0) {
                    bornDate = utils.showQuestion(utils.InfoMessage, "Data de nascimento", "Introduza a data de nascimento no formato DD-MM-AAAA:");
                    
                    try {
                        day = Integer.parseInt(bornDate.substring(0, 2));
                        month = Integer.parseInt(bornDate.substring(3, 5));
                        year = Integer.parseInt(bornDate.substring(6, 10));
                        
                        if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1970) {
                            utils.printMessage(utils.ErrorMessage, "Erro", "Data inválida!");
                            bornDate = "";
                        }
                    } catch (NumberFormatException e) {
                        utils.printMessage(utils.ErrorMessage, "Erro", "Data num formato errado!");
                        bornDate = "";
                    }
                }

                cc = utils.showQuestion(utils.InfoMessage, "CC", "Introduza o numero de Cartao de Cidadao:");

                nif = utils.showQuestion(utils.InfoMessage, "NIF", "Introduza o NIF:");

                String[] option = {"Aluno", "Orientador", "Supervisor"};
                user_type = utils.showQuestionWithMenu(utils.InfoMessage, "Tipo de Utilizador", "Indique o tipo de Utilizador", option);

                if (user_type == -1) {
                    menu();
                }

                switch (user_type) {
                    case 0: //Estudante
                        int num_mec = 0;
                        String adress = "";

                        num_mec = utils.showQuestionInt(utils.InfoMessage, "Numero Mecanografico", "Introduza o numero mecanografico");

                        email = num_mec + "@isep.ipp.pt";

                        adress = utils.showQuestion(utils.InfoMessage, "Morada", "Introduza a Morada");

                        Main.authorized_action.newStudents(name, new Date(day, month, year), cc, nif, num_mec, adress, pw);
                        break;

                    case 1: //Orientador
                        String sigla = "";

                        sigla = utils.showQuestion(utils.InfoMessage, "Sigla", "Introduza a sigla do docente");
                        ;

                        email = sigla + "@isep.ipp.pt";

                        Main.authorized_action.newAdvisor(name, new Date(day, month, year), cc, nif, sigla, pw);
                        break;

                    case 2: //Supervisor
                        int employer = 0,
                         i = 0;
                        int listSize = companys_data.size();
                        String[] options = Main.authorized_action.showCompany();
                        employer = utils.showQuestionWithMenu(utils.InfoMessage, "Empresa", "Selecione a Empresa", options);

                        email = utils.showQuestion(utils.InfoMessage, "Email", "Introduza o email");

                        pw = utils.readNewPassword();

                        Main.authorized_action.newManager(name, new Date(day, month, year), cc, nif, employer, email, pw);
                        break;
                }
                /*Fim Nova Pessoa*/
                break;
            case 1:
                /*Nova Empresa*/
                String c_name,
                 c_adress,
                 c_nipc,
                 c_tlf,
                 c_website;

                c_name = utils.showQuestion(utils.InfoMessage, "Nova Empresa", "Nome da Empresa");
                c_adress = utils.showQuestion(utils.InfoMessage, "Nova Empresa", "Morada da Empresa");
                c_nipc = utils.showQuestion(utils.InfoMessage, "Nova Empresa", "NIPC da Empresa");
                c_tlf = utils.showQuestion(utils.InfoMessage, "Nova Empresa", "Nº de telefone da Empresa");
                c_website = utils.showQuestion(utils.InfoMessage, "Nova Empresa", "Website da Empresa");

                Main.authorized_action.newCompany(c_name, c_adress, c_nipc, c_tlf, c_website);

                /*Fim Nova Empresa*/
                break;
            case 2:
                /*Novo Projeto*/
                String title,
                 description;
                int company,
                 student,
                 studentsListPosition,
                 advisorsListPosition,
                 managersListPosition,
                 advidor,
                 manager,
                 state,
                 i = 0,
                 is = 0,
                 ia = 0,
                 im = 0,
                 lenS = 0,
                 lenA = 0,
                 lenM = 0;

                title = utils.showQuestion(utils.InfoMessage, "Novo Projeto", "Título do projeto");
                description = utils.showQuestion(utils.InfoMessage, "Novo Projeto", "Descrição do projeto");

                String[] options = Main.authorized_action.showCompany();

                company = utils.showQuestionWithMenu(utils.InfoMessage, "Empresa", "Selecione a Empresa", options);

                state = utils.showQuestionWithMenu(utils.InfoMessage, "Estado do Projeto", "Selecione um estado", new String[]{"Disponivel", "Atribuido"});

                if (state == 0) {
                    Main.authorized_action.newAvailableProject(title, description, company);
                } else {

                    i = 0;

                    for (Person p : person_data) {
                        switch (p.getPersonType()) {
                            case "Student":
                                lenS++;
                                break;
                            case "Advisor":
                                lenA++;
                                break;
                            case "Manager":
                                Manager m = (Manager) p;
                                if (m.getEmployer() == company) {
                                    lenM++;
                                }
                                break;
                        }
                    }

                    int[] studentsOriginalListPositions = new int[lenS];
                    int[] advisorsOriginalListPositions = new int[lenA];
                    int[] managersOriginalListPositions = new int[lenM];

                    String[] students = new String[lenS];
                    String[] advisors = new String[lenA];
                    String[] managers = new String[lenM];

                    i = 0;

                    for (Person p : person_data) {
                        switch (p.getPersonType()) {
                            case "Student":
                                Student s = (Student) p;
                                students[is] = s.getName() + "(" + s.getNum_mec() + ")";
                                studentsOriginalListPositions[is] = i;
                                is++;
                                break;
                            case "Advisor":
                                Advisor a = (Advisor) p;
                                advisors[ia] = a.getName() + "(" + a.getSigla() + ")";
                                advisorsOriginalListPositions[ia] = i;
                                ia++;
                                break;
                            case "Manager":
                                Manager m = (Manager) p;
                                
                                if (m.getEmployer() == company) {
                                    managers[im] = m.getName() + "(" + m.getEmail() + ")";
                                    managersOriginalListPositions[im] = i;
                                    im++;
                                }
                                break;
                        }
                        i++;
                    }

                    studentsListPosition = utils.showQuestionWithMenu(utils.InfoMessage, "Estudante", "Selecione o estudante", students);
                    advisorsListPosition = utils.showQuestionWithMenu(utils.InfoMessage, "Orientador", "Selecione o orientador", advisors);
                    managersListPosition = utils.showQuestionWithMenu(utils.InfoMessage, "Supervisor", "Selecione o Supervisor", managers);

                    student = studentsOriginalListPositions[studentsListPosition];
                    advidor = advisorsOriginalListPositions[advisorsListPosition];
                    manager = managersOriginalListPositions[managersListPosition];

                    Main.authorized_action.newAssignedProject(title, description, company, student, advidor, manager);
                }
                /*Fim Novo Projeto*/
                break;
        }
        menu();
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
                /*Atualiza Empresa*/
                String[] options = Main.authorized_action.showCompany();

                int comp_id = utils.showQuestionWithMenu(utils.InfoMessage, "Atualizar Empresa", "Selecione a Empresa", options);
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
                    if (p.getAdvisor()== Main.user.getUser_profile()) {
                        lenP++;
                    }
                }

                String[] projects = new String[lenP];

                int[] projectsListPositions = new int[lenP];

                for (Project p : projects_data) {
                    if (p.getAdvisor()== Main.user.getUser_profile()) {
                        projects[ip] = p.getProject_number() + " - " + p.getTitle();
                        projectsListPositions[ip] = i;
                        ip++;
                    }
                        i++;
                }
                int proj_id = utils.showQuestionWithMenu(utils.InfoMessage, "Atribuir Projeto", "Selecione o projeto a atribuir", projects);

                Project selected_project = projects_data.get(proj_id);

                i = 0;
                int lenS = 0,
                 lenA = 0,
                 lenM = 0,
                 is = 0,
                 ia = 0,
                 im = 0;
                int proj_company = selected_project.getCompany();

                for (Person p : person_data) {
                    switch (p.getPersonType()) {
                        case "Student":
                            lenS++;
                            break;
                        case "Advisor":
                            lenA++;
                            break;
                        case "Manager":
                            Manager m = (Manager) p;
                            if (m.getEmployer() == proj_company) {
                                lenM++;
                            }
                            break;
                    }
                }

                int[] studentsOriginalListPositions = new int[lenS];
                int[] advisorsOriginalListPositions = new int[lenA];
                int[] managersOriginalListPositions = new int[lenM];

                String[] students = new String[lenS];
                String[] advisors = new String[lenA];
                String[] managers = new String[lenM];

                i = 0;

                for (Person p : person_data) {
                    switch (p.getPersonType()) {
                        case "Student":
                            Student s = (Student) p;
                            students[is] = s.getName() + "(" + s.getNum_mec() + ")";
                            studentsOriginalListPositions[is] = i;
                            is++;
                            break;
                        case "Advisor":
                            Advisor a = (Advisor) p;
                            advisors[ia] = a.getName() + "(" + a.getSigla() + ")";
                            advisorsOriginalListPositions[ia] = i;
                            ia++;
                            break;
                        case "Manager":
                            Manager m = (Manager) p;
                            if (m.getEmployer() == proj_company) {
                                managers[is] = m.getName() + "(" + m.getEmail() + ")";
                                managersOriginalListPositions[is] = i;
                                is++;
                            }
                            break;
                    }
                    i++;
                }

                int studentsListPosition = utils.showQuestionWithMenu(utils.InfoMessage, "Estudante", "Selecione o estudante", students);
                int advisorsListPosition = utils.showQuestionWithMenu(utils.InfoMessage, "Orientador", "Selecione o orientador", advisors);
                int managersListPosition = utils.showQuestionWithMenu(utils.InfoMessage, "Supervisor", "Selecione o Supervisor", managers);

                int student = studentsOriginalListPositions[studentsListPosition];
                int advidor = advisorsOriginalListPositions[advisorsListPosition];
                int manager = managersOriginalListPositions[managersListPosition];

                String proj_num = selected_project.getProject_number();

                Main.authorized_action.assignProject(proj_num, student, advidor, manager);

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
                int projects = utils.showMenu("Listar Projetos", "Selecione um estado", new String[]{"Disponivel", "Atribuido", "Finalizado", "Todos"});

                for (Project p : projects_data) {
                    Users u = users_data.get(i);
                    if (projects == 3 || p.getState() == projects) {
                        output += "\n(" + p.getProject_number() + ") " + p.getTitle();
                    }
                    i++;
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
                                + "A precentagem de projetos disponiveis é:" + perc_available + "%\n"
                                + "A precentagem de projetos atribuidoss é:" + perc_assigned + "%\n"
                                + "Empresa com mais estágios:" + "\t" + companys_data.get(comp_more_projs).getName();

                        utils.printMessage(utils.InfoMessage, "Percentagem de projetos ativos", output);
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
        output += "\n" + utils.modelationLine;
        for (Project p : projects_data) {
            if (p.getAdvisor() == user_id || p.getStudent() == user_id || p.getManager() == user_id) {
                output += "\n" + p.toString();
            }
        }
        utils.printMessage(utils.InfoMessage, "Perfil", output);
        menu();
    }

}
