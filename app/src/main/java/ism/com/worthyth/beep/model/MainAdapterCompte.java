package ism.com.worthyth.beep.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import ism.com.worthyth.beep.R;


public class MainAdapterCompte extends RecyclerView.Adapter<MainAdapterCompte.RecyclerViewAdapter> {
    private Context context;
    private List<Users> comptes;
    private ItemClickListener itemClickListener;

    public MainAdapterCompte(Context context, List<Users> comptes, ItemClickListener itemClickListener) {
        this.context = context;
        this.comptes  = comptes;
        this.itemClickListener = itemClickListener;
    }
    @Override
    public int getItemCount() {
try{

        return comptes.size();
}
catch (Exception ex){
    return 0;
}
    }
    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view= LayoutInflater.from(context).inflate(R.layout.item_comp, parent,false);
        return new RecyclerViewAdapter(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        Users compte =comptes.get(position);
        if(compte.getEtat()==1){
            holder.textEtatuser.setText("ACTIF");
        }
        if(compte.getEtat()==0){
            holder.textEtatuser.setText("DESACTIVE");
        }
        if(compte.getEtat()==2){
            holder.textEtatuser.setText("Bloqué");
        }
        holder.textnomUser.setText(compte.getNom());
        holder.testPrenomuser.setText(compte.getPrenom());
        holder.textTelephoneUser.setText(compte.getTelephone());
        holder.textTypeUser.setText(compte.getType());
        holder.textCompterUser.setText(compte.getCompte());
        holder.textSolderUser.setText(compte.getSolde());

        //  holder.card_item.setCardBackgroundColor(compte.getColor());

    }



    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textnomUser,testPrenomuser,textTelephoneUser,textTypeUser,textCompterUser,textEtatuser,textSolderUser;

        // CardView card_item;
        ItemClickListener itemClickListener;

        RecyclerViewAdapter( View itemView,ItemClickListener itemClickListener) {

            super(itemView);
            textnomUser = itemView.findViewById(R.id.idnomuser);
            testPrenomuser =itemView. findViewById(R.id.idprenomuser);
            textTelephoneUser =itemView.findViewById(R.id.idteluser);
            textTypeUser = itemView.findViewById(R.id.idtypeuser);
            textCompterUser = itemView.findViewById(R.id.idcompteuser);
            textEtatuser = itemView.findViewById(R.id.idetatuser);
            textSolderUser =itemView.findViewById(R.id.idsoldeuser);

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v,getAdapterPosition());
        }
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
