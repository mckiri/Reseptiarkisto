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
        
        Spark.get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            
            map.put("annokset", annosDao.findAll());

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());
        
        Spark.get("/annokset", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("annokset", annosDao.findAll());

            return new ModelAndView(map, "annokset");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/annokset", (req, res) -> {
            HashMap map = new HashMap<>();
            
            Annos a = new Annos(1, req.queryParams("Annos"));
            annosDao.save(a);
            
            map.put("annokset", a);
            
            res.redirect("/annokset");
            return new ModelAndView(map, "annokset");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/annokset/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            
            map.put("annos", annosDao.findOne(Integer.parseInt(req.params("id"))));
            map.put("raaka-aineet", aineDao.findAll());
            
            return new ModelAndView(map, "annos");
        }, new ThymeleafTemplateEngine());
    }
}
