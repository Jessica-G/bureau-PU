/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bureau.Admission;
import bureau.Analyse;
import bureau.Boite;
import bureau.Crayon;
import bureau.DatabaseUtils;
import bureau.NomenclatureActe;
import bureau.RestServices;
import bureau.Resultat;
import bureau.Services;
import bureau.StatutAnalyse;
import bureau.UniteFonctionnelle;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nicolas Singer <Nicolas.Singer@gmail.com>
 */
public class bureauTest {
    
    static EntityManagerFactory fact;
    
   public bureauTest() {
       
    }
    
    @BeforeClass
    public static void setUpClass() {
         //fact = Persistence.createEntityManagerFactory("bureauPU");
    }
    
    @AfterClass
    public static void tearDownClass() {
        //fact.close();
    }
    
    @Before
    public void setUp() {
       
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    
    
   /*  public void clean() {
        Services serv = new Services(DatabaseUtils.fact());
        serv.deleteAllBoites();
        serv.deleteAllCrayons();
        serv.deleteAllAdmission();
        serv.deleteAllAnalyse();
        serv.deleteAllNomenclatureActe();
        serv.deleteAllResultat();
        
    }
    
        
    @Test
    public void crayon() {
        clean();
        Services serv = new Services(DatabaseUtils.fact());
        Crayon cr = serv.newCrayon("vert");
        assertNotNull(cr); 
        cr = serv.newCrayon("jaune");
        assertNotNull(cr);
        cr = serv.newCrayon("vert");
        assertNotNull(cr);
        List<Crayon> res = serv.getCrayonsByCouleurId("vert");
        assert(!res.isEmpty());
        assert(res.size() == 2);
      
        res = serv.getAllCrayons();
        assert(!res.isEmpty());
        assert(res.size() == 3);
        
    }
    
    @Test
    public void boite() {
        clean();
        Services serv = new Services(DatabaseUtils.fact());
        List<Crayon> liste = new ArrayList<>();
        String[] couleurs = { "rouge", "jaune", "vert" };
        for (String c : couleurs) {
            Crayon cr = new Crayon();
            cr.setCouleur(c);
            liste.add(cr);
        }
        Boite b = serv.newBoite(liste);
        assertNotNull(b);
        
        List<Boite> boites = serv.getBoitesByCouleurDeCrayon("vert");
        assert(!boites.isEmpty());
        assert(!boites.get(0).getCrayons().isEmpty());
        System.out.println(boites.get(0).getCrayons().get(2).getCouleur());
        assert(boites.get(0).getCrayons().size() == 3);
        
    }
    
    @Test
    public void boite2() {
        clean();
        Services serv = new Services(DatabaseUtils.fact());
        
        List<Crayon> liste = new ArrayList<>();
        String[] couleurs = { "rouge", "jaune", "vert" };
        for (String c : couleurs) {
            Crayon cr = new Crayon();
            cr.setCouleur(c);
            liste.add(cr);
        }
        Boite b = serv.newBoite(liste);
        assertNotNull(b);
        
        Crayon cr = new Crayon();
        cr.setCouleur("vert");
        
        b.getCrayons().add(cr);
        
        
        List<Boite> boites = serv.getBoitesByCouleurDeCrayon("vert");
        assert(!boites.isEmpty());
        assert(!boites.get(0).getCrayons().isEmpty());
        assert(boites.get(0).getCrayons().size() == 4);
        
        serv.updateBoite(b);
        
        
    }
    
    
    
        @Test
    public void admision() {
        // ajouter une admission
        clean();
        Services serv = new Services(DatabaseUtils.fact());
        Admission adm = new Admission();
        adm.setPrenom("Jessica");
        adm.setNom("Ghenassia");
        adm.setIpp("0101010101");
        adm.setIep("123");
        serv.newAdmission(adm);

        List<Admission> res = serv.getAllAdmissions();
        assert(!res.isEmpty());
        assert(res.size() == 1);
        
    }  
    
    @Test
    public void getAdmisionByIep() {
        // ajouter une admission
        clean();
        Services serv = new Services(DatabaseUtils.fact());
        Admission adm = new Admission();
        adm.setPrenom("Jessica");
        adm.setNom("Ghenassia");
        adm.setIpp("0101010101");
        adm.setIep("123");
        serv.newAdmission(adm);

        Admission adm2 = serv.getAdmissionByIep("123");
        
        assert(adm2.getIpp().equals(adm.getIpp()));
        
    }
    
    @Test
    public void analyse(){
        clean();
        Services serv = new Services(DatabaseUtils.fact());
        
        Analyse analyse = new Analyse();
        analyse.setDateDemande("12/02/20016");
        analyse.setDateRealisation("13/02/2016");
        analyse.setServiceDemandeur(UniteFonctionnelle.URGENCE);
        NomenclatureActe nomAct = new NomenclatureActe(); 
        nomAct.setCode("4321");
        nomAct.setLibelle("Prise de sang");
        
        analyse.setTypeActe(nomAct);
        serv.newAnalyse(analyse);
        
        List<Analyse> res = serv.getAllAnalyses();
        assert(!res.isEmpty());
        assert(res.size() == 1);
    }
    
        @Test
    public void updateAnalyseWithPatient(){
        clean();
        Services serv = new Services(DatabaseUtils.fact());
        
        Analyse analyse = new Analyse();
        analyse.setDateDemande("12/02/20016");
        analyse.setDateRealisation("13/02/2016");
        analyse.setServiceDemandeur(UniteFonctionnelle.URGENCE);
        analyse.setTypeActe(new NomenclatureActe());
        
        serv.newAnalyse(analyse);
        
        Admission adm = new Admission();
        adm.setPrenom("Jessica");
        adm.setNom("Ghenassia");
        adm.setIpp("0101010101");
        adm.setIep("123");
        analyse.setPatient(adm);
        
        serv.updateAnalyse(analyse);
        
        
        List<Analyse> res = serv.getAllAnalyses();
        assert(!res.isEmpty());
        assert(res.size() == 1);
    }
    
            @Test
    public void updateAnalyseWithResult(){
        clean();
        Services serv = new Services(DatabaseUtils.fact());
        
        Analyse analyse = new Analyse();
        analyse.setDateDemande("12/02/20016");
        analyse.setDateRealisation("13/02/2016");
        analyse.setServiceDemandeur(UniteFonctionnelle.URGENCE);
        analyse.setTypeActe(new NomenclatureActe());
        
        serv.newAnalyse(analyse);
        
        Resultat result = new Resultat(); 
        result.setValeur("10mg");
        result.setDateResultat("16/02/2016");
        result.setCommentaire("glycémie");
        analyse.setResultat(result);
        
        serv.updateAnalyse(analyse);
        
        
        List<Analyse> res = serv.getAllAnalyses();
        assert(!res.isEmpty());
        assert(res.size() == 1);
    }*/
    
 /*  @Test
    public void analyse() {
        clean();
        Services serv = new Services(DatabaseUtils.fact());
        
        Admission adm = new Admission();
        adm.setPrenom("Jessica");
        adm.setNom("Ghenassia");
        adm.setIpp("0101010101");
        adm.setIep("123");
        serv.newAdmission(adm);
        
        Admission adm = serv.getAdmissionByIep("123");
        
        Analyse analyse = new Analyse();
        analyse.setDateDemande("12/02/20016");
        analyse.set
        analyse.statut = StatutAnalyse.DEMANDEE; 
        analyse.serviceDemandeur = null; 
        analyse.typeActe = null; 
        analyse.resultat = null; 
        analyse.patient = null; 
        
        adm.getListeActes().add(acte);
        
        serv.newActe(acte);
        serv.newAdmission(adm);
        List<Acte> res = serv.getAllActes();
        assert(!res.isEmpty());
        assert(res.size() == 1);
        
    }*/
    @Test
   /** public void initDB(){
        Services serv = new Services(DatabaseUtils.fact());
        
  //      serv.deleteAllAdmission();
    //    serv.deleteAllAnalyse();
     //   serv.deleteAllNomenclatureActe();
   //     serv.deleteAllResultat();
        
      
    NomenclatureActe nom = new NomenclatureActe("0040", "CARYOTYPE CONSTITUTIONNEL PRENATAL AVEC INCUBATION"); 
        NomenclatureActe nom1 = new NomenclatureActe("0162", "ANTICORPS ANTIPLAQUETTAIRES : DEPISTAGE"); 
        NomenclatureActe nom2 = new NomenclatureActe("0183", "CORRECTION DU TEMPS DE THROMBINE"); 
        NomenclatureActe nom3 = new NomenclatureActe("0269", "ANTIBIOGRAMME BACTERIE AEROBIE"); 
        NomenclatureActe nom4 = new NomenclatureActe("0524", "LIPASEMIE"); 
        NomenclatureActe nom5 = new NomenclatureActe("0624", "UR. : CALCIUM"); 
        
        serv.newNomenclatureActe(nom);
        serv.newNomenclatureActe(nom1);
        serv.newNomenclatureActe(nom2);
        serv.newNomenclatureActe(nom3);
        serv.newNomenclatureActe(nom4);
        serv.newNomenclatureActe(nom5);
        
        
        Admission adm = new Admission("123", "123", "Dupont", "Jean"); 
        serv.newAdmission(adm);
        
        Analyse ana = new Analyse(adm); 
        ana.setTypeActe(nom5);
        ana.setDateDemande("09/03/2017");
        ana.setStatut(StatutAnalyse.REALISEE);
        ana.setServiceDemandeur(UniteFonctionnelle.URGENCE);
        ana.setDateRealisation("09/03/2017");
        
        Resultat res = new Resultat("2", "09/03/2017", "test");
//        serv.newResultat(res);
        
        ana.setResultat(res);
        serv.newAnalyse(ana);
        
        
        Admission adm2 = new Admission("123", "124", "Dupont", "Jean");
        serv.newAdmission(adm2);
        
        Analyse ana2 = new Analyse(adm); 
        ana2.setTypeActe(nom2);
        ana2.setDateDemande("07/03/2017");
        ana2.setStatut(StatutAnalyse.DEMANDEE);
        ana2.setServiceDemandeur(UniteFonctionnelle.CARDIOLOGIE);
        serv.newAnalyse(ana2);
    
    List<NomenclatureActe> result = serv.getAllNomenclatureActes(); 
    assert(!result.isEmpty()); 
    assert(result.size()==6); 
    }**/
    
    public void admissionTest(){
        RestServices rserv= new RestServices(); 
        rserv.newAdmission("{\"iep\":\"987\", \"ipp\":\"147\", \"nom\":\"Lala\", \"prenom\":\"mimi\"}");
        assert(!rserv.getAdmission(0).isEmpty()); 
        
    }
}
