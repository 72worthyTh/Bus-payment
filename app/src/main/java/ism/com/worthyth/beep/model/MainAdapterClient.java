package ism.com.worthyth.beep.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import ism.com.worthyth.beep.R;

public class MainAdapterClient extends RecyclerView.Adapter<MainAdapterClient.RecyclerViewAdapter> {

    private Context context;
    private List<Users> users;
    private ItemClickListener itemClickListener;

    public MainAdapterClient(Context context, List<Users> users, ItemClickListener itemClickListener) {
        this.context = context;
        this.users  = users;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view= LayoutInflater.from(context).inflate(R.layout.item_client, parent,false);

        return new RecyclerViewAdapter(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        Users user =users.get(position);

        holder.tv_nom.setText(user.getNom());
        holder.tv_prenom.setText(user.getPrenom());
        holder.tv_compte.setText(user.getCompte());
        holder.tv_type.setText(user.getType());
        holder.tv_date.setText(user.getDate());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_compte,tv_type,tv_nom,tv_prenom,tv_date;
        ItemClickListener itemClickListener;

        RecyclerViewAdapter( View itemView,ItemClickListener itemClickListener) {

            super(itemView);
            tv_compte=itemView.findViewById(R.id.idnumerocompte);
            tv_type=itemView.findViewById(R.id.idtypeclient);
            tv_nom=itemView.findViewById(R.id.idnom);
            tv_prenom=itemView.findViewById(R.id.idprenom);
            tv_date=itemView.findViewById(R.id.iddatecreation);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v,getAdapterPosition());
        }
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public MainAdapterClient(Context context, List<Users> users) {
        this.context = context;
        this.users = users;
    }

    public MyViewHolder CreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_client, parent, false);
        return new MyViewHolder(view);
    }


    public void onBindViewHolder(MyViewHolder holder, int position) {
        Users user =users.get(position);
        holder.tv_nom.setText(user.getNom());
        holder.tv_prenom.setText(user.getPrenom());
        holder.tv_compte.setText(user.getCompte());
        holder.tv_type.setText(user.getType());
        holder.tv_date.setText(user.getDate());

    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_compte,tv_type,tv_nom,tv_prenom,tv_date;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_compte=itemView.findViewById(R.id.idnumerocompte);
            tv_type=itemView.findViewById(R.id.idtypeclient);
            tv_nom=itemView.findViewById(R.id.idnom);
            tv_prenom=itemView.findViewById(R.id.idprenom);
            tv_date=itemView.findViewById(R.id.iddatecreation);
        }
    }

}





