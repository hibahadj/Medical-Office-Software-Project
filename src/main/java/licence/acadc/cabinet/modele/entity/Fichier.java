package licence.acadc.cabinet.modele.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "fichier")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Fichier.findAll", query = "SELECT f FROM Fichier f"),
    @NamedQuery(name = "Fichier.findByFichId", query = "SELECT f FROM Fichier f WHERE f.fichId = :fichId"),
    @NamedQuery(name = "Fichier.findByFichType", query = "SELECT f FROM Fichier f WHERE f.fichType = :fichType")})
public class Fichier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FICH_ID")
    private Integer fichId;
    @Size(max = 255)
    @Column(name = "FICH_NAME")
    private String fichName;
    @Size(max = 100)
    @Column(name = "FICH_TYPE")
    private String fichType;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "FICH_FILE")
    private byte[] fichFile;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FICH_DATE")
    @Temporal(TemporalType.DATE)
    private Date fichDate;
    @JoinColumn(name = "FK_FICH_PAT", referencedColumnName = "PAT_ID")
    @ManyToOne(optional = false)
    private Patient fkFichPat;

    public Fichier() {
    }

    public Fichier(Integer fichId) {
        this.fichId = fichId;
    }

    public Integer getFichId() {
        return fichId;
    }

    public void setFichId(Integer fichId) {
        this.fichId = fichId;
    }

    public String getFichName() {
        return fichName;
    }

    public void setFichName(String fichName) {
        this.fichName = fichName;
    }

    public String getFichType() {
        return fichType;
    }

    public void setFichType(String fichType) {
        this.fichType = fichType;
    }

    public byte[] getFichFile() {
        return fichFile;
    }

    public void setFichFile(byte[] fichFile) {
        this.fichFile = fichFile;
    }

    public Date getFichDate() {
        return fichDate;
    }

    public void setFichDate(Date fichDate) {
        this.fichDate = fichDate;
    }

    public Patient getFkFichPat() {
        return fkFichPat;
    }

    public void setFkFichPat(Patient fkFichPat) {
        this.fkFichPat = fkFichPat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fichId != null ? fichId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fichier)) {
            return false;
        }
        Fichier other = (Fichier) object;
        if ((this.fichId == null && other.fichId != null) || (this.fichId != null && !this.fichId.equals(other.fichId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "licence.acadc.cabinet.modele.entity.Fichier[ fichId=" + fichId + " ]";
    }

}
