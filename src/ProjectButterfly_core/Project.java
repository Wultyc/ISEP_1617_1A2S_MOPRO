
package ProjectButterfly_core;

import java.io.Serializable;


public class Project  implements Serializable{

    
    //Atributos
    private String project_number;
    private String title;
    private String description;
    private String about_info;
    
    private float classification;
    
    private int company;
    
    private Date startDate;
    private Date finishDate;
    
    private int state;
    private int student;
    private int advisor;
    private int manager;
    
    String[] states = {"Disponivel", "Atribuido", "Finalizado"};
    
    public Project(String project_number, String title, String description, int company, Date startDate, int student, int advisor, int manager){
        this.project_number = project_number;
        this.title =  title;
        this.description =  description;
        this.company =  company;
        this.startDate =  startDate;
        this.state =  1;
        this.student =  student;
        this.advisor =  advisor;
        this.manager =  manager;
        this.classification = 0;
    }
    
    public Project(String project_number, String title, String description, int company){
        this.project_number = project_number;
        this.title =  title;
        this.description =  description;
        this.company =  company;
        this.state =  0;
        this.classification = 0;
    }

    /**
     * @return the Project_number
     */
    public String getProject_number() {
        return project_number;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the about_info
     */
    public String getAbout_info() {
        return about_info;
    }

    /**
     * @param about_info the about_info to set
     */
    public void setAbout_info(String about_info) {
        this.about_info += "\n" + about_info;
    }
    
    /**
     * @return the classification
     */
    public float getClassification() {
        return classification;
    }

    /**
     * @param classification the classification to set
     */
    public void setClassification(float classification) {
        this.classification = classification;
    }
    
    /**
     * @return the company
     */
    public int getCompany() {
        return company;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }
    /**
     * @param startDate the finishDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the finishDate
     */
    public Date getFinishDate() {
        return finishDate;
    }

    /**
     * @param finishDate the finishDate to set
     */
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    /**
     * @return the state
     */
    public int getState() {
        return state;
    }
    
    /**
     * @return the state
     */
    public String getStatetoString() {
        return states[state];
    }

    /**
     * @param state the state to set
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * @return the student
     */
    public int getStudent() {
        return student;
    }

    /**
     * @return the student
     */
    public void setStudent(int student) {
        this.student = student;
    }

    /**
     * @return the advisor
     */
    public int getAdvisor() {
        return advisor;
    }

    /**
     * @param advisor the advisor to set
     */
    public void setAdvisor(int advisor) {
        this.advisor = advisor;
    }

    /**
     * @return the manager
     */
    public int getManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(int manager) {
        this.manager = manager;
    }
    
    
}
