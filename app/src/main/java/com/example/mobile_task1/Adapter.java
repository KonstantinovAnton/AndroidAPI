package com.example.mobile_task1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {


    private Context mContext;
    List<Person> personListList;
    private int i;
    private View view;
    private ViewGroup viewGroup;

    public Adapter(Context mContext, List<Person> listProduct) {
        this.mContext = mContext;
        this.personListList = listProduct;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }


    private Bitmap getUserImage(String encodedImg)
    {

        if(encodedImg!=null&& !encodedImg.equals("null")) {
            byte[] bytes = Base64.decode(encodedImg, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        else
        {
            return null;

        }
    }


    public View getView(int i, View view)
    {
        View v = View.inflate(mContext,R.layout.listlayouttemplate,null);

        TextView fname = v.findViewById(R.id.tvFname);
        TextView lname = v.findViewById(R.id.tvLname);

        Person p = personListList.get(i);
        fname.setText(p.getFname());
        lname.setText(p.getLname());

        //  imageView.setImageBitmap(getUserImage(mask.get()));
        return v;
    }
}
