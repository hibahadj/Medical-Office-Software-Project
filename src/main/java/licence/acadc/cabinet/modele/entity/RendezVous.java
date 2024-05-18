/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package licence.acadc.cabinet.modele.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "rendez_vous")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RendezVous.findAll", query = "SELECT r FROM RendezVous r"),
    @NamedQuery(name = "RendezVous.findByRdvId", query = "SELECT r FROM RendezVous r WHERE r.rdvId = :rdvId"),
    @NamedQuery(name = "RendezVous.findByRdvEtat", query = "SELECT r FROM RendezVous r WHERE r.rdvEtat = :rdvEtat"),
    @NamedQuery(name = "RendezVous.findByRdvDate", query = "SELECT r FROM RendezVous r WHERE r.rdvDate = :rdvDate"),
    @NamedQuery(name = "RendezVous.findByRdvHeure", query = "SELECT r FROM RendezVous r WHERE r.rdvHeure = :rdvHeure"),
    @NamedQuery(name = "RendezVous.findByRdvPriorite", query = "SELECT r FROM RendezVous r WHERE r.rdvPriorite = :rdvPriorite"),
    @NamedQuery(name = "RendezVous.findByRdvDescr", query = "SELECT r FROM RendezVous r WHERE r.rdvDescr = :rdvDescr")})
public class RendezVous implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RDV_ID")
    private Integer rdvId;
    @Size(max = 100)
    @Column(name = "RDV_ETAT")
    private String rdvEtat;
    @Column(name = "RDV_DATE")
    @Temporal(TemporalType.DATE)
    private Date rdvDate;
    @Column(name = "RDV_HEURE")
    @Temporal(TemporalType.TIME)
    private Date rdvHeure;
    @Size(max = 100)
    @Column(name = "RDV_PRIORITE")
    private String rdvPriorite;
    @Size(max = 999)
    @Column(name = "RDV_DESCR")
    private String rdvDescr;
    @JoinColumn(name = "FK_RDV_PAT", referencedColumnName = "PAT_ID")
    @ManyToOne(optional = false)
    private Patient fkRdvPat;

    public RendezVous() {
    }

    public RendezVous(Integer rdvId) {
        this.rdvId = rdvId;
    }

    public Integer getRdvId() {
        return rdvId;
    }

    public void setRdvId(Integer rdvId) {
        this.rdvId = rdvId;
    }

    public String getRdvEtat() {
        return rdvEtat;
    }

    public void setRdvEtat(String rdvEtat) {
        this.rdvEtat = rdvEtat;
    }

    public Date getRdvDate() {
        return rdvDate;
    }

    public void setRdvDate(Date rdvDate) {
        this.rdvDate = rdvDate;
    }

    public Date getRdvHeure() {
        return rdvHeure;
    }

    public void setRdvHeure(Date rdvHeure) {
        this.rdvHeure = rdvHeure;
    }

    public String getRdvPriorite() {
        return rdvPriorite;
    }

    public void setRdvPriorite(String rdvPriorite) {
        this.rdvPriorite = rdvPriorite;
    }

    public String getRdvDescr() {
        return rdvDescr;
    }

    public void setRdvDescr(String rdvDescr) {
        this.rdvDescr = rdvDescr;
    }

    public Patient getFkRdvPat() {
        return fkRdvPat;
    }

    public void setFkRdvPat(Patient fkRdvPat) {
        this.fkRdvPat = fkRdvPat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rdvId != null ? rdvId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RendezVous)) {
            return false;
        }
        RendezVous other = (RendezVous) object;
        if ((this.rdvId == null && other.rdvId != null) || (this.rdvId != null && !this.rdvId.equals(other.rdvId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "licence.acadc.cabinet.modele.entity.RendezVous[ rdvId=" + rdvId + " ]";
    }
    
}
