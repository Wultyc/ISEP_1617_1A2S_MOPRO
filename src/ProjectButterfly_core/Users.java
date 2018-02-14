
package ProjectButterfly_core;

import ProjectButterfly_ui.UserInterfaceUtils;
import java.io.IOException;
import java.io.Serializable;

public class Users implements Serializable {
    //Atributos
    private String id;  //identificação do utilizador
    private String pw;  //verificação da autenticidade da identidade do utilizador
    private boolean account_active; //Indicação se a conta esta ativa ou não
    private int user_profile; //Posição na ArrayList de pessoas do perfil do utilizador
    private int permissions = -1;
    
    public Users(String id, String pw, boolean active, int user_profile, int permissions){
        this.id = id;
        this.pw = pw;
        account_active = active;
        this.user_profile = user_profile;
        this.permissions = permissions;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the pw
     */
    public String getPw() {
        return pw;
    }

    /**
     * @param pw the pw to set
     */
    public void setPw(String pw) {
        this.pw = pw;
    }

    /**
     * @return the account_active
     */
    public boolean isAccount_active() {
        return account_active;
    }

    /**
     * @param account_active the account_active to set
     */
    public void setAccount_active(boolean account_active) {
        this.account_active = account_active;
    }

    /**
     * @return the user_profile
     */
    public int getUser_profile() {
        return user_profile;
    }

    /**
     * @return the permissions
     */
    public int getPermissions() {
        return permissions;
    }
    
    
}