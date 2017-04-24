/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bureau;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

/**
 *
 * @author Nicolas Singer <Nicolas.Singer@gmail.com>
 */
public class Services {
    
    EntityManagerFactory fact;
    EntityManager em;

    public Services(EntityManagerFactory fact) {
        this.fact = fact;
        this.em = fact.createEntityManager();
    }
    
    
    public void close() {
        em.close();
    }
    
    //////////////////////////////////////// CRAYON ET BOITE ///////////////////////////////////////////
    public void newCrayon(Crayon cr) {
	em.getTransaction( ).begin( );
        em.persist(cr);
        em.getTransaction().commit();
    }

    public Crayon newCrayon(String couleur) {
        Crayon p = new Crayon();
        p.setCouleur(couleur);
     
	em.getTransaction( ).begin( );
        em.persist(p);
        em.getTransaction().commit();
      
        return p;
    }
    
    public void removeCrayon(int id) {
       
        Crayon cr = em.find( Crayon.class, id );
	em.getTransaction( ).begin( );
        em.remove(cr);
        em.getTransaction().commit();
       
    }
    
  
    public void deleteBoite(int id) {
       
        Boite b = em.find( Boite.class, id );
	em.getTransaction( ).begin( );
        em.remove(b);
        em.getTransaction().commit();
       
    }
  
    public void editCrayon(Crayon cr) {
      
	em.getTransaction( ).begin( );
        em.merge(cr);
        em.getTransaction().commit();
     
    }
    
    public Crayon getCrayonsById(int id) {
       
	Crayon res = em.find( Crayon.class, id );
      
        return res;
    }
    
    public List<Crayon> getAllCrayons() {
	TypedQuery<Crayon> query = em.createQuery("SELECT c FROM Crayon c", Crayon.class);
        List<Crayon> res = query.getResultList();
        return res;
    }
    
     public List<Crayon> getAllCrayonsSansBoite() {
        // va chercher la liste des crayons qui ne sont pas affectés à une boite, cad les crayons pour lesquels
        // il n'existe pas de boite les contenant. D'où la requête.
	TypedQuery<Crayon> query = em.createQuery("SELECT c FROM Crayon c WHERE  NOT EXISTS (SELECT b FROM Boite b, IN (b.crayons) cr WHERE cr = c)", Crayon.class);
        List<Crayon> res = query.getResultList();
        return res;
    }
    
    public List<Boite> getAllBoites() {
      
	TypedQuery<Boite> query = em.createQuery("SELECT b FROM Boite b", Boite.class);
        List<Boite> res = query.getResultList();
      
        return res;
    }
    
    public List<Crayon> getCrayonsByCouleurId(String couleur) {
     
        TypedQuery<Crayon> query = em.createQuery("SELECT c FROM Crayon c WHERE c.couleur = :couleur", Crayon.class)
                .setParameter("couleur",couleur);
        List<Crayon> res = query.getResultList();
     
        return res;
    }
    
    public Boite newBoite(List<Crayon> crayons) {
        Boite b = new Boite();
	em.getTransaction( ).begin( );
        b.setCrayons(crayons);
        em.persist(b);
        em.getTransaction().commit();
        return b;
    }
    
    public Boite newBoite(Boite b) {
	em.getTransaction( ).begin( );
        em.persist(b);
        em.getTransaction().commit();
        return b;
    }
    
    public void updateBoite(Boite b) {
        em.getTransaction( ).begin( );
        em.merge(b);
        em.getTransaction().commit();
    }
    
    public Boite getBoiteById(int id) {
        
	Boite res = em.find( Boite.class, id );
       
        return res;
    }
    
    public List<Boite> getBoitesByCouleurDeCrayon(String couleur) {
       
        TypedQuery<Boite> query = em.createQuery("SELECT b FROM Boite b JOIN b.crayons c WHERE c.couleur = :couleur", Boite.class)
                .setParameter("couleur",couleur);
        List<Boite> res =  query.getResultList();
       
        return res;
    }
    
    public void deleteAllBoites() {
      
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM Boite").executeUpdate();
        em.getTransaction().commit();
        
    }
    
    public void deleteAllCrayons() {
      
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM Crayon").executeUpdate();
        em.getTransaction().commit();
        
    }
    
    
    /////////////////////////////////////////////// ADMISSION /////////////////////////////////////////////
    
    public void newAdmission(Admission adm) {
	em.getTransaction( ).begin( );
        em.persist(adm);
        em.getTransaction().commit();
    }
    
    public void updateAdmission(Admission adm) {
        em.getTransaction( ).begin( );
        em.merge(adm);
        em.getTransaction().commit();
    }
    
    public List<Admission> getAllAdmissions() {
      
	TypedQuery<Admission> query = em.createQuery("SELECT adm FROM Admission adm", Admission.class);
        List<Admission> res = query.getResultList();
      
        return res;
    }
    
    public Admission getAdmissionById(int id) {
        
	Admission adm = em.find( Admission.class, id );
       
        return adm;
    }
    
    public Admission getAdmissionByIep(String iep) {
        
	TypedQuery<Admission> query = em.createQuery("SELECT adm FROM Admission adm where adm.iep = :iep", Admission.class).setParameter("iep",iep);
        Admission res = query.getSingleResult(); 
   
       
        return res;
    }
    
    public void removeAdmission(int id) {
       
        Admission adm = em.find( Admission.class, id );
	em.getTransaction( ).begin( );
        em.remove(adm);
        em.getTransaction().commit();
       
    }
    
    public void deleteAllAdmission() {
      
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM Admission").executeUpdate();
        em.getTransaction().commit();
        
    }
    
    
    /////////////////////////////////////////////// ANALYSE /////////////////////////////////////////////    
    public void newAnalyse(Analyse ana) {
	em.getTransaction( ).begin( );
        em.persist(ana);
        em.getTransaction().commit();
    }
    
    public void newAnalyse(Admission patient){
        Analyse ana = new Analyse(patient); 
        em.getTransaction( ).begin( );
        em.persist(ana);
        em.getTransaction().commit();
    }
    
        public void updateAnalyse(Analyse ana) {
        em.getTransaction( ).begin( );
        em.merge(ana);
        em.getTransaction().commit();
    }
    
    public List<Analyse> getAllAnalyses() {
      
	TypedQuery<Analyse> query = em.createQuery("SELECT ana FROM Analyse ana", Analyse.class);
        List<Analyse> res = query.getResultList();
      
        return res;
    }
    
    public Analyse getAnalyseById(Long id) {
        
	Analyse ana = em.find( Analyse.class, id );
       
        return ana;
    }
    
//    public List<Analyse> getAnalysesByAdmission(Admission patient) {
//        
//	TypedQuery<Analyse> query = em.createQuery("SELECT a FROM analyse a JOIN a.patient p WHERE p.iep = :patient", Analyse.class).setParameter("patient",patient.getIep());
//        List<Analyse> res =  query.getResultList();
//       
//        return res;
//    }
    
    public void removeAnalyse(int id) {
       
        Analyse ana = em.find( Analyse.class, id );
	em.getTransaction( ).begin( );
        em.remove(ana);
        em.getTransaction().commit();
       
    }
    
    public void deleteAllAnalyse() {
      
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM Analyse").executeUpdate();
        em.getTransaction().commit();
        
    }
    
    /////////////////////////////////////////////// NOMENCLATURE /////////////////////////////////////////////
    public void newNomenclatureActe(NomenclatureActe nomAct) {
	em.getTransaction( ).begin( );
        em.persist(nomAct);
        em.getTransaction().commit();
    }
    
        public void updateNomenclatureActe(NomenclatureActe nomAct) {
        em.getTransaction( ).begin( );
        em.merge(nomAct);
        em.getTransaction().commit();
    }
    
    public List<NomenclatureActe> getAllNomenclatureActes() {
      
	TypedQuery<NomenclatureActe> query = em.createQuery("SELECT nomAct FROM NomenclatureActe nomAct", NomenclatureActe.class);
        List<NomenclatureActe> res = query.getResultList();
      
        return res;
    }
    
    public NomenclatureActe getNomenclatureActeById(int id) {
        
	NomenclatureActe nomAct = em.find( NomenclatureActe.class, id );
       
        return nomAct;
    }
    
    public void removeNomenclatureActe(int id) {
       
        NomenclatureActe nomAct = em.find( NomenclatureActe.class, id );
	em.getTransaction( ).begin( );
        em.remove(nomAct);
        em.getTransaction().commit();
       
    }
    
    public void deleteAllNomenclatureActe() {
      
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM NomenclatureActe").executeUpdate();
        em.getTransaction().commit();
        
    }
    
    /////////////////////////////////////////////// RESULTAT /////////////////////////////////////////////
    public void newResultat(Resultat result) {
	em.getTransaction( ).begin( );
        em.persist(result);
        em.getTransaction().commit();
    }
    
        public void updateResultat(Resultat result) {
        em.getTransaction( ).begin( );
        em.merge(result);
        em.getTransaction().commit();
    }
    
    public List<Resultat> getAllResultats() {
      
	TypedQuery<Resultat> query = em.createQuery("SELECT result FROM Resultat result", Resultat.class);
        List<Resultat> res = query.getResultList();
      
        return res;
    }
    
    public Resultat getResultatById(int id) {
        
	Resultat result = em.find( Resultat.class, id );
       
        return result;
    }
    
    public void removeResultat(int id) {
       
        Resultat result = em.find( Resultat.class, id );
	em.getTransaction( ).begin( );
        em.remove(result);
        em.getTransaction().commit();
       
    }
    
    public void deleteAllResultat() {
      
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM Resultat").executeUpdate();
        em.getTransaction().commit();
        
    }
    
    
}