import java.io.Serializable;

public class Login implements Serializable {
    private String username;
    private String password;
    private String name;

public Login(String username,String password){
    this.username=username;
    this.password=password;
}
public Login(String name,String username,String password){
    this.username=username;
    this.password=password;
    this.name=name;
}
    public String getusername(){
        return username;
    }
    public void setusername(String username){
        this.username=username;
    }
}
