
package licence.acadc.cabinet.modele.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "medicament")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Medicament.findAll", query = "SELECT m FROM Medicament m"),
    @NamedQuery(name = "Medicament.findByMedId", query = "SELECT m FROM Medicament m WHERE m.medId = :medId"),
    @NamedQuery(name = "Medicament.findByMedNom", query = "SELECT m FROM Medicament m WHERE m.medNom = :medNom"),
    @NamedQuery(name = "Medicament.findByMedForme", query = "SELECT m FROM Medicament m WHERE m.medForme = :medForme"),
    @NamedQuery(name = "Medicament.findByMedType", query = "SELECT m FROM Medicament m WHERE m.medType = :medType"),
    @NamedQuery(name = "Medicament.findByMedPrix", query = "SELECT m FROM Medicament m WHERE m.medPrix = :medPrix")})
public class Medicament implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MED_ID")
    private Integer medId;
    @Size(max = 100)
    @Column(name = "MED_NOM")
    private String medNom;
    @Size(max = 100)
    @Column(name = "MED_FORME")
    private String medForme;
    @Size(max = 100)
    @Column(name = "MED_TYPE")
    private String medType;
    @Column(name = "MED_PRIX")
    private Integer medPrix;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkMedLien")
    private List<LienMedOrd> lienMedOrdList;

    public Medicament() {
    }

    public Medicament(Integer medId) {
        this.medId = medId;
    }

    public Integer getMedId() {
        return medId;
    }

    public void setMedId(Integer medId) {
        this.medId = medId;
    }

    public String getMedNom() {
        return medNom;
    }

    public void setMedNom(String medNom) {
        this.medNom = medNom;
    }

    public String getMedForme() {
        return medForme;
    }

    public void setMedForme(String medForme) {
        this.medForme = medForme;
    }

    public String getMedType() {
        return medType;
    }

    public void setMedType(String medType) {
        this.medType = medType;
    }

    public Integer getMedPrix() {
        return medPrix;
    }

    public void setMedPrix(Integer medPrix) {
        this.medPrix = medPrix;
    }

    @XmlTransient
    public List<LienMedOrd> getLienMedOrdList() {
        return lienMedOrdList;
    }

    public void setLienMedOrdList(List<LienMedOrd> lienMedOrdList) {
        this.lienMedOrdList = lienMedOrdList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medId != null ? medId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicament)) {
            return false;
        }
        Medicament other = (Medicament) object;
        if ((this.medId == null && other.medId != null) || (this.medId != null && !this.medId.equals(other.medId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "licence.acadc.cabinet.modele.entity.Medicament[ medId=" + medId + " ]";
    }
    
}
