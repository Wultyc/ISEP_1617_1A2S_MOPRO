package ProjectButterfly_ui;

import ProjectButterfly_core.*;
import java.util.ArrayList;

public class Features {

    //Informação Guardada
    private ArrayList<Person> person_data = Main.pd.getPerson_data();
    private ArrayList<Company> companys_data = Main.pd.getCompanys_data();
    private ArrayList<Project> projects_data = Main.pd.getProjects_data();
    private ArrayList<Users> users_data = Main.pd.getUsers_data();

    /**
     * **************************Constantes de Apoio**************************
     */
    public final int UserEmail = 0;
    public final int Userpw = 1;
    public final int UserEmployer = 2;
    public final int UserAdress = 3;

    public final int CompanyAdress = 0;
    public final int CompanyTlf = 1;
    public final int CompanyWebSite = 2;

    /**
     * *****************************Construtores******************************
     */
    public Features() {

    }

    /**
     * ***************************Infos de Login******************************
     */
    public String showUsers() {
        int i = 0;
        String output;
        output = "id    Email    Estado\n";

        for (Users u : users_data) {
            output += i + "    " + u.getId() + "    " + u.isAccount_active();
        }

        return output;
    }

    private void updateActiveState(int user_id) {
        Users u = users_data.get(user_id);
        u.setAccount_active(!u.isAccount_active());
    }

    /**
     * ***************************Infos Pessoais******************************
     */
    public void newStudents(String name, Date bornDate, String cc, String nif, int num_mec, String adress, String pw) {
        person_data.add(new Student(name, bornDate, cc, nif, num_mec, adress));
        int id = person_data.size() - 1;
        users_data.add(new Users(num_mec + "@isep.ipp.pt", pw, true, id, 0));
    }

    public String showStudents() {
        int i = 0;
        String output = "A lista do alunos é: ";
        for (Person p : person_data) {
            if (p.getPersonType().equals("Student")) {
                output += "\n" + i + ") " + p;
            }
            i++;
        }
        return output;
    }

    public void newAdvisor(String name, Date bornDate, String cc, String nif, String sigla, String pw) {
        person_data.add(new Advisor(name, bornDate, cc, nif, sigla));
        int id = person_data.size() - 1;
        users_data.add(new Users(sigla + "@isep.ipp.pt", pw, true, id, 1));
    }

    public String showAdvisors() {
        int i = 0;
        String output = "A lista do orientadores é: ";
        for (Person p : person_data) {
            if (p.getPersonType().equals("Advisor")) {
                output += "\n" + i + ") " + p;
            }
            i++;
        }
        return output;
    }

    public void newManager(String name, Date bornDate, String cc, String nif, int employer, String email, String pw) {
        person_data.add(new Manager(name, bornDate, cc, nif, employer, email));
        int id = person_data.size() - 1;
        users_data.add(new Users(email, pw, true, id, 2));
    }

    public String showManagers() {
        int i = 0;
        String output = "A lista do supervisores é: ";
        for (Person p : person_data) {
            if (p.getPersonType().equals("Manager")) {
                output += "\n" + i + ") " + p;
            }
            i++;
        }
        return output;
    }

    public String[] showPeople() {
        int i = 0;
        String output[] = new String[person_data.size()];
        for (Person p : person_data) {
            output[i] += p.getName() + " (" + p.getEmail() + ")";
            i++;
        }
        return output;
    }

    public String showProfile(int id) {
        String output = "";
        Person p = person_data.get(id);

        output = "Nome: " + p.getName() + "     Email:" + p.getEmail() + "     Data de Nascimento: " + p.getBornDate() + "\n"
                + "CC: " + p.getCC() + "     NIF: " + p.getNIF() + "\n"
                + "Tipo de Utilizador: " + p.getPersonType();

        return output;
    }

    public boolean updateUserData(int type, int user_id, String value) {
        Person p = person_data.get(user_id);

        switch (type) {
            case UserEmail:
                Manager m = (Manager) p;
                Users u = users_data.get(user_id);
                m.setEmail(value);
                u.setId(value);
                break;
            case UserEmployer:
                Manager m2 = (Manager) p;
                m2.setEmployer(Integer.parseInt(value));
                break;
            case UserAdress:
                Student s = (Student) p;
                s.setAddress(value);
                break;
            case Userpw:
                Users u2 = users_data.get(user_id);
                u2.setPw(value);
                break;
        }
        return true;
    }

    /**
     * *****************************Empresas**********************************
     */
    public void newCompany(String name, String adress, String nipc, String tlf, String website) {
        companys_data.add(new Company(name, adress, nipc, tlf, website));
    }

    public void updateCompanyInfo(int comp_id, int target, String value) {
        Company c = companys_data.get(comp_id);
        switch (target) {
            case 1:
                c.setAdress(value);
                break;
            case 2:
                c.setTlf(value);
                break;
            case 3:
                c.setWebsite(value);
                break;
        }
    }

    public String showCompanysInfo(int comp_id) {
        Company c = companys_data.get(comp_id);
        String output = "Nome: " + c.getName() + "\n"
                + "Morada: " + c.getAdress() + "\n"
                + "NIPC: " + c.getNipc() + "    TLF: " + c.getTlf() + "    WebSite: " + c.getWebsite()
                + "Projetos da empresa:\n"
                + showProjectsComp(comp_id);

        return output;
    }

    public String[] showCompany() {
        int i = 0;
        int listSize = companys_data.size();
        String[] output = new String[listSize];
        for (Company c : companys_data) {
            output[i] = c.getName();
            i++;
        }
        return output;
    }

    /**
     * *****************************Projetos**********************************
     */
    public void newAvailableProject(String title, String description, int company) {
        String projN = Main.pd.getProjetNumber();
        projects_data.add(new Project(projN, title, description, company));

    }

    public void newAssignedProject(String title, String description, int company, int student, int advisor, int manager) {
        Date startDate = new Date();
        String projN = Main.pd.getProjetNumber();
        projects_data.add(new Project(projN, title, description, company, startDate, student, advisor, manager));
    }

    public String[] showProjects() {
        int i = 0;
        int listSize = projects_data.size();
        String[] output = new String[listSize];
        for (Project p : projects_data) {
            output[i] = "(" + p.getProject_number() + ")" + p.getTitle();
            i++;
        }
        return output;
    }

    public String showProjectsComp(int compID) {
        int i = 0, cnt = 0;
        String output;
        output = "";
        for (Project p : projects_data) {
            if (p.getCompany() == compID) {
                Student s = (Student)person_data.get(p.getStudent());
                Advisor a = (Advisor)person_data.get(p.getAdvisor());
                Manager m = (Manager)person_data.get(p.getManager());
                output += i + "    " + p.getTitle() + "    " + p.getDescription() + "    " + p.getStartDate() + "    " + p.getFinishDate() + "    " + p.getStatetoString()+ "    " + s.getName() + "(" + s.getNum_mec()+ ")" + "    " + a.getName() + "(" + a.getSigla()+ ")" + "    " + m.getName() + "(" + m.getEmail()+ ")" + "\n";
                cnt++;
            }
            i++;
        }
        output = "A Empresa possui um total de " + cnt + " projetos registados\n\n" + output;
        return output;
    }

    public void assignProject(String proj, int student, int advisor, int manager) {

        Date startDate = new Date();

        for (Project p : projects_data) {
            if (p.getProject_number().equals(proj)) {//Encontramos o projeto
                p.setStudent(student);
                p.setAdvisor(advisor);
                p.setManager(manager);
                p.setStartDate(startDate);
                p.setState(1);
                return; //fim
            }
        }

    }

    public void finalizeProject(String proj, float classification) {
        //Dados do utilizador
        int user_id = Main.user.getUser_profile();
        Date finishDate = new Date();

        for (Project p : projects_data) {
            if (p.getProject_number().equals(proj) && (p.getAdvisor() == user_id || p.getManager() == user_id)) {//Encontramos o projeto
                p.setClassification(classification);
                p.setFinishDate(finishDate);
                return; //fim
            }
        }
    }

    public void updateProject(String proj, String input) {
        //Dados do utilizador
        int user_id = Main.user.getUser_profile();
        Person userp = person_data.get(user_id);
        Date now = new Date();
        String text = "[" + now + "][" + userp.getName() + " - " + userp.getEmail() + "] " + input;

        for (Project p : projects_data) {
            if (p.getProject_number().equals(proj) && (p.getAdvisor() == user_id || p.getManager() == user_id)) {//Encontramos o projeto
                p.setAbout_info(text);
                return; //fim
            }
        }

    }

    public String showProjectInfo(int proj_id) {
        Project p = projects_data.get(proj_id);
        Company c = companys_data.get(p.getCompany());
        Student s = (Student) person_data.get(p.getStudent());
        Advisor a = (Advisor) person_data.get(p.getAdvisor());
        Manager m = (Manager) person_data.get(p.getManager());
        String output = "Nº: " + p.getProject_number() + "    Titulo" + p.getTitle() + "\n"
                + "Estado: " + p.getStatetoString();
        if (p.getState() > 0) {
            output += "Data de Inicio" + p.getStartDate() + "    ";
        }
        if (p.getState() == 2) {
            output += "Data de Fim" + p.getFinishDate();
        }
        output += "\nEmpresa: " + c.getName() + "    ";
        if (p.getState() == 2) {
            output += "Classificação: " + p.getClassification();
        }
        if (p.getState() > 0) {
            output += "Estudante: " + s.getName() + "(" + s.getNum_mec() + ")    Orientador: " + a.getName() + "(" + a.getSigla() + ")    Supervisor: " + m.getName();
        }
        output += "\n\nDescrição: " + p.getDescription() + "\n\nAnotações:" + p.getAbout_info();
        return output;
    }

}
