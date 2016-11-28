package examples.edwin.android.mac.com.org.simpledagger2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import examples.edwin.android.mac.com.org.simpledagger2.R;
import examples.edwin.android.mac.com.org.simpledagger2.model.Item;
import examples.edwin.android.mac.com.org.simpledagger2.model.SpotifyResult;
import rx.Subscriber;

/**
 * Created by User on 11/26/2016.
 */

public class RVArtistAdapter extends RecyclerView.Adapter<RVArtistAdapter.ViewHolder>{

    private List<Item> lstRes = new ArrayList<>();
    private Context context;

    public RVArtistAdapter(List<Item> lstRes, Context context) {
        this.lstRes = lstRes;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(lstRes.get(position).getName());
        Glide.with(context)
                .load("http://www.freemagebank.com/wp-content/uploads/edd/2015/04/GL0000228-1560x1997.jpg")
                .centerCrop()
                .fitCenter()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return lstRes.size() > 0 ? lstRes.size() : 0 ;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.mImageView)
        ImageView mImageView;
        @BindView(R.id.mTextView)
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
