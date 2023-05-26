package it.Digitazon.NeoTokyoComics.persistence.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


@Entity //Questa annotazione indica che la classe rappresenta un'entità che sarà mappata su una tabella nel database.
@Table(name = "books") // Questa annotazione specifica il nome della tabella nel database che corrisponderà all'entità "Books".
public class Books {
@Id //Questa annotazione indica che l'attributo "id" è la chiave primaria dell'entità.
@GeneratedValue(strategy = GenerationType.IDENTITY) //Questa annotazione specifica la strategia di generazione dei valori per l'attributo "id". In questo caso, viene utilizzata l'identità del database, che assegna un valore automaticamente.
private long id;

@Column(name = "name", nullable = false) //Questa annotazione viene utilizzata per mappare gli attributi alle colonne della tabella nel database.
private String name;

@Column(name = "price", nullable = false) //indica che il valore non può essere nullo
private float price;

@Column(name = "publish_date")
private Date publishDate;

@Column(name = "publishing_house" )
private String publishing_house;

@Column(name = "quantity")
private long quantity;

@Column(name = "isDeleted", nullable = false, columnDefinition = "BOOLEAN NOT NULL DEFAULT false") // specifica la definizione della colonna nel database come booleana non nulla con valore predefinito "false".
private boolean isDeleted;



@ManyToOne  //Questa annotazione indica una relazione molti-a-uno tra due entità. In questo caso, viene utilizzata per stabilire la relazione tra l'entità "Books" e altre entità come "Type" e "Author".
@JoinColumn(name = "id_type", nullable = false) //Questa annotazione viene utilizzata per specificare la colonna di join nella tabella del database per la relazione specificata con l'annotazione @ManyToOne.

private Type type;

@ManyToOne
@JoinColumn(name = "id_author", nullable = false)

private Author author;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublishing_house() {
        return publishing_house;
    }

    public void setPublishing_house(String publishing_house) {
        this.publishing_house = publishing_house;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
