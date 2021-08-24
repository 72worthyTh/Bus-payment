package ism.com.worthyth.beep.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryDivers {
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("compteD")
    private String numeroD;
    @Expose
    @SerializedName("compteC")
    private String numeroC;
    @Expose
    @SerializedName("montant")
    private Double montant;
    @Expose
    @SerializedName("dateOp")
    private String dateop;
    @Expose
    @SerializedName("typeop")
    private String tupeop;
    @Expose
    @SerializedName("success")
    private Boolean success;
    @Expose
    @SerializedName("message")
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }

    public String getNumeroD() {
        return numeroD;
    }

    public String getNumeroC() {
        return numeroC;
    }

    public Double getMontant() {
        return montant;
    }

    public String getDateop() {
        return dateop;
    }

    public String getTupeop() {
        return tupeop;
    }
}
