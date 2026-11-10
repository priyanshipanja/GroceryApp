package com.priyanshi.groceryapp;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GroceryAdapter extends BaseAdapter {
    private Context context;
    private List<GroceryItem> groceryList;
    private LayoutInflater inflater;

    public GroceryAdapter(Context context, List<GroceryItem> groceryList) {
        this.context = context;
        this.groceryList = groceryList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return groceryList != null ? groceryList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return groceryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        ImageView imgView;
        TextView tvName;
        TextView tvPrice;
        EditText etQuantity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.page2, parent, false);
            holder = new ViewHolder();
            holder.imgView = convertView.findViewById(R.id.imageView2);
            holder.tvName = convertView.findViewById(R.id.tvVegName);
            holder.tvPrice = convertView.findViewById(R.id.tvPrice);
            holder.etQuantity = convertView.findViewById(R.id.etQuantity);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GroceryItem item = groceryList.get(position);

        holder.tvName.setText(item.getName());
        holder.tvPrice.setText("₹ " + item.getPricePerKg() + " / kg");
        holder.imgView.setImageResource(item.getImageResId());

        if (holder.etQuantity.getTag() instanceof TextWatcher) {
            holder.etQuantity.removeTextChangedListener((TextWatcher) holder.etQuantity.getTag());
        }

        if (item.getQuantity() > 0) {
            if (item.getQuantity() == (long) item.getQuantity()) {
                holder.etQuantity.setText(String.format("%d", (long) item.getQuantity()));
            } else {
                holder.etQuantity.setText(String.valueOf(item.getQuantity()));
            }
        } else {
            holder.etQuantity.setText("");
        }

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String val = s.toString().trim();
                if (!val.isEmpty()) {
                    try {
                        double qty = Double.parseDouble(val);
                        item.setQuantity(qty);
                    } catch (NumberFormatException e) {
                        item.setQuantity(0.0);
                    }
                } else {
                    item.setQuantity(0.0);
                }
            }
        };

        holder.etQuantity.addTextChangedListener(watcher);
        holder.etQuantity.setTag(watcher);

        return convertView;
    }
}
