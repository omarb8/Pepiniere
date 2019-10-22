/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entits;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "recette")
@NamedQueries({
    @NamedQuery(name = "Recette.findAll", query = "SELECT r FROM Recette r")})
public class Recette implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom_rec")
    private String nomRec;
    @Basic(optional = false)
    @Column(name = "rec_description")
    private String recDescription;
    @Basic(optional = false)
    @Column(name = "type_rec")
    private String typeRec;
    @OneToMany(mappedBy = "idRecette")
    private Collection<LigneRecette> ligneRecetteCollection;

    public Recette() {
    }

    public Recette(Integer id) {
        this.id = id;
    }

    public Recette(Integer id, String nomRec, String recDescription, String typeRec) {
        this.id = id;
        this.nomRec = nomRec;
        this.recDescription = recDescription;
        this.typeRec = typeRec;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomRec() {
        return nomRec;
    }

    public void setNomRec(String nomRec) {
        this.nomRec = nomRec;
    }

    public String getRecDescription() {
        return recDescription;
    }

    public void setRecDescription(String recDescription) {
        this.recDescription = recDescription;
    }

    public String getTypeRec() {
        return typeRec;
    }

    public void setTypeRec(String typeRec) {
        this.typeRec = typeRec;
    }

    public Collection<LigneRecette> getLigneRecetteCollection() {
        return ligneRecetteCollection;
    }

    public void setLigneRecetteCollection(Collection<LigneRecette> ligneRecetteCollection) {
        this.ligneRecetteCollection = ligneRecetteCollection;
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
        if (!(object instanceof Recette)) {
            return false;
        }
        Recette other = (Recette) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entits.Recette[ id=" + id + " ]";
    }
    
}
