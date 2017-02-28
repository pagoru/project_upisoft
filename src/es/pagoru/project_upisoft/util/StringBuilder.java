package es.pagoru.project_upisoft.util;

/**
 * Created by pablo on 28/2/17.
 */
public class StringBuilder {

    private String txt;

    public StringBuilder(){
        txt = "";
    }

    public StringBuilder ln(String txt){
        this.txt += txt + "\n";
        return this;
    }

    public String build(){
        return txt;
    }

}
