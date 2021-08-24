package ism.com.worthyth.beep.fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import ism.com.worthyth.beep.R;
import ism.com.worthyth.beep.chare.SharedPrefManager;
import ism.com.worthyth.beep.interfaces.EditorView;
import ism.com.worthyth.beep.model.EditorPresenterCompte;
import ism.com.worthyth.beep.model.Users;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link RetraitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RetraitFragment extends Fragment implements EditorView {
    Button btnScanBarcode;
    SurfaceView surfaceView;
    TextView txtBarcodeValue;
    ImageView imageView;
    boolean test = false;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    Button btnAction;
    String intentData = "";
    boolean isEmail = false;

    ProgressDialog progressDialog;

    EditorPresenterCompte presenter;
    EditText editMontant;
    EditText montantvalide;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public RetraitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RetraitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RetraitFragment newInstance(String param1, String param2) {
        RetraitFragment fragment = new RetraitFragment();
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
        View v= inflater.inflate(R.layout.fragment_retrait, container, false);
        presenter = new EditorPresenterCompte(this);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait...");
        btnScanBarcode = v.findViewById(R.id.btnScanBarcode);
        imageView=v.findViewById(R.id.img);
        txtBarcodeValue = v.findViewById(R.id.txtBarcodeValue);
        editMontant= v.findViewById(R.id.id_montatpayer);
        montantvalide= v.findViewById(R.id.id_comptescaner);
        surfaceView = v.findViewById(R.id.surfaceView);


        btnScanBarcode.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                test=true;
                if (editMontant.getText().toString().equals("")){
                    editMontant.setError("entrer le montant s'il te plait");
                    editMontant.requestFocus();
                    return;
                }

                else{
                    double s=Double.parseDouble(editMontant.getText().toString());
                    montantvalide.setText(String.valueOf(s));
                    Toast.makeText(getActivity(), "Scan commencer", Toast.LENGTH_SHORT).show();
                    imageView.setVisibility(View.INVISIBLE);
                    surfaceView.setVisibility(View.VISIBLE);
                }
            }
        });

        return v;

    }
    private void initialiseDetectorsAndSources() {

        barcodeDetector = new BarcodeDetector.Builder(getContext())
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(getContext(), barcodeDetector)
                .setRequestedPreviewSize(980, 540)
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


                               /* AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                LayoutInflater inflater = requireActivity().getLayoutInflater();
                                builder.setView(inflater.inflate(R.layout.pincode, null));
                                builder.setPositiveButton("Quitter", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {         else {*/

                            try {


                                surfaceView.setVisibility(View.GONE);
                                //btnAction.setText("LAUNCH URL");
                                intentData = barcodes.valueAt(0).displayValue;
                                //txtBarcodeValue.setText(intentData);
                                final String comptev = intentData;
                                Users user = SharedPrefManager.getInstance(getContext()).getUser();
                                final String compte = user.getCompte();


                                final double solde = Double.valueOf(montantvalide.getText().toString());

                                presenter.retrait(comptev, compte, solde);

                                //startActivity(new Intent(getContext(), WelCome.class));
                                //editMontant.setText("");
                                barcodes.valueAt(0).displayValue="";
                                txtBarcodeValue.setText("");

                                montantvalide.setText("");
                                cameraSource.stop();
                            }
                            catch (Exception ex){

                            }
                            //cameraSource.stop();
                                       /* }
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

}
