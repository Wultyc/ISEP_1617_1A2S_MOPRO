package ProjectButterfly_ui;

import java.io.IOException;
import java.util.ArrayList;
import ProjectButterfly_core.Users;

public class Login {

    /*Nivel de Utilizadores:
        Estudante   (0)
    
        Orientador  (1)
    
        Supervisor  (2)
    
        Admin       (3)
    */
    
    //Atributos
    private int user_pos;       //Possição do utilizador na array list
    private int user_level;     //Nivel de permissões
    private int user_profile;   //Indice do perfil do utilizador na array list a que pertece
    
    //Informação Guardada
    private ArrayList<Users> users_data = Main.pd.getUsers_data();

    /*
     * Constroi o objeto pedindo o ID e Password
     */
    Login() throws IOException, InterruptedException {
        process_login();
    }

    /*
     * Finaliza o processo de login regular
     */
    private void process_login() throws InterruptedException, IOException {
        String userid, pw;
        int pos, user_list_size = users_data.size(); //Numero de registos feitos
        boolean loggedIn = false;
        
        UserInterfaceUtils u = new UserInterfaceUtils();
                
        while(!loggedIn){
            userid = u.readEmail();
            
            pw = u.readPassword();
            
            for(int i=0; i<user_list_size; i++){
                String saved_userID = users_data.get(i).getId();
                String saved_userPW = users_data.get(i).getPw();
                boolean acount_active = users_data.get(i).isAccount_active();
                
                if(userid.equalsIgnoreCase(saved_userID) && pw.equals(saved_userPW) && acount_active){
                    loggedIn = true;
                    user_pos = i;
                    user_profile = users_data.get(i).getUser_profile();
                    user_level = users_data.get(i).getPermissions();
                    //user_level;
                    break;
                }
            }
            
            if(!loggedIn){
                u.printMessage(u.ErrorMessage, "Erro", "Conta de Utilizador invalida.\nPor favor confirma os dados e tente novamente");
            }
        }
        
        u.printMessage(u.InfoMessage, "Login bem sucedido", "Login efetuado com sucesso!");
    }

    /**
     * @return the user_pos
     */
    public int getUser_pos() {
        return user_pos;
    }

    /**
     * @return the user_level
     */
    public int getUser_level() {
        return user_level;
    }

    /**
     * @param user_level the user_level to set
     */
    public void setUser_level(int user_level) {
        this.user_level = user_level;
    }

    /**
     * @return the user_profile
     */
    public int getUser_profile() {
        return user_profile;
    }
}
