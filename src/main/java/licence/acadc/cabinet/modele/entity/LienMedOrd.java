/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package licence.acadc.cabinet.modele.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "lien_med_ord")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LienMedOrd.findAll", query = "SELECT l FROM LienMedOrd l"),
    @NamedQuery(name = "LienMedOrd.findByLienId", query = "SELECT l FROM LienMedOrd l WHERE l.lienId = :lienId"),
    @NamedQuery(name = "LienMedOrd.findByLienDose", query = "SELECT l FROM LienMedOrd l WHERE l.lienDose = :lienDose"),
    @NamedQuery(name = "LienMedOrd.findByLienDuree", query = "SELECT l FROM LienMedOrd l WHERE l.lienDuree = :lienDuree"),
    @NamedQuery(name = "LienMedOrd.findByLienEtat", query = "SELECT l FROM LienMedOrd l WHERE l.lienEtat = :lienEtat"),
    @NamedQuery(name = "LienMedOrd.findByLienNbDose", query = "SELECT l FROM LienMedOrd l WHERE l.lienNbDose = :lienNbDose"),
    @NamedQuery(name = "LienMedOrd.findByLienTemps", query = "SELECT l FROM LienMedOrd l WHERE l.lienTemps = :lienTemps")})
public class LienMedOrd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LIEN_ID")
    private Integer lienId;
    @Column(name = "LIEN_DOSE")
    private Integer lienDose;
    @Column(name = "LIEN_DUREE")
    private Integer lienDuree;
    @Size(max = 100)
    @Column(name = "LIEN_ETAT")
    private String lienEtat;
    @Column(name = "LIEN_NB_DOSE")
    private Integer lienNbDose;
    @Size(max = 100)
    @Column(name = "LIEN_TEMPS")
    private String lienTemps;
    @JoinColumn(name = "FK_MED_LIEN", referencedColumnName = "MED_ID")
    @ManyToOne(optional = false)
    private Medicament fkMedLien;
    @JoinColumn(name = "FK_ORD_LIEN", referencedColumnName = "ORD_ID")
    @ManyToOne(optional = false)
    private Ordonnance fkOrdLien;

    public LienMedOrd() {
    }

    public LienMedOrd(Integer lienId) {
        this.lienId = lienId;
    }

    public Integer getLienId() {
        return lienId;
    }

    public void setLienId(Integer lienId) {
        this.lienId = lienId;
    }

    public Integer getLienDose() {
        return lienDose;
    }

    public void setLienDose(Integer lienDose) {
        this.lienDose = lienDose;
    }

    public Integer getLienDuree() {
        return lienDuree;
    }

    public void setLienDuree(Integer lienDuree) {
        this.lienDuree = lienDuree;
    }

    public String getLienEtat() {
        return lienEtat;
    }

    public void setLienEtat(String lienEtat) {
        this.lienEtat = lienEtat;
    }

    public Integer getLienNbDose() {
        return lienNbDose;
    }

    public void setLienNbDose(Integer lienNbDose) {
        this.lienNbDose = lienNbDose;
    }

    public String getLienTemps() {
        return lienTemps;
    }

    public void setLienTemps(String lienTemps) {
        this.lienTemps = lienTemps;
    }

    public Medicament getFkMedLien() {
        return fkMedLien;
    }

    public void setFkMedLien(Medicament fkMedLien) {
        this.fkMedLien = fkMedLien;
    }

    public Ordonnance getFkOrdLien() {
        return fkOrdLien;
    }

    public void setFkOrdLien(Ordonnance fkOrdLien) {
        this.fkOrdLien = fkOrdLien;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lienId != null ? lienId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LienMedOrd)) {
            return false;
        }
        LienMedOrd other = (LienMedOrd) object;
        if ((this.lienId == null && other.lienId != null) || (this.lienId != null && !this.lienId.equals(other.lienId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "licence.acadc.cabinet.modele.entity.LienMedOrd[ lienId=" + lienId + " ]";
    }
    
}
