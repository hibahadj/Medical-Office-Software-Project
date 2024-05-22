/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package licence.acadc.cabinet.modele.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "patient")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p"),
    @NamedQuery(name = "Patient.findByPatId", query = "SELECT p FROM Patient p WHERE p.patId = :patId"),
    @NamedQuery(name = "Patient.findByPatNom", query = "SELECT p FROM Patient p WHERE p.patNom = :patNom"),
    @NamedQuery(name = "Patient.findByPatPrenom", query = "SELECT p FROM Patient p WHERE p.patPrenom = :patPrenom"),
    @NamedQuery(name = "Patient.findByPatDdn", query = "SELECT p FROM Patient p WHERE p.patDdn = :patDdn"),
    @NamedQuery(name = "Patient.findByPatAdresse", query = "SELECT p FROM Patient p WHERE p.patAdresse = :patAdresse"),
    @NamedQuery(name = "Patient.findByPatGenre", query = "SELECT p FROM Patient p WHERE p.patGenre = :patGenre"),
    @NamedQuery(name = "Patient.findByPatSang", query = "SELECT p FROM Patient p WHERE p.patSang = :patSang"),
    @NamedQuery(name = "Patient.findByPatCreeDate", query = "SELECT p FROM Patient p WHERE p.patCreeDate = :patCreeDate")})
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PAT_ID")
    private Integer patId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PAT_NOM")
    private String patNom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PAT_PRENOM")
    private String patPrenom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAT_DDN")
    @Temporal(TemporalType.DATE)
    private Date patDdn;
    @Size(max = 45)
    @Column(name = "PAT_ADRESSE")
    private String patAdresse;
    @Size(max = 30)
    @Column(name = "PAT_TEL")
    private String patTel;
    @Size(max = 45)
    @Column(name = "PAT_GENRE")
    private String patGenre;
    @Size(max = 100)
    @Column(name = "PAT_SANG")
    private String patSang;
    @Column(name = "PAT_CREE_DATE")
    @Temporal(TemporalType.DATE)
    private Date patCreeDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkFichPat")
    private List<Fichier> fichierList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkOrdPat")
    private List<Ordonnance> ordonnanceList;
    @JoinColumn(name = "FK_USER_PAT", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private CabUser fkUserPat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkDossPat")
    private List<Dossier> dossierList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkRdvPat", fetch = FetchType.EAGER)
    private List<RendezVous> rendezVousList;

    public Patient() {
    }

    public Patient(Integer patId) {
        this.patId = patId;
    }

    public Patient(Integer patId, String patNom, String patPrenom, Date patDdn) {
        this.patId = patId;
        this.patNom = patNom;
        this.patPrenom = patPrenom;
        this.patDdn = patDdn;
    }

    public Integer getPatId() {
        return patId;
    }

    public void setPatId(Integer patId) {
        this.patId = patId;
    }

    public String getPatNom() {
        return patNom;
    }

    public void setPatNom(String patNom) {
        this.patNom = patNom;
    }

    public String getPatPrenom() {
        return patPrenom;
    }

    public void setPatPrenom(String patPrenom) {
        this.patPrenom = patPrenom;
    }

    public Date getPatDdn() {
        return patDdn;
    }

    public void setPatDdn(Date patDdn) {
        this.patDdn = patDdn;
    }

    public String getPatAdresse() {
        return patAdresse;
    }

    public void setPatAdresse(String patAdresse) {
        this.patAdresse = patAdresse;
    }

    public String getPatTel() {
        return patTel;
    }

    public void setPatTel(String patTel) {
        this.patTel = patTel;
    }

    public String getPatGenre() {
        return patGenre;
    }

    public void setPatGenre(String patGenre) {
        this.patGenre = patGenre;
    }

    public String getPatSang() {
        return patSang;
    }

    public void setPatSang(String patSang) {
        this.patSang = patSang;
    }

    public Date getPatCreeDate() {
        return patCreeDate;
    }

    public void setPatCreeDate(Date patCreeDate) {
        this.patCreeDate = patCreeDate;
    }

    public List<Fichier> getFichierList() {
        return fichierList;
    }

    public void setFichierList(List<Fichier> fichierList) {
        this.fichierList = fichierList;
    }

    
    public List<Ordonnance> getOrdonnanceList() {
        return ordonnanceList;
    }

    public void setOrdonnanceList(List<Ordonnance> ordonnanceList) {
        this.ordonnanceList = ordonnanceList;
    }

    public CabUser getFkUserPat() {
        return fkUserPat;
    }

    public void setFkUserPat(CabUser fkUserPat) {
        this.fkUserPat = fkUserPat;
    }

    
    public List<Dossier> getDossierList() {
        return dossierList;
    }

    public void setDossierList(List<Dossier> dossierList) {
        this.dossierList = dossierList;
    }

    
    public List<RendezVous> getRendezVousList() {
        return rendezVousList;
    }

    public void setRendezVousList(List<RendezVous> rendezVousList) {
        this.rendezVousList = rendezVousList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patId != null ? patId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.patId == null && other.patId != null) || (this.patId != null && !this.patId.equals(other.patId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "licence.acadc.cabinet.modele.entity.Patient[ patId=" + patId + " ]";
    }

}
