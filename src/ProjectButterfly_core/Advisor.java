package ProjectButterfly_core;

import java.io.IOException;
import java.io.Serializable;

public class Advisor extends Person implements Serializable {

    private String sigla;

    public Advisor(String name, Date bornDate, String cc, String nif, String sigla) {
        super(name, bornDate, cc, nif);
        this.sigla = sigla;
    }

    @Override
    public String getEmail() {
        String email;
        email = getSigla() + "@isep.ipp.pt";
        return email;
    }

    @Override
    public String getPersonType() {
        return "Advisor";
    }

    /**
     * @return the sigla
     */
    public String getSigla() {
        return sigla;
    }

    @Override
    public String toString() {
        return super.getName() + "\t\t" + getEmail() + "\t\t" + sigla;
    }

}
