
package ProjectButterfly_core;

import java.io.Serializable;


public class Company implements Serializable {
    private String type;
    private String name;
    private String adress;
    private String nipc;
    private String tlf;
    private String website;
    
    public Company(String name, String adress, String nipc, String tlf, String website){
        this.type = "Companhia Privada";
        this.name = name;
        this.adress = adress;
        this.nipc = nipc;
        this.tlf = tlf;
        this.website = website;
    }
    
    public Company(String name, String adress, String tlf, String website){
        this.type = "ISEP";
        this.name = name;
        this.adress = adress;
        this.tlf = tlf;
        this.website = website;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     * @param adress the adress to set
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * @return the nipc
     */
    public String getNipc() {
        return nipc;
    }

    /**
     * @return the tlf
     */
    public String getTlf() {
        return tlf;
    }

    /**
     * @param tlf the tlf to set
     */
    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }
    
    public String toString(){
        return name + "    " + nipc + "    " + tlf + "    " + website;
    }
}
