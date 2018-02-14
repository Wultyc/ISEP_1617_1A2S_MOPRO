package ProjectButterfly_core;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;


public class PersistentData implements Serializable{
    
    //Informação guardada
    private ArrayList<Person> person_data = new ArrayList<Person>();
    private ArrayList<Company> companys_data = new ArrayList<Company>();
    private ArrayList<Project> projects_data = new ArrayList<Project>();
    private ArrayList<Users> users_data = new ArrayList<Users>();
    
    private int nProjects, year;
    
    public PersistentData() throws IOException{
        year = 0;
        nProjects = 0;
        
        users_data.add(new Users("su@isep.ipp.pt", "abc", true, 0, 3));
        person_data.add(new SuperUser("Super User", new Date(1,1,1970), "", ""));
        companys_data.add(new Company("ISEP", "Rua Dr. António Bernardino de Almeida, 431\n" + "4249-015 Porto\n" + "Portugal", "22 834 0500", "http://isep.ipp.pt"));
        
        
        
    }
    
    public String getProjetNumber(){
        Date today = new Date();
        String projetc_number;
        NumberFormat formatter = new DecimalFormat("0000");
        if(today.getMonth() < 9){
            if(year != (today.getYear() - 1)){
                year = today.getYear() - 1;
                nProjects = 0;
            }
        }else{
            if(year != today.getYear()){
                year = today.getYear();
                nProjects = 0;
            }
        }
        
        nProjects++;
        
        projetc_number = "" + (year-2000) + formatter.format(nProjects);
        
        return projetc_number;
    }

    /**
     * @return the person_data
     */
    public ArrayList<Person> getPerson_data() {
        return person_data;
    }

    /**
     * @return the companys_data
     */
    public ArrayList<Company> getCompanys_data() {
        return companys_data;
    }

    /**
     * @return the projects_data
     */
    public ArrayList<Project> getProjects_data() {
        return projects_data;
    }

    /**
     * @return the users_data
     */
    public ArrayList<Users> getUsers_data() {
        return users_data;
    }
    
}
