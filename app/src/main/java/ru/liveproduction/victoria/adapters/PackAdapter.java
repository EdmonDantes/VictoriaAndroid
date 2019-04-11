package ru.liveproduction.victoria.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import ru.liveproduction.victoria.R;
import ru.liveproduction.victoria.VictoriaApplication;
import ru.liveproduction.victoria.activities.InfoPackActivity;

public class PackAdapter extends BaseAdapter {

    Activity activity;
    LayoutInflater inflater;


    public PackAdapter(Activity activity){
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        if (VictoriaApplication.packList != null)
            return VictoriaApplication.packList.size();
        else return 0;
    }

    @Override
    public Object getItem(int i) {
         return VictoriaApplication.packList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.chose_pack_adapter_layout, viewGroup, false);
        }

        Button button = ((Button) view.findViewById(R.id.packName));
        button.setText("Пак №" + VictoriaApplication.packList.get(i).getId());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, InfoPackActivity.class);
                intent.putExtra("pack", VictoriaApplication.packList.get(i));
                activity.startActivityForResult(intent, 43);
            }
        });
        return view;
    }
}
