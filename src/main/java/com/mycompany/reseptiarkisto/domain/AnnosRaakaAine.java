
package com.mycompany.reseptiarkisto.domain;


public class AnnosRaakaAine {
    private Integer annos_id;
    private Integer raakaAine_id;
    private Integer jarjestys;
    private Integer maara;
    private String ohje;

    public AnnosRaakaAine(Integer annos_id, Integer raakaAine_id, Integer jarjestys, Integer maara, String ohje, Integer getOhje) {
        this.annos_id = annos_id;
        this.raakaAine_id = raakaAine_id;
        this.jarjestys = jarjestys;
        this.maara = maara;
        this.ohje = ohje;
    }
    
    public Integer getMaara(){
        return this.maara;
    }
    
    public String getOhje(){
        return this.ohje;
    }
    
    public Integer getJarjestys(){
        return this.jarjestys;
    }
}
