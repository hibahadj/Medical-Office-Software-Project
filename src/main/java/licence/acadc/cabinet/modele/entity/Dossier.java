package licence.acadc.cabinet.modele.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "dossier")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Dossier.findAll", query = "SELECT d FROM Dossier d"),
    @NamedQuery(name = "Dossier.findByDossId", query = "SELECT d FROM Dossier d WHERE d.dossId = :dossId"),
    @NamedQuery(name = "Dossier.findByDossNom", query = "SELECT d FROM Dossier d WHERE d.dossNom = :dossNom"),
    @NamedQuery(name = "Dossier.findByDossComm", query = "SELECT d FROM Dossier d WHERE d.dossComm = :dossComm")})
public class Dossier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DOSS_ID")
    private Integer dossId;
    @Size(max = 100)
    @Column(name = "DOSS_NOM")
    private String dossNom;
    @Size(max = 1000)
    @Column(name = "DOSS_COMM")
    private String dossComm;
    @JoinColumn(name = "FK_DOSS_PAT", referencedColumnName = "PAT_ID")
    @ManyToOne(optional = false)
    private Patient fkDossPat;

    public Dossier() {
    }

    public Dossier(Integer dossId) {
        this.dossId = dossId;
    }

    public Integer getDossId() {
        return dossId;
    }

    public void setDossId(Integer dossId) {
        this.dossId = dossId;
    }

    public String getDossNom() {
        return dossNom;
    }

    public void setDossNom(String dossNom) {
        this.dossNom = dossNom;
    }

    public String getDossComm() {
        return dossComm;
    }

    public void setDossComm(String dossComm) {
        this.dossComm = dossComm;
    }

    public Patient getFkDossPat() {
        return fkDossPat;
    }

    public void setFkDossPat(Patient fkDossPat) {
        this.fkDossPat = fkDossPat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dossId != null ? dossId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dossier)) {
            return false;
        }
        Dossier other = (Dossier) object;
        if ((this.dossId == null && other.dossId != null) || (this.dossId != null && !this.dossId.equals(other.dossId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "licence.acadc.cabinet.modele.entity.Dossier[ dossId=" + dossId + " ]";
    }
    
}
