package it.Digitazon.NeoTokyoComics.presentation.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//Questa classe AuthorDTO può essere utilizzata per incapsulare
// i dati di un libro e passarli tra diverse componenti o livelli di un'applicazione.
// È comune utilizzare oggetti DTO per separare
// la logica di business dai dettagli di implementazione e
// semplificare lo scambio di dati tra diversi componenti del sistema.
public class AuthorDTO {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private long id;
    private String name;
    private String alias;
    private long idType;
    private boolean isDelete;
    private String birthDate;

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public long getIdType() {
        return idType;
    }

    public void setIdType(long idType) {
        this.idType = idType;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Date convertBirthDate() {
        if(this.birthDate == null || this.birthDate.length() ==0){
            return null;
        }
        try {
            return dateFormat.parse(this.birthDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void convertDateToString(Date date) {
        if(date == null){
            this.birthDate = null;
        }
        else{
            this.birthDate = dateFormat.format(date);
        }
    }
}