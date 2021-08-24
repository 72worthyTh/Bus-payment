package ism.com.worthyth.beep.chare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import ism.com.worthyth.beep.Login_Activity;
import ism.com.worthyth.beep.model.Users;


public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "S_TAXI";
    private static final String KEY_NOM = "keynom";
    private static final String KEY_PRENOM = "keyprenom";
    private static final String KEY_TEL = "keytel";
    private static final String KEY_ID = "keyid";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEK_TYPE= "keyType";
    private static final String KEY_PHOTO= "keyphoto";
    private static final String KEY_COMPTE= "keycompte";
    private static final String KEY_ETAT= "keyetat";
    private static final String KEY_SOLDE= "keysolde";


    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(Users user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_NOM, user.getNom());
        editor.putString(KEY_PRENOM, user.getPrenom());
        editor.putString(KEY_TEL, user.getTelephone());
        editor.putString(KEK_TYPE, user.getType());
        editor.putString( KEY_COMPTE, user.getCompte());
        editor.putInt(KEY_ETAT, user.getEtat());
        editor.putString(KEY_SOLDE, user.getSolde());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    //this method will give the logged in user
    public Users getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Users(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_NOM, null),
                sharedPreferences.getString(KEY_PRENOM, null),
                sharedPreferences.getString(KEY_TEL, null),
                sharedPreferences.getString(KEK_TYPE,null),
                sharedPreferences.getString(KEY_COMPTE, null),
                sharedPreferences.getInt(KEY_ETAT, -1),
                sharedPreferences.getString(KEY_SOLDE, null)


        );
    }










    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, Login_Activity.class));
    }
}


