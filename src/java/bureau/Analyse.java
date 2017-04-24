/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bureau;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Jessica Ghenassia
 */
@Entity
public class Analyse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String dateDemande; 
    @Column
    private String dateRealisation;
    @Column
    private String statut; 
    @Column
    private String serviceDemandeur;

    @ManyToOne(cascade = CascadeType.ALL)
    private NomenclatureActe typeActe;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Resultat resultat;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Admission patient; 

    public Analyse(){
        this.dateDemande = ""; 
        this.dateRealisation = ""; 
        this.statut = StatutAnalyse.DEMANDEE.toString(); 
        this.serviceDemandeur = UniteFonctionnelle.INCONNU.toString(); 
        this.typeActe = null; 
        this.resultat = null; 
        this.patient = null; 
    }
    
    public Analyse(Admission patient){
        this.dateDemande = ""; 
        this.dateRealisation = ""; 
        this.statut = StatutAnalyse.DEMANDEE.toString(); 
        this.serviceDemandeur = null; 
        this.typeActe = null; 
        this.resultat = null; 
        this.patient = patient; 
    }
    
    public Analyse(Admission patient, String uf, String dateDemande){
        this.dateDemande = dateDemande; 
        this.dateRealisation = ""; 
        this.statut = StatutAnalyse.DEMANDEE.toString(); 
        this.serviceDemandeur = uf; 
        this.typeActe = null; 
        this.resultat = null; 
        this.patient = patient; 
    }
    
    public Admission getPatient() {
        return patient;
    }

    public void setPatient(Admission patient) {
        this.patient = patient;
    }

    public String getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(String dateDemande) {
        this.dateDemande = dateDemande;
    }

    public String getDateRealisation() {
        return dateRealisation;
    }

    public void setDateRealisation(String dateRealisation) {
        this.dateRealisation = dateRealisation;
    }

    public StatutAnalyse getStatut() {
        return StatutAnalyse.valueOf(statut);
    }

    public void setStatut(StatutAnalyse statut) {
        this.statut = statut.toString();
    }

    public UniteFonctionnelle getServiceDemandeur() {
        return UniteFonctionnelle.valueOf(serviceDemandeur);
    }

    public void setServiceDemandeur(UniteFonctionnelle serviceDemandeur) {
        this.serviceDemandeur = serviceDemandeur.toString();
    }

    public NomenclatureActe getTypeActe() {
        return typeActe;
    }

    public void setTypeActe(NomenclatureActe typeActe) {
        this.typeActe = typeActe;
    }

    public Resultat getResultat() {
        return resultat;
    }

    public void setResultat(Resultat resultat) {
        this.resultat = resultat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Analyse)) {
            return false;
        }
        Analyse other = (Analyse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bureau.Analyse[ id=" + id + " ]";
    }
    
}
