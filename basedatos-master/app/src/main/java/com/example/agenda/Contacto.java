package com.example.agenda;

import java.util.Date;

public  class Contacto {
    int id;
            String usuario;
            String tel;
            String email;
            String fecNac;

    public Contacto(int id, String usuario, String tel, String email,String fecNac) {
        this.id = id;
        this.usuario = usuario;
        this.email = email;
        this.tel = tel;
        this.fecNac = fecNac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFecNac() {
        return fecNac;
    }

    public void setFecNac(String fecNac) {
        this.fecNac = fecNac;
    }



@Override
public String toString(){

    return "Nombre: "+this.usuario+" Email: "+this.email+"\n"+"Tel:"+this.tel+""+" Nacimiento:"+this.fecNac;

}






}