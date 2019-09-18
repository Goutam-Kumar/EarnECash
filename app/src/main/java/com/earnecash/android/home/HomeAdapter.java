package com.earnecash.android.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.earnecash.android.R;
import com.earnecash.android.apphelper.AppHelper;
import com.earnecash.android.login.model.UserData;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.PartnerHolder> {

    private Context context;
    private String[] imgArray;
    private String shareURL = "Check the best offer on TV, Internet, and Phone.  Save with an unbeatable offer.Watch live TV and On Demand on any screen, from smart TVs to smartphones. Stream, surf and game faster than ever with game-changing Internet starting speeds. And talk all you want to family and friends in the U.S., Mexico, Canada and more countries. Plus, get it all with NO contracts and NO early termination fees!    Follow the link  to avail the offer! \n http://www.earnecash.com/refer?code=";
    private String referCode = "";

    public HomeAdapter(Context context) {
        this.context = context;
        imgArray = context.getResources().getStringArray(R.array.imgs);
    }

    @NonNull
    @Override
    public PartnerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PartnerHolder(LayoutInflater.from(context).inflate(R.layout.item_image, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PartnerHolder partnerHolder, int i) {
        Picasso.get().load(imgArray[i]).resize(200,200).centerCrop().into(partnerHolder.imgPartner);
        partnerHolder.imgPartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = AppHelper.getString(AppHelper.USER_PREF,AppHelper.USER_DATA);
                UserData userData = new Gson().fromJson(user,UserData.class);
                if (user != null)
                     referCode = userData.getReferCode();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, shareURL+referCode);
                sendIntent.setType("text/plain");
                context.startActivity(sendIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imgArray.length;
    }

    public class PartnerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgPartner)
        ImageView imgPartner;

        public PartnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
