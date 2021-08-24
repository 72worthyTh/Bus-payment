package ism.com.worthyth.beep.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import ism.com.worthyth.beep.R;
import ism.com.worthyth.beep.api.ApiInterface;
import ism.com.worthyth.beep.chare.SharedPrefManager;
import ism.com.worthyth.beep.model.GetMomCompte;
import ism.com.worthyth.beep.model.ImageDownloadTask;
import ism.com.worthyth.beep.model.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class CompteFragment extends Fragment{


Button button;
ImageView imageView;
RelativeLayout lay;
TextView textnomUser,testPrenomuser,textTelephoneUser,textTypeUser,textCompterUser,textEtatuser,textSolderUser;
    private ApiInterface apiInterface;

    List<GetMomCompte> comptes;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public CompteFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompteFragment newInstance(String param1, String param2) {
        CompteFragment fragment = new CompteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_compte, container, false);


        textnomUser = v.findViewById(R.id.idnomuser);
        testPrenomuser = v.findViewById(R.id.idprenomuser);
        textTelephoneUser = v.findViewById(R.id.idteluser);
        textTypeUser = v.findViewById(R.id.idtypeuser);
        textCompterUser = v.findViewById(R.id.idcompteuser);
        textEtatuser = v.findViewById(R.id.idetatuser);
        textSolderUser = v.findViewById(R.id.idsoldeuser);
        Users user = SharedPrefManager.getInstance(getContext()).getUser();
        final String ncompte=user.getCompte();
        textSolderUser.setText(user.getSolde());
        textnomUser.setText(user.getNom());
        testPrenomuser.setText(user.getPrenom());
        textTelephoneUser.setText(user.getTelephone());
        textTypeUser.setText(user.getType());
        if(user.getEtat()==1){
          textEtatuser.setText("ACTIF");
        }
        if(user.getEtat()==0){
            textEtatuser.setText("INACTIF");
        }
        if(user.getEtat()==2){
            textEtatuser.setText("BLOQUER");
        }
        textCompterUser.setText(user.getCompte());

        lay=v.findViewById(R.id.lay);
        button = v.findViewById(R.id.idbutton);
        imageView = v.findViewById(R.id.idimageView);
       /*  editText.setText("Mon compte:"+n);
       listView = v.findViewById(R.id.listView);
        getJSON("https://gigi.so-mas.net/getmomSolde.php?key="+compte);*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(!ncompte.equals("")){
                 lay.setVisibility(View.INVISIBLE );
                 new ImageDownloadTask(imageView).execute("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data="+ncompte);
             }
             else{
                 Toast.makeText(getContext(),"",Toast.LENGTH_LONG).show();
             }
            }
        });



        return v;
    }


//Recuperation du solde




}
