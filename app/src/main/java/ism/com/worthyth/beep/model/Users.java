package ism.com.worthyth.beep.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {
    @Expose
    @SerializedName("id")
    int id;
    @Expose
    @SerializedName("nom")
    String nom;
    @Expose
    @SerializedName("prenom")
    String prenom;
    @Expose
    @SerializedName("telephone")
    String telephone;
    @Expose
    @SerializedName("type")
    String type;
    @Expose
    @SerializedName("permis")
    private String permis;
    @Expose
    @SerializedName("idcommerce")
    private String idCommerce;

    @Expose
    @SerializedName("nomboutique")
    private String nomBoutique;
    @Expose
    @SerializedName("compte")
    String compte;
    @Expose
    @SerializedName("username")
    String nomUser;
    @Expose
    @SerializedName("password")
    String passWord;
    @Expose
    @SerializedName("photo")
    String photo;

    @Expose
    @SerializedName("etat")
    private
    int etat;
    @Expose
    @SerializedName("solde")
    private
    String solde;

    @Expose
    @SerializedName("datecreation")
    String date;
    @Expose
    @SerializedName("success")
    private Boolean success;
    @Expose
    @SerializedName("message")
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public Users(int id, String nom, String prenom, String telephone, String type, String photo,String compte, String nomUser, String passWord) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.type = type;
        this.photo = photo;
        this.compte = compte;
        this.nomUser = nomUser;
        this.passWord = passWord;
    }




    public Users(int id, String nom, String prenom, String telephone,String type, String compte, int etat, String solde) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.type = type;
        this.compte=compte;
        this.etat=etat;
        this.solde=solde;


    }


    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }
    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    public String getPermis() {
        return permis;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public String getNomBoutique() {
        return nomBoutique;
    }

    public void setNomBoutique(String nomBoutique) {
        this.nomBoutique = nomBoutique;
    }

    public String getIdCommerce() {
        return idCommerce;
    }

    public void setIdCommerce(String idCommerce) {
        this.idCommerce = idCommerce;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getSolde() {
        return solde;
    }

    public void setSolde(String solde) {
        this.solde = solde;
    }
}
