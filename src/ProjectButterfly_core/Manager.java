
package ProjectButterfly_core;

import java.io.Serializable;


public class Manager extends Person implements Serializable{
    
    private int employer;
    private String email;

    public Manager(String name, Date bornDate, String cc, String nif, int employer, String email) {
        super(name, bornDate, cc, nif);
        this.employer = employer;
        this.email = email;
    }

    @Override
    public String getEmail() {
    return email;
    }

    @Override
    public String getPersonType() {
        return "Manager";
    }

    /**
     * @return the employer
     */
    public int getEmployer() {
        return employer;
    }

    /**
     * @param employer the employer to set
     */
    public void setEmployer(int employer) {
        this.employer = employer;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return super.getName() + "\t\t" + getEmail();
    }
    
    
}
