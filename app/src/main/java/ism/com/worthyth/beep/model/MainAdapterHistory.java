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

public class MainAdapterHistory extends RecyclerView.Adapter<MainAdapterHistory.RecyclerViewAdapter> {

    private Context context;
    private List<HistoryDivers> comptes;
    private ItemClickListener itemClickListener;

    public MainAdapterHistory(Context context, List<HistoryDivers> comptes, ItemClickListener itemClickListener) {
        this.context = context;
        this.comptes  = comptes;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view= LayoutInflater.from(context).inflate(R.layout.item_allhistory, parent,false);
        return new RecyclerViewAdapter(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        HistoryDivers compte =comptes.get(position);
        holder.tv_numero.setText(compte.getNumeroD());
        holder.tv_type.setText(compte.getNumeroC());
        holder.tv_solde.setText(String.valueOf(compte.getMontant()));
        holder.tv_taux.setText(compte.getDateop());
        holder.tv_decouvert.setText(compte.getTupeop());
      //  holder.card_item.setCardBackgroundColor(compte.getColor());

    }

    @Override
    public int getItemCount() {
        return comptes.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_numero,tv_type,tv_solde,tv_taux,tv_decouvert;
        CardView card_item;
        ItemClickListener itemClickListener;

        RecyclerViewAdapter( View itemView,ItemClickListener itemClickListener) {

            super(itemView);
            tv_numero=itemView.findViewById(R.id.idnumero1);
            tv_type=itemView.findViewById(R.id.type);
            tv_solde=itemView.findViewById(R.id.solde);
            tv_decouvert=itemView.findViewById(R.id.decouvert);
            tv_taux=itemView.findViewById(R.id.taux);
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
