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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "ordonnance")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Ordonnance.findAll", query = "SELECT o FROM Ordonnance o"),
    @NamedQuery(name = "Ordonnance.findByOrdId", query = "SELECT o FROM Ordonnance o WHERE o.ordId = :ordId"),
    @NamedQuery(name = "Ordonnance.findByOrdDc", query = "SELECT o FROM Ordonnance o WHERE o.ordDc = :ordDc"),
    @NamedQuery(name = "Ordonnance.findByOrdCreeDate", query = "SELECT o FROM Ordonnance o WHERE o.ordCreeDate = :ordCreeDate")})
public class Ordonnance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ORD_ID")
    private Integer ordId;
    @Size(max = 200)
    @Column(name = "ORD_DC")
    private String ordDc;
    @Column(name = "ORD_CREE_DATE")
    @Temporal(TemporalType.DATE)
    private Date ordCreeDate;
    @JoinColumn(name = "FK_ORD_PAT", referencedColumnName = "PAT_ID")
    @ManyToOne(optional = false)
    private Patient fkOrdPat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkOrdLien")
    private List<LienMedOrd> lienMedOrdList;

    public Ordonnance() {
    }

    public Ordonnance(Integer ordId) {
        this.ordId = ordId;
    }

    public Integer getOrdId() {
        return ordId;
    }

    public void setOrdId(Integer ordId) {
        this.ordId = ordId;
    }

    public String getOrdDc() {
        return ordDc;
    }

    public void setOrdDc(String ordDc) {
        this.ordDc = ordDc;
    }

    public Date getOrdCreeDate() {
        return ordCreeDate;
    }

    public void setOrdCreeDate(Date ordCreeDate) {
        this.ordCreeDate = ordCreeDate;
    }

    public Patient getFkOrdPat() {
        return fkOrdPat;
    }

    public void setFkOrdPat(Patient fkOrdPat) {
        this.fkOrdPat = fkOrdPat;
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
        hash += (ordId != null ? ordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordonnance)) {
            return false;
        }
        Ordonnance other = (Ordonnance) object;
        if ((this.ordId == null && other.ordId != null) || (this.ordId != null && !this.ordId.equals(other.ordId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "licence.acadc.cabinet.modele.entity.Ordonnance[ ordId=" + ordId + " ]";
    }
    
}
