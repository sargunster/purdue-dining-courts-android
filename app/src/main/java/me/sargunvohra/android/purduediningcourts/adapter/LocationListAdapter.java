package me.sargunvohra.android.purduediningcourts.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.InjectView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.model.Location;
import me.sargunvohra.android.purduediningcourts.service.DiningServiceHelper;

@AllArgsConstructor(suppressConstructorProperties = true)
public class LocationListAdapter<T extends Location> extends RecyclerView.Adapter<LocationListAdapter.ViewHolder> {

    public static class ViewHolder extends BaseViewHolder {
        @InjectView(R.id.title)
        public TextView title;

        @InjectView(R.id.image)
        public ImageView image;

        public ViewHolder(View parentView) {
            super(parentView);
        }
    }

    public interface OnClickListener<T extends Location> {
        void onClick(T location);
    }

    @Getter
    @Setter
    private List<T> dataSet;

    @Getter
    @Setter
    private OnClickListener<T> onClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_location, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Location loc = dataSet.get(position);

        holder.parentView.setId(loc.hashCode());

        // set card title
        holder.title.setText(loc.getFullName());

        // set card image
        Picasso.with(holder.image.getContext())
                .load(DiningServiceHelper.getFileUrl(loc.getImageId()))
                .placeholder(R.drawable.placeholder)
                .resize(500, 400)
                .centerCrop()
                .into(holder.image);

        // activate click
        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(dataSet.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}