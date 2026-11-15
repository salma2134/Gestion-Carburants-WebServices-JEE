
package ma.fstt.gestioncarburants.entities;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.List;

@Entity
@Table(name = "station")
@XmlAccessorType(XmlAccessType.FIELD)
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom", length = 100)
    private String nom;

    @Column(name = "ville", length = 100)
    private String ville;

    @Column(name = "adresse", length = 150)
    private String adresse;

    @XmlTransient
    @OneToMany(mappedBy = "station")
    private List<HistoCarb> historiques;

    public Station() {
    }

    public Station(String nom, String ville, String adresse) {
        this.nom = nom;
        this.ville = ville;
        this.adresse = adresse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<HistoCarb> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(List<HistoCarb> historiques) {
        this.historiques = historiques;
    }
}
