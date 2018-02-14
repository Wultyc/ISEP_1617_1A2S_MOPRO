
package ProjectButterfly_core;

import java.io.Serializable;


public class Student extends Person implements Serializable{
    
    private int num_mec;
    private String address;

    public Student(String name, Date bornDate, String cc, String nif, int num_mec, String address) {
        super(name, bornDate, cc, nif);
        this.num_mec = num_mec;
        this.address = address;
    }
    
    @Override
    public String getEmail() {
        String email;
        email = getNum_mec() + "@isep.ipp.pt";
    return email;
    }

    @Override
    public String getPersonType() {
        return "Student";
    }
    
    /**
     * @param adress the adress to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the num_mec
     */
    public int getNum_mec() {
        return num_mec;
    }

    /**
     * @return the adress
     */
    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return super.getName() + "\t\t" + getNum_mec() + "\t\t" + getEmail();
    }
    
}
