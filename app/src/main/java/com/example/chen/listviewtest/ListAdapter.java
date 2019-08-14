package com.example.chen.listviewtest;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;

public class ListAdapter extends BaseAdapter  {
    private LinkedList<ListContent> listContents;
    private Context context;
    //用HashMap存储每个item的checkbox选择状态
    //key：每个item的textView的text值， value：item的选择状态。
    private HashMap<String,Boolean> listMap =  new HashMap<>();

    public ListAdapter(LinkedList<ListContent> listContents,Context context) {

        this.listContents = listContents;

        this.context = context;
    }

    @Override
    public int getCount() {
        return listContents.size();
    }

    @Override
    public Object getItem(int item) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int index = position;
        ViewHolder viewHolder;
        if(convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textView =(TextView) convertView.findViewById(R.id.text_View);
            viewHolder.checkStatus =  convertView.findViewById(R.id.check_box);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.textView.setText(listContents.get(index).getName());
        final String str = (String) viewHolder.textView.getText();
        viewHolder.checkStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked) {
                     listMap.put(str,isChecked);
                 }else {
                     listMap.remove(str);
                 }
            }
        });
        viewHolder.checkStatus.setChecked(listMap.get(str) != null);
        return convertView;
    }



    class ViewHolder{
        TextView textView;
        CheckBox checkStatus;
    }
}
