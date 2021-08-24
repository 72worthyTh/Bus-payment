package ism.com.worthyth.beep.fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.List;


import ism.com.worthyth.beep.EmailActivity;
import ism.com.worthyth.beep.R;
import ism.com.worthyth.beep.api.ApiClient;
import ism.com.worthyth.beep.api.ApiInterface;
import ism.com.worthyth.beep.chare.SharedPrefManager;
import ism.com.worthyth.beep.interfaces.EditorView;
import ism.com.worthyth.beep.interfaces.MainViewUsers;
import ism.com.worthyth.beep.model.EditorPresenterCompte;
import ism.com.worthyth.beep.model.EditorPresenterUsers;
import ism.com.worthyth.beep.model.MainAdapterClient;
import ism.com.worthyth.beep.model.MainPrisenterClient;
import ism.com.worthyth.beep.model.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragmentAgent extends Fragment implements EditorView, MainViewUsers {

    CardView cardViewClient,cardViewListClient,cardViewRecharge;
    LinearLayout linearLayoutFormClient,linearLayoutFormListeclient,linearLayoutRecharge;

    RecyclerView recyclerViewClient;
    SwipeRefreshLayout swipeRefreshClient;

    MainPrisenterClient presenterClient;
    MainAdapterClient adapterClient;
    MainAdapterClient.ItemClickListener itemClickListener;

    private RecyclerView.LayoutManager layoutManager;
    private ApiInterface apiInterface;

    List<Users> users;




    Button btnScanBarcode;
    SurfaceView surfaceView;
    TextView txtBarcodeValue;
    ImageView imageView;

    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    Button btnAction;
    String intentData = "";
    boolean isEmail = false;
    EditorPresenterCompte presenterCompte;
    ProgressDialog progressDialog;


    EditText et_nom, et_prenomnom,et_tel,et_permis,et_commerce,et_nombout,et_numcompte,et_username,et_password,editMontant,montavalide;
    RadioButton simplecl,rcommerce,rchauffeur;
    TextView textViewpermis,textViewnif,textViewbout;
    Button save;
    EditorPresenterUsers presenter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public HomeFragmentAgent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragmentAgent.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragmentAgent newInstance(String param1, String param2) {
        HomeFragmentAgent fragment = new HomeFragmentAgent();
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
        View v= inflater.inflate(R.layout.fragment_home_fragment_agent, container, false);
        presenter = new EditorPresenterUsers(this);
        presenterCompte = new EditorPresenterCompte(this);
        cardViewClient=v.findViewById(R.id.addClient);
        cardViewListClient=v.findViewById(R.id.listClient);
        cardViewRecharge=v.findViewById(R.id.nouveaurech);
        linearLayoutFormClient=v.findViewById(R.id.form_addclient);
        linearLayoutFormListeclient=v.findViewById(R.id.form_listclient);
        linearLayoutRecharge=v.findViewById(R.id.form_recharger);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait...");
        btnScanBarcode = v.findViewById(R.id.btnScanBarcode);
        imageView=v.findViewById(R.id.img);
        txtBarcodeValue = v.findViewById(R.id.txtBarcodeValue);
        editMontant= v.findViewById(R.id.id_montatpayer);
        surfaceView = v.findViewById(R.id.surfaceView);
        btnAction = v.findViewById(R.id.btnAction);

        et_nom=v.findViewById(R.id.editTextNom);
        et_prenomnom=v.findViewById(R.id.editTextPrenom);

        et_tel=v.findViewById(R.id.editTextTelephone);

        et_permis=v.findViewById(R.id.editpermis);

        et_commerce=v.findViewById(R.id.editIdComerce);

        et_nombout=v.findViewById(R.id.editNomButique);

        et_numcompte=v.findViewById(R.id.editnumcompte);

        et_username=v.findViewById(R.id.editTextUsername);

        et_password=v.findViewById(R.id.editTextPassword);
        rcommerce=v.findViewById(R.id.radioBtnCom);
        simplecl=v.findViewById(R.id.radioBtnSimple);
        rchauffeur=v.findViewById(R.id.radioBtnchauf);
        textViewbout=v.findViewById(R.id.idBout);
        textViewnif=v.findViewById(R.id.idNif);
        textViewpermis=v.findViewById(R.id.idP);
        save=v.findViewById(R.id.buttonSave);
        montavalide=v.findViewById(R.id.id_montantvalide);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Saving...");

        swipeRefreshClient = v.findViewById(R.id.swipe_refreshclient);
        recyclerViewClient = v.findViewById(R.id.recycler_viewclient);
        recyclerViewClient.setLayoutManager(new LinearLayoutManager(getContext()));


        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewClient.setLayoutManager(layoutManager);
        recyclerViewClient.setHasFixedSize(true);
        fetchContact(0);
        presenterClient = new MainPrisenterClient(this);
        presenterClient.getDataUsers();
        swipeRefreshClient.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenterClient.getDataUsers();
            }
        });

        Users user = SharedPrefManager.getInstance(getContext()).getUser();
        final String ncompte=user.getCompte();








        simplecl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_commerce.setVisibility(View.GONE);
                et_nombout.setVisibility(View.GONE);
                et_permis.setVisibility(View.GONE);
                textViewbout.setVisibility(View.GONE);
                textViewnif.setVisibility(View.GONE);
                textViewpermis.setVisibility(View.GONE);


            }
        });
        rcommerce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_permis.setVisibility(View.GONE);
                et_commerce.setVisibility(View.VISIBLE);
                et_nombout.setVisibility(View.VISIBLE);
                textViewbout.setVisibility(View.VISIBLE);
                textViewnif.setVisibility(View.VISIBLE);
                textViewpermis.setVisibility(View.GONE);
            }
        });

        rchauffeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_commerce.setVisibility(View.GONE);
                et_nombout.setVisibility(View.GONE);
                et_permis.setVisibility(View.VISIBLE);
                textViewbout.setVisibility(View.GONE);
                textViewnif.setVisibility(View.GONE);
                textViewpermis.setVisibility(View.VISIBLE);
            }
        });
        if(simplecl.isChecked()){
            et_commerce.setVisibility(View.GONE);
            et_nombout.setVisibility(View.GONE);
            et_permis.setVisibility(View.GONE);
            textViewbout.setVisibility(View.GONE);
            textViewnif.setVisibility(View.GONE);
            textViewpermis.setVisibility(View.GONE);

        }
        if(rcommerce.isChecked()){

            et_permis.setVisibility(View.GONE);
            et_commerce.setVisibility(View.VISIBLE);
            et_nombout.setVisibility(View.VISIBLE);
            textViewbout.setVisibility(View.VISIBLE);
            textViewnif.setVisibility(View.VISIBLE);
            textViewpermis.setVisibility(View.GONE);
        }
        if(rchauffeur.isChecked()){
            et_commerce.setVisibility(View.GONE);
            et_nombout.setVisibility(View.GONE);
            et_permis.setVisibility(View.VISIBLE);
            textViewbout.setVisibility(View.GONE);
            textViewnif.setVisibility(View.GONE);
            textViewpermis.setVisibility(View.VISIBLE);
        }

        activeForm();
        enregistreUser();

        //pour le camera
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intentData.length() > 0) {
                    if (isEmail)
                        startActivity(new Intent(getContext(), EmailActivity.class).putExtra("email_address", intentData));
                    else {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(intentData)));
                    }
                }
            }
        });

        btnScanBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editMontant.getText().toString().equals("")) {
                    editMontant.setError("entrer le montant s'il te plait");
                    editMontant.requestFocus();
                    return;
                }

                else{
                    double s=Double.parseDouble(editMontant.getText().toString());
                    montavalide.setText(String.valueOf(s));
                    Toast.makeText(getActivity(), "Scan commencer", Toast.LENGTH_SHORT).show();
                    imageView.setVisibility(View.INVISIBLE);
                    surfaceView.setVisibility(View.VISIBLE);
                }
            }
        });
        return v;
    }
public void activeForm(){
        cardViewClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutFormClient.setVisibility(View.VISIBLE);
                linearLayoutFormClient.getSolidColor();
                linearLayoutFormListeclient.setVisibility(View.GONE);
                linearLayoutRecharge.setVisibility(View.GONE);

            }
        });
    cardViewListClient.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            linearLayoutFormClient.setVisibility(View.GONE);
            linearLayoutFormListeclient.setVisibility(View.VISIBLE);
            linearLayoutFormClient.getSolidColor();
            linearLayoutRecharge.setVisibility(View.GONE);

        }
    });
    cardViewRecharge.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            linearLayoutFormClient.setVisibility(View.GONE);
            linearLayoutFormListeclient.setVisibility(View.GONE);
            linearLayoutRecharge.setVisibility(View.VISIBLE);

        }
    });
}
   public void enregistreUser(){
       save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
       String nom = et_nom.getText().toString().trim();
       String prenom = et_prenomnom.getText().toString().trim();
       String telephone = et_tel.getText().toString().trim();
       String permis = et_permis.getText().toString().trim();
       String commerce = et_commerce.getText().toString().trim();
       String nomboutique = et_nombout.getText().toString().trim();
       String compte = et_numcompte.getText().toString().trim();
       String username = et_username.getText().toString().trim();
       String password=et_password.getText().toString().trim();
       String type="";
       if(simplecl.isChecked()){
            type="Client";
            permis="";
            nomboutique="";
            commerce="";
           et_permis.setText("");
           et_commerce.setText("");
           et_nombout.setText("");
           et_commerce.setVisibility(View.GONE);
           et_nombout.setVisibility(View.GONE);
           et_permis.setVisibility(View.GONE);
       }
      else if(rcommerce.isChecked()){
           type="Commercant";
           permis="";
           et_permis.setText("");
           et_permis.setVisibility(View.GONE);
           et_commerce.setVisibility(View.VISIBLE);
           et_nombout.setVisibility(View.VISIBLE);
       }
       else if(rchauffeur.isChecked()){
           type="Chauffeur";
           nomboutique="";
           commerce="";
           et_commerce.setText("");
           et_nombout.setText("");
           et_commerce.setVisibility(View.GONE);
           et_nombout.setVisibility(View.GONE);
           et_permis.setVisibility(View.VISIBLE);
       }




       if (et_nom.getText().toString().equals("")) {
           et_nom.setError("entrer le nom s'il te plait");
           et_nom.requestFocus();
           return;
       }
       if (et_prenomnom.getText().toString().equals("")) {
           et_prenomnom.setError("entrer le prénom s'il te plait");
           et_prenomnom.requestFocus();
           return;
       }

       if (et_tel.getText().toString().equals("")) {
           et_tel.setError("entrer le telephone s'il te plait");
           et_tel.requestFocus();
           return;
       }

       if (et_numcompte.getText().toString().equals("")) {
           et_numcompte.setError("entrer le numéro de compte s'il te plait");
           et_numcompte.requestFocus();
           return;
       }

       if (et_username.getText().toString().equals("")) {
           et_username.setError("entrer le nom utilisateur lors de connexion s'il te plait");
           et_username.requestFocus();
           return;
       }

       if (et_password.getText().toString().equals("")) {
           et_password.setError("entrer le mot de passe s'il te plait");
           et_password.requestFocus();
           return;
       }

       if (et_password.getText().length()<4) {
           et_password.setError("le mot de passe avoir ou mois 4 caractères ");
           et_password.requestFocus();
           return;
       }

               Users user = SharedPrefManager.getInstance(getContext()).getUser();
               final int userConn=user.getId();

               presenter.saveUsers(nom,prenom,telephone,type,permis,commerce,nomboutique,compte,username,password,userConn);

           }
       });

   } // TODO: Rename method, update argument and hook method into UI event


    // Recharge comptes

    private void initialiseDetectorsAndSources() {

        barcodeDetector = new BarcodeDetector.Builder(getContext())
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(getContext(), barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(getActivity(), new
                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                Toast.makeText(getActivity(), "Cammera arreté", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {
                    txtBarcodeValue.post(new Runnable() {
                        @Override
                        public void run() {

try{
                                surfaceView.setVisibility(View.GONE);
                                //btnAction.setText("LAUNCH URL");
                                intentData = barcodes.valueAt(0).displayValue;
                                txtBarcodeValue.setText(intentData);
                                final String comptev=intentData;
                                Users user = SharedPrefManager.getInstance(getContext()).getUser();
                                final String compte=user.getCompte();


                                final double solde=Double.valueOf(montavalide.getText().toString());

                               /* AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                LayoutInflater inflater = requireActivity().getLayoutInflater();
                                builder.setView(inflater.inflate(R.layout.pincode, null));
                                builder.setPositiveButton("Quitter", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {


                                        else {*/
                                presenterCompte.recharge(comptev,compte, solde);
                                 montavalide.setText("");
                                cameraSource.stop();
                                       }
catch (Exception ex){ }
                                        // User clicked OK button*/
                                   /* }
                                });

                                builder.setNegativeButton("Déconecter", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }*/
                               /* });
// 2. Chain together various setter methods to set the dialog characteristics
                                builder.setMessage("Pour terminer l'opération entre le code PIN du client")
                                        .setTitle("CODE PIN");

// 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
                                AlertDialog dialog = builder.create();
                                dialog.show();

*/




                        }
                    });
                }
            }
        });
    }
    @Override
    public void onPause() {
        super.onPause();
        cameraSource.release();
    }

    @Override
    public void onResume() {
        super.onResume();
        initialiseDetectorsAndSources();
    }

    @Override    public void showProgress() {
        progressDialog.show();
    }

    @Override    public void hideProgress() {
        progressDialog.hide();
    }

    @Override    public void onRequestSuccess(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        imageView.setVisibility(View.VISIBLE);
        //back to main activity
    }

    @Override    public void onRequestError(String message) {
        Toast.makeText(getContext(),
                message,
                Toast.LENGTH_LONG).show();
        imageView.setVisibility(View.VISIBLE);

    }

    public void fetchContact(int key){
        Users user = SharedPrefManager.getInstance(getContext()).getUser();
        int ncompte=user.getId();
       key=ncompte;
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Users>> call = apiInterface.getUsersBykey(key);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                //progressBar.setVisibility(View.GONE);
                users = response.body();
                adapterClient = new MainAdapterClient(getContext(),users,itemClickListener);
                recyclerViewClient.setAdapter(adapterClient);
                adapterClient.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                // progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }







    @Override
    public void showLoading() {
        swipeRefreshClient.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshClient.setRefreshing(false);
    }

    @Override
    public void onGetResultUsers(List<Users> user) {
        adapterClient = new MainAdapterClient(getContext(), user, itemClickListener);
        adapterClient.notifyDataSetChanged();
        recyclerViewClient.setAdapter(adapterClient);
        users = user;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }
}
