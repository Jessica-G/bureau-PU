package bureau;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Nicolas Singer
 */
@Path("generic")
public class RestServices {

    @Context
    private UriInfo context;

    Services serv;

    public RestServices() {
       serv = new Services(DatabaseUtils.fact());
        
//        serv.deleteAllAdmission();
//        serv.deleteAllAnalyse();
//        serv.deleteAllNomenclatureActe();
//        serv.deleteAllResultat();
//        
//      
//        NomenclatureActe nom = new NomenclatureActe("0040", "CARYOTYPE CONSTITUTIONNEL PRENATAL AVEC INCUBATION"); 
//        NomenclatureActe nom1 = new NomenclatureActe("0162", "ANTICORPS ANTIPLAQUETTAIRES : DEPISTAGE"); 
//        NomenclatureActe nom2 = new NomenclatureActe("0183", "CORRECTION DU TEMPS DE THROMBINE"); 
//        NomenclatureActe nom3 = new NomenclatureActe("0269", "ANTIBIOGRAMME BACTERIE AEROBIE"); 
//        NomenclatureActe nom4 = new NomenclatureActe("0524", "LIPASEMIE"); 
//        NomenclatureActe nom5 = new NomenclatureActe("0624", "UR. : CALCIUM"); 
//        
//        serv.newNomenclatureActe(nom);
//        serv.newNomenclatureActe(nom1);
//        serv.newNomenclatureActe(nom2);
//        serv.newNomenclatureActe(nom3);
//        serv.newNomenclatureActe(nom4);
//        serv.newNomenclatureActe(nom5);
//        
//        
//        Admission adm = new Admission("123", "123", "Dupont", "Jean"); 
//        serv.newAdmission(adm);
//        
//        Analyse ana = new Analyse(adm); 
//        ana.setTypeActe(nom5);
//        ana.setDateDemande("09/03/2017");
//        ana.setStatut(StatutAnalyse.REALISEE);
//        ana.setServiceDemandeur(UniteFonctionnelle.URGENCE);
//        ana.setDateRealisation("09/03/2017");
//        
//        Resultat res = new Resultat("2", "09/03/2017", "test");
////        serv.newResultat(res);
//        
//        ana.setResultat(res);
//        serv.newAnalyse(ana);
//        
//        
//        Admission adm2 = new Admission("123", "124", "Dupont", "Jean");
//        serv.newAdmission(adm2);
//        
//        Analyse ana2 = new Analyse(adm); 
//        ana2.setTypeActe(nom2);
//        ana2.setDateDemande("07/03/2017");
//        ana2.setStatut(StatutAnalyse.DEMANDEE);
//        ana2.setServiceDemandeur(UniteFonctionnelle.CARDIOLOGIE);
//        serv.newAnalyse(ana2);
        
        
    }

//////////////////////////////////////////////CRAYONS et BOITES////////////////
    @GET
    @Path("crayons/{id}")
    @Produces("application/json")
    public Crayon getCrayons(@PathParam("id") int id) {
        return serv.getCrayonsById(id);
    }

    @GET
    @Path("crayons")
    @Produces("application/json")
    public List<Crayon> getAllCrayons(@DefaultValue("") @QueryParam("type") String type) {
        if (type.equals("sansboites")) {
            return serv.getAllCrayonsSansBoite();
        } else {
            return serv.getAllCrayons();
        }
    }

    @POST
    @Path("crayons")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Crayon newCrayon(Crayon cr) {
        serv.newCrayon(cr);
        System.out.println("id:" + cr.getId());
        return cr;
    }

    @POST
    @Path("crayons/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editCrayon(Crayon cr) {
        serv.editCrayon(cr);
        return Response.status(200).entity(cr).build();
    }

    @DELETE
    @Path("crayons/{id}")
    public Response removeCrayon(@PathParam("id") int id) {
        serv.removeCrayon(id);
        return Response.status(200).build();
    }

    @GET
    @Path("boites")
    @Produces("application/json")
    public List<Boite> getBoites() {
        return serv.getAllBoites();
    }

    @GET
    @Path("boites/{id}")
    @Produces("application/json")
    public Boite getBoite(@PathParam("id") int id) {
        return serv.getBoiteById(id);
    }

    @POST
    @Path("boites")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Boite newBoite(Boite b) {
        return serv.newBoite(b);

    }

    @POST
    @Path("boites/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editBoite(Boite b) {
        serv.updateBoite(b);
        return Response.status(200).entity(b).build();
    }

    @DELETE
    @Path("boites/{id}")
    public Response removeBoite(@PathParam("id") int id) {
        serv.deleteBoite(id);
        return Response.status(200).build();
    }

    /*   @GET
    @Path("crayons")
    @Produces("application/json")
    public List<Crayon> getAllCrayons(@DefaultValue("") @QueryParam("type") String type ) {
        if (type.equals("sansboites"))
            return serv.getAllCrayonsSansBoite();
        else return serv.getAllCrayons();
    }*/
    
    ///////////////////////////// ADMISSION /////////////////////////////////
    @GET
    @Path("admissions/{id}")
    @Produces("application/json")
    public String getAdmission(@PathParam("id") int id) {
       // return serv.getAdmissionById(id); 
       return ("OK getAdmission"); 
    }
    
    @GET
    @Path("admissions/iep/{iep}")
    @Produces("application/json")
    public Admission getAdmissionByIep(@PathParam("iep") int iep) {
        return serv.getAdmissionById(iep); 
    }

    @GET
    @Path("admissions")
    @Produces("application/json")
    public List<Admission> getAllAdmission() {
       return serv.getAllAdmissions(); 
    }

    @POST
    @Path("admissions")
    @Consumes("application/json")
    @Produces("application/json")
    public Admission newAdmission(String json) {
        Gson gs = new Gson(); 
        Admission adm = gs.fromJson(json, Admission.class); 
        serv.newAdmission(adm);
        System.out.println("id:" + adm.getId());
        return adm;
    }

    @POST
    @Path("admissions/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editAdmission(Admission adm) {
        serv.updateAdmission(adm);
        return Response.status(200).entity(adm).build();
    }

    @DELETE
    @Path("admissions/{id}")
    public Response removeAdmission(@PathParam("id") int id) {
        serv.removeAdmission(id);
        return Response.status(200).build();
    }
    
    
    ///////////////////////////// ANALYSE /////////////////////////////////
    
        @GET
    @Path("analyses/{id}")
    @Produces("application/json")
    public Analyse getAnalyse(@PathParam("id") Long id) {
        return serv.getAnalyseById(id); 
    }
    
//    @GET
//    @Path("analyses/admission/{patient}")
//    @Produces("application/json")
//    public List<Analyse> getAnalysesByAdmission(@PathParam("patient") Admission patient) {
//        return serv.getAnalysesByAdmission(patient); 
//    }

    @GET
    @Path("analyses")
    @Produces("application/json")
    public List<Analyse> getAllAnalyse() {
       return serv.getAllAnalyses(); 
    }

    @POST
    @Path("analyses")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Analyse newAnalyse(Analyse ana) {
        serv.newAnalyse(ana);
        System.out.println("id:" + ana.getId());
        return ana;
    }
    
    @POST
    @Path("analyses/admission")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Analyse newAnalyseWithAdmission(String json) {

        JsonElement jsonTree = new JsonParser().parse(json);
        
        JsonObject jsonObject = jsonTree.getAsJsonObject();
        JsonElement iep = jsonObject.get("iep");
        JsonElement ipp = jsonObject.get("ipp");
        JsonElement nom = jsonObject.get("nom");
        JsonElement prenom = jsonObject.get("prenom");
        JsonElement dateDemande = jsonObject.get("dateDemande");
        JsonElement uf = jsonObject.get("uf");
        
        Admission adm = new Admission(ipp.getAsString(), iep.getAsString(), nom.getAsString(), prenom.getAsString()); 
        serv.newAdmission(adm);
        System.out.println("id:" + adm.getId());
        
        
        Analyse ana = new Analyse(adm, uf.getAsString(), dateDemande.getAsString()); 
        serv.newAnalyse(ana);
        System.out.println("id:" + ana.getId());
        
        
        return ana;
    }
    
    
    @GET
    @Path("factu/{id}")
    @Produces("application/json")
    public String envoiFacture(@PathParam("id") Long id){
        PrintWriter writer = null;
        try {
            Analyse ana = serv.getAnalyseById(id);
//        JsonObject json = new JsonObject();
//        json.addProperty("iep", ana.getPatient().getIep());
//        json.addProperty("ipp", ana.getPatient().getIpp());
//        json.addProperty("code", ana.getTypeActe().getCode());
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Map<String, String> valeurs = new HashMap<String, String>();
        valeurs.put("iep", ana.getPatient().getIep());
        valeurs.put("ipp", ana.getPatient().getIpp());
        valeurs.put("code", ana.getTypeActe().getCode());
        String json = gson.toJson(valeurs);
            writer = new PrintWriter("D:\\Users\\admin\\Documents\\Mirth\\Factu\\factu.txt", "UTF-8");
            writer.println(json);
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RestServices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RestServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        }

        
        return "ok"; 
    }

//    @POST
//    @Path("analyses/admission")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces("application/json")
//    public Analyse newAnalyseByAdmission(Admission adm) {
//        serv.newAnalyse(adm);
//        System.out.println("id:" + adm.getId());
//        return adm;
//    }
//    
    @POST
    @Path("analyse/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editAnalyse(Analyse ana) {
        serv.updateAnalyse(ana);
        return Response.status(200).entity(ana).build();
    }

    @DELETE
    @Path("analyse/{id}")
    public Response removeAnalyse(@PathParam("id") int id) {
        serv.removeAnalyse(id);
        return Response.status(200).build();
    }
    ///////////////////////////// NOMENCLATUREACTE /////////////////////////////////
     @GET
    @Path("nomenclature/{id}")
    @Produces("application/json")
    public NomenclatureActe getNomenclatureActe(@PathParam("id") int id) {
        return serv.getNomenclatureActeById(id); 
    }

    @GET
    @Path("nomenclature")
    @Produces("application/json")
    public List<NomenclatureActe> getAllNomclature() {
       return serv.getAllNomenclatureActes(); 
    }

    @POST
    @Path("nomenclature")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public NomenclatureActe newNomenclature(NomenclatureActe nom) {
        serv.newNomenclatureActe(nom);
        System.out.println("id:" + nom.getId());
        return nom;
    }

    @POST
    @Path("nomenclature/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editNomenclature(NomenclatureActe nom) {
        serv.updateNomenclatureActe(nom);
        return Response.status(200).entity(nom).build();
    }

    @DELETE
    @Path("nomenclature/{id}")
    public Response removeNomenclature(@PathParam("id") int id) {
        serv.removeNomenclatureActe(id);
        return Response.status(200).build();
    }
    
    ///////////////////////////// RESULTAT /////////////////////////////////
     @GET
    @Path("resultat/{id}")
    @Produces("application/json")
    public Resultat getResultat(@PathParam("id") int id) {
        return serv.getResultatById(id); 
    }

    @GET
    @Path("resultat")
    @Produces("application/json")
    public List<Resultat> getAllResultat() {
       return serv.getAllResultats(); 
    }

    @POST
    @Path("resultat")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Resultat newResultat(Resultat res) {
        serv.newResultat(res);
        System.out.println("id:" + res.getId());
        return res;
    }

    @POST
    @Path("resultat/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editResultat(Resultat res) {
        serv.updateResultat(res);
        return Response.status(200).entity(res).build();
    }

    @DELETE
    @Path("resultat/{id}")
    public Response removeResultat(@PathParam("id") int id) {
        serv.removeResultat(id);
        return Response.status(200).build();
    }
    
}
