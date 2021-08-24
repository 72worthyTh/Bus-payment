package ism.com.worthyth.beep.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMomCompte {
        @Expose
        @SerializedName("numcompte")
        private String numCompte;
        @Expose
        @SerializedName("etat")
        private int etat;
        @Expose
        @SerializedName("solde")
        private double solde;
        @Expose
        @SerializedName("success")
        private Boolean success;
        @Expose
        @SerializedName("message")
        private String message;


        public String getNumeroCompte() {
            return numCompte;
        }

        public int getEtat() {
            return etat;
        }

        public double getSolde() {
            return solde;
        }

        public Boolean getSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }


