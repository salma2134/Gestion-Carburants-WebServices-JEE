
package ma.fstt.gestioncarburants.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "histocarb")
public class HistoCarb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "prix", precision = 10, scale = 2)
    private BigDecimal prix;

    @ManyToOne
    @JoinColumn(name = "id_station")
    private Station station;

    @ManyToOne
    @JoinColumn(name = "id_carburant")
    private Carburant carburant;

    public HistoCarb() {
    }

    public HistoCarb(LocalDate date, BigDecimal prix,
                     Station station, Carburant carburant) {
        this.date = date;
        this.prix = prix;
        this.station = station;
        this.carburant = carburant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Carburant getCarburant() {
        return carburant;
    }

    public void setCarburant(Carburant carburant) {
        this.carburant = carburant;
    }
}
