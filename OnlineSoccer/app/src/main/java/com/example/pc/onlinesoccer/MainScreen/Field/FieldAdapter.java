package com.example.pc.onlinesoccer.MainScreen.Field;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.onlinesoccer.R;

import java.util.ArrayList;

/**
 * Created by CO on 4/28/2016.
 */
public class FieldAdapter extends ArrayAdapter{
    private int resource;
    private LayoutInflater inflater;
    private ArrayList<Fields> arrFields;
    public FieldAdapter(Context context, int resource, ArrayList<Fields> arrFields) {
        super(context, resource,arrFields);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrFields = arrFields;
        this.resource = resource;
    }

    @Override
    public void remove(Object object) {
        super.remove(object);
    }

    @Override
    public void insert(Object object, int index) {
        super.insert(object, index);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = inflater.inflate(R.layout.field_item,parent,false);
        }

        TextView tvField_name = (TextView) convertView.findViewById(R.id.tvField_name);
        TextView tvField_address = (TextView) convertView.findViewById(R.id.tvField_address);
        TextView tvField_phone = (TextView) convertView.findViewById(R.id.tvField_phone);
        TextView tvField_price = (TextView) convertView.findViewById(R.id.tvField_price);
        ImageView flagView = (ImageView) convertView.findViewById(R.id.imgField);

        Fields field = this.arrFields.get(position);

        int imageId = this.getMipmapResIdByName(field.getFlagName());
        flagView.setImageResource(imageId);

        tvField_name.setText(field.getName());
        tvField_address.setText(field.getAddress());
        tvField_phone.setText(field.getPhone());
        tvField_price.setText(""+field.getPriceNormal());

        return convertView;
    }

    // Tìm ID của Image ứng với tên của ảnh (Trong thư mục mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = getContext().getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = getContext().getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
}
