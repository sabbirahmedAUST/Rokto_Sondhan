package com.example.blood_bank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AmbulanceAdapter extends RecyclerView.Adapter<AmbulanceAdapter.ProfileViewHolder> {

    private Context mCtx;
    private List<Ambulance> profileList;

    public AmbulanceAdapter(Context mCtx, List<Ambulance> profileList) {
        this.mCtx = mCtx;
        this.profileList = profileList;
    }




    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProfileViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.activity_ambulance_adapter, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull final AmbulanceAdapter.ProfileViewHolder holder, final int position) {
        Ambulance profile = profileList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Toast.makeText(holder.itemView.getContext(),""+position,Toast.LENGTH_SHORT).show();
            }


        });

        holder.text_name.setText(profile.getName());
        holder.text_phone.setText(profile.getPhone());
        holder.text_district.setText(profile.getDistrict());




    }








    @Override
    public int getItemCount() {
        return profileList.size();
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder {

        TextView text_name, text_phone, text_district;

        public ProfileViewHolder(View itemView) {
            super(itemView);


            text_name = itemView.findViewById(R.id.text_name);
            text_phone = itemView.findViewById(R.id.text_phone);
            text_district = itemView.findViewById(R.id.text_district);



        }
    }

}
