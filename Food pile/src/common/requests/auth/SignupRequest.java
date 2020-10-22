package common.requests.auth;

import common.requests.Request;

public class SignupRequest extends Request {
    private String fname;
    private String lname;
    private String username;
    private String password;

    public SignupRequest(String fname,String lname, String username, String password){
        this.username = username;
	    this.lname=lname;
        this.fname = fname;
        this.password = password;
    }
    public String getFname() {
        return fname;
    }
    public String getLname() {
        return lname;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
}
