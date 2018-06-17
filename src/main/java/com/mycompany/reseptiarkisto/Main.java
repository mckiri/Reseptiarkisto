/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reseptiarkisto;

import com.mycompany.reseptiarkisto.database.AnnosDao;
import com.mycompany.reseptiarkisto.database.Database;
import com.mycompany.reseptiarkisto.database.RaakaAineDao;
import com.mycompany.reseptiarkisto.domain.Annos;
import com.mycompany.reseptiarkisto.domain.RaakaAine;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import spark.TemplateEngine;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main{
    
    public static void main(String[] args) throws Exception {
        
        if (System.getenv("PORT") != null) {
            Spark.port(Integer.valueOf(System.getenv("PORT")));
        }
        
        Database db = new Database("jdbc:sqlite:Reseptiarkisto.db");
    
        AnnosDao annosDao = new AnnosDao(db);
        RaakaAineDao aineDao = new RaakaAineDao(db);
        
        //Etusivu
        Spark.get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            
            map.put("annokset", annosDao.findAll());

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());
        
        //Listataan reseptit
        Spark.get("/annokset", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("annokset", annosDao.findAll());
        
            return new ModelAndView(map, "annokset");
        }, new ThymeleafTemplateEngine());
        
        //Lisätään uusi resepti
        Spark.post("/annokset", (req, res) -> {
            HashMap map = new HashMap<>();
            
            Annos a = new Annos(1, req.queryParams("Annos"));
            annosDao.save(a);
            
            map.put("annokset", a);
            
            res.redirect("/annokset");
            return new ModelAndView(map, "annokset");
        }, new ThymeleafTemplateEngine());
        
        //Listataan tietyn reseptin raaka-aineet
        Spark.get("/annokset/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            
            map.put("annos", aineDao.findAllViaKey(Integer.parseInt(req.params("id"))));
            map.put("annos", annosDao.findOne(Integer.parseInt(req.params("id"))));
            
            return new ModelAndView(map, "annos");
        }, new ThymeleafTemplateEngine());
        
        //Listataan raaka-aineet
        Spark.get("/raaka-aineet", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("aineet", aineDao.findAll());
            
            return new ModelAndView(map, "raaka-aineet");
        }, new ThymeleafTemplateEngine());
        
        //Lisätään uusi raaka-aine
        Spark.post("/raaka-aineet", (req, res) -> {
            HashMap map = new HashMap<>();
            
            RaakaAine a  = new RaakaAine(1, req.queryParams("RaakaAine"));
            aineDao.save(a);
            
            map.put("aineet", a);
            
            res.redirect("/raaka-aineet");
            return new ModelAndView(map, "raaka-aineet");
        }, new ThymeleafTemplateEngine());
    }
}
