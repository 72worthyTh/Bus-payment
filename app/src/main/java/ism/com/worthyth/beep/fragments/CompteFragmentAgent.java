package ism.com.worthyth.beep.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;




import java.util.List;

import ism.com.worthyth.beep.R;
import ism.com.worthyth.beep.api.ApiClient;
import ism.com.worthyth.beep.api.ApiInterface;
import ism.com.worthyth.beep.chare.SharedPrefManager;
import ism.com.worthyth.beep.interfaces.MainViewUsers;
import ism.com.worthyth.beep.model.ImageDownloadTask;
import ism.com.worthyth.beep.model.MainAdapterCompte;
import ism.com.worthyth.beep.model.MainPresenter;
import ism.com.worthyth.beep.model.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link CompteFragmentAgent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompteFragmentAgent extends Fragment implements MainViewUsers {
    Button button;
    ImageView imageView;
    LinearLayout lay;


    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    MainPresenter presenter;
    MainAdapterCompte adapter;
    MainAdapterCompte.ItemClickListener itemClickListener;

    private RecyclerView.LayoutManager layoutManager;
    private ApiInterface apiInterface;

    List<Users> comptes;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CompteFragmentAgent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompteFragmentAgent.
     */
    // TODO: Rename and change types and number of parameters
    public static CompteFragmentAgent newInstance(String param1, String param2) {
        CompteFragmentAgent fragment = new CompteFragmentAgent();
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
        View v=inflater.inflate(R.layout.fragment_compte_fragment_agent, container, false);

        swipeRefresh = v.findViewById(R.id.swipe_refreshcompte);
        recyclerView = v.findViewById(R.id.recycler_viewcompte);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        fetchContact("");
        presenter = new MainPresenter(this);
        Users user = SharedPrefManager.getInstance(getContext()).getUser();
        final String ncompte=user.getCompte();
        presenter.getDataCompte();
        swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getDataCompte();
            }
        });
        /*editText= v.findViewById(R.id.editext);*/
        lay=v.findViewById(R.id.lay);
        button = v.findViewById(R.id.button1);
        imageView = v.findViewById(R.id.imageView);

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
    public void fetchContact(String key){
        Users user = SharedPrefManager.getInstance(getContext()).getUser();
        final String ncompte=user.getCompte();
        key=ncompte;
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Users>> call = apiInterface.getUserConBykey(key);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                //progressBar.setVisibility(View.GONE);
                comptes = response.body();
                adapter = new MainAdapterCompte(getContext(),comptes,itemClickListener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                // progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /*   private void getJSON(final String urlWebService) {

           class GetJSON extends AsyncTask<Void, Void, String> {

               @Override
               protected void onPreExecute() {
                   super.onPreExecute();
               }


               @Override
               protected void onPostExecute(String s) {
                   super.onPostExecute(s);
                  // Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                   try {
                       loadIntoListView(s);
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }

               @Override
               protected String doInBackground(Void... voids) {
                   try {
                       URL url = new URL(urlWebService);
                       HttpURLConnection con = (HttpURLConnection) url.openConnection();
                       StringBuilder sb = new StringBuilder();
                       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                       String json;
                       while ((json = bufferedReader.readLine()) != null) {
                           sb.append(json + "\n");
                       }
                       return sb.toString().trim();
                   } catch (Exception e) {
                       return null;
                   }
               }
           }
           GetJSON getJSON = new GetJSON();
           getJSON.execute();
       }
       private void loadIntoListView(String json) throws JSONException {
           String solde="solde";
           JSONArray jsonArray = new JSONArray(json);
           String[] heroes = new String[jsonArray.length()];
           for (int i = 0; i < jsonArray.length(); i++) {
               JSONObject obj = jsonArray.getJSONObject(i);
               heroes[i] = obj.getString(solde);
           }
          ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, heroes);
           listView.setAdapter(arrayAdapter);
       }
   */
    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);

    }

    @Override
    public void onGetResultUsers(List<Users> compte) {
        adapter = new MainAdapterCompte(getContext(), compte, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        comptes = compte;
    }





    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }


}
