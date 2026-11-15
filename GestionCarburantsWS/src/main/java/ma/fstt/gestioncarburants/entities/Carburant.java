
package ma.fstt.gestioncarburants.entities;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "carburant")
@XmlAccessorType(XmlAccessType.FIELD)
public class Carburant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom", length = 100)
    private String nom;

    @Column(name = "description", length = 255)
    private String description;

    @XmlTransient
    @OneToMany(mappedBy = "carburant")
    private List<HistoCarb> historiques = new ArrayList<>();

    public Carburant() {
    }

    public Carburant(String nom, String description) {
        this.nom = nom;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<HistoCarb> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(List<HistoCarb> historiques) {
        this.historiques = historiques;
    }
}
