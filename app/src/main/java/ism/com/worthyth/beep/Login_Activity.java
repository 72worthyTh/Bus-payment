package ism.com.worthyth.beep;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import ism.com.worthyth.beep.api.URLs;
import ism.com.worthyth.beep.chare.SharedPrefManager;
import ism.com.worthyth.beep.model.Users;
import ism.com.worthyth.beep.preferences.RequestHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Login_Activity extends AppCompatActivity {


    EditText editTextUsername, editTextPassword;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //if user presses on login
        //calling the method login
        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });


        findViewById(R.id.changePass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                getSupportActionBar().setTitle("MODIFICATION MOT DE PASSE");
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                progressBar.setVisibility(View.GONE);

            }
        });

        findViewById(R.id.forgotPass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                getSupportActionBar().setTitle("MODIFICATION MOT DE PASSE");
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void userLogin() {
        //first getting the values
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("entrer le nom d'utisateur valide");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("entrer votre mot de passe");
            editTextPassword.requestFocus();
            return;
        }

        //if everything is fine

        class UserLogin extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");

                        //creating a new user object
                        Users user = new Users(
                                userJson.getInt("id"),
                                userJson.getString("nom"),
                                userJson.getString("prenom"),
                                userJson.getString("telephone"),
                                userJson.getString("type"),
                                userJson.getString("compte"),
                                userJson.getInt("etat"),
                                userJson.getString("solde")
                                );

                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                        //starting the profile activity
                       if(userJson.getString("type").equals("Chauffeur")||userJson.getString("type").equals("Commercant")){
                            finish();
                            startActivity(new Intent(getApplicationContext(), WelCome.class));
                       }
                        if(userJson.getString("type").equals("Agent")){
                            finish();
                            startActivity(new Intent(getApplicationContext(), AgentActivity.class));
                        }
                        if(userJson.getString("type").equals("Client")){
                            finish();
                            startActivity(new Intent(getApplicationContext(), Main2Activity.class));}

                    } else {
                        Toast.makeText(getApplicationContext(), "Impossible de se connecter sur le serveur", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object

                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("nom_user", username);
                params.put("passWord", password);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_LOGIN, params);
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();
    }
    }

