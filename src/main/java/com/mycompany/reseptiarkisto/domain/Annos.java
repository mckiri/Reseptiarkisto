
package com.mycompany.reseptiarkisto.domain;


public class Annos {
    private Integer id;
    private String nimi;

    public Annos(Integer id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }
    
    public void setId (Integer id){
        this.id = id;
    }
    
    public Integer getId (){
        return this.id;
    }
    
    public void setNimi (String nimi){
        this.nimi = nimi;
    }
    
    public String getNimi(){
        return this.nimi;
    }
}
