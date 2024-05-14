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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HADJIEDJ
 */
@Entity
@Table(name = "patient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p")})
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
    @Column(name = "PAT_NOME")
    private String patNome;
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
    @Column(name = "PAT_ADDRESS")
    private String patAddress;
    @Size(max = 45)
    @Column(name = "PAT_GENRE")
    private String patGenre;
    @Size(max = 100)
    @Column(name = "PAT_SANG")
    private String patSang;
    @Size(max = 30)
    @Column(name = "PAT_CREE_PAR")
    private String patCreePar;
    @Column(name = "PAT_CREE_DATE")
    @Temporal(TemporalType.DATE)
    private Date patCreeDate;

    public Patient() {
    }

    public Patient(Integer patId) {
        this.patId = patId;
    }

    public Patient(Integer patId, String patNome, String patPrenom, Date patDdn) {
        this.patId = patId;
        this.patNome = patNome;
        this.patPrenom = patPrenom;
        this.patDdn = patDdn;
    }

    public Integer getPatId() {
        return patId;
    }

    public void setPatId(Integer patId) {
        this.patId = patId;
    }

    public String getPatNome() {
        return patNome;
    }

    public void setPatNome(String patNome) {
        this.patNome = patNome;
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

    public String getPatAddress() {
        return patAddress;
    }

    public void setPatAddress(String patAddress) {
        this.patAddress = patAddress;
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

    public String getPatCreePar() {
        return patCreePar;
    }

    public void setPatCreePar(String patCreePar) {
        this.patCreePar = patCreePar;
    }

    public Date getPatCreeDate() {
        return patCreeDate;
    }

    public void setPatCreeDate(Date patCreeDate) {
        this.patCreeDate = patCreeDate;
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
