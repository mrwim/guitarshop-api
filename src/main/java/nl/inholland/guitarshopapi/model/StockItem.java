package nl.inholland.guitarshopapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@SequenceGenerator(name = "stockitem_seq", initialValue = 1_000_001)
public class StockItem {

    @Id
    @GeneratedValue(generator = "stockitem_seq")
    private long id;

    @OneToOne
    private Guitar guitar;

    private int quantity;

    public StockItem(Guitar guitar, int quantity) {
        this.guitar = guitar;
        this.quantity = quantity;
    }
}
