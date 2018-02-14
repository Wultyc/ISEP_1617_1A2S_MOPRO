
package ProjectButterfly_core;

import java.io.Serializable;


public abstract class Person implements Serializable {

    //Atributos
    private String name;
    private Date bornDate;
    private String cc;
    private String nif;
    
    //Construtores
    public Person(String name, Date bornDate, String cc, String nif){
        this.name = name;
        this.bornDate = bornDate;
        this.cc = cc;
        this.nif = nif;
    }
    //Metodos
    
    public abstract String getEmail();
    
    public abstract String getPersonType();
    
    public abstract String toString();

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the bornDate
     */
    public Date getBornDate() {
        return bornDate;
    }

    /**
     * @return the cc
     */
    public String getCC() {
        return cc;
    }

    /**
     * @return the nif
     */
    public String getNIF() {
        return nif;
    }
}
