package com.example.leebeomwoo.viewbody_final.ItemGroup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leebeomwoo.viewbody_final.R;

import java.util.List;

/**
 * Created by LeeBW on 2016-07-14.
 */
public class Trainer_license_Adapter extends RecyclerView.Adapter<Trainer_license_Adapter.ViewHolder>{
    private List<License> licenses;
    private Context mContext;

    public Trainer_license_Adapter(Context context, List<License> licenseList){
        this.licenses = licenseList;
        this.mContext = context;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtViewAssociation;
        public final TextView txtViewName;
        public final TextView txtViewCheck;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewAssociation = (TextView) itemLayoutView.findViewById(R.id.association_txtB);
            txtViewName = (TextView) itemLayoutView.findViewById(R.id.name_txtB);
            txtViewCheck = (TextView) itemLayoutView.findViewById(R.id.check_B);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trainer_source, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final License license = licenses.get(position);
        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData
        viewHolder.txtViewAssociation.setText(license.getLAssociation());
        viewHolder.txtViewName.setText(license.getLName());
        viewHolder.txtViewCheck.setText(license.getLCheck());
    }
    @Override
    public int getItemCount() {

        return (null != licenses ? licenses.size() : 0);
    }
}
