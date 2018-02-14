
package ProjectButterfly_core;

import java.io.IOException;
import java.io.Serializable;


public class SuperUser extends Person implements Serializable{

    private String sigla = "ADMIN";
    

    public SuperUser(String name, Date bornDate, String cc, String nif) throws IOException{
        super(name, bornDate, cc, nif);    
    }
    
    @Override
    public String getEmail() {
        String email;
        email = sigla + "@isep.ipp.pt";
        return email;
    }

    @Override
    public String getPersonType() {
        return "Admin";
    }

    @Override
    public String toString() {
        return super.getName() + "\t\t" + sigla;
    }
    
}
