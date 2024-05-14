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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HADJIEDJ
 */
@Entity
@Table(name = "dossier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dossier.findAll", query = "SELECT d FROM Dossier d")})
public class Dossier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DSS_ID")
    private Integer dssId;
    @Size(max = 255)
    @Column(name = "DSS_CMNT")
    private String dssCmnt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DSS_PAT_ID")
    private int dssPatId;

    public Dossier() {
    }

    public Dossier(Integer dssId) {
        this.dssId = dssId;
    }

    public Dossier(Integer dssId, int dssPatId) {
        this.dssId = dssId;
        this.dssPatId = dssPatId;
    }

    public Integer getDssId() {
        return dssId;
    }

    public void setDssId(Integer dssId) {
        this.dssId = dssId;
    }

    public String getDssCmnt() {
        return dssCmnt;
    }

    public void setDssCmnt(String dssCmnt) {
        this.dssCmnt = dssCmnt;
    }

    public int getDssPatId() {
        return dssPatId;
    }

    public void setDssPatId(int dssPatId) {
        this.dssPatId = dssPatId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dssId != null ? dssId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dossier)) {
            return false;
        }
        Dossier other = (Dossier) object;
        if ((this.dssId == null && other.dssId != null) || (this.dssId != null && !this.dssId.equals(other.dssId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "licence.acadc.cabinet.modele.entity.Dossier[ dssId=" + dssId + " ]";
    }
    
}
