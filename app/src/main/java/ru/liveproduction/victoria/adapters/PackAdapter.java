package ru.liveproduction.victoria.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

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
        return VictoriaApplication.packList.size();
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
            view = inflater.inflate(R.layout.chosepack_adapter, viewGroup, false);
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
