package ru.mivlgu.bookshop.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.mivlgu.bookshop.R;
import ru.mivlgu.bookshop.models.PublisherShop;

public class PublisherAdapter extends RecyclerView.Adapter<PublisherAdapter.PublisherViewHolder> {
    private List<PublisherShop> publishers = new ArrayList<>();

    @NonNull
    @Override
    public PublisherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_publisher_item, parent, false);
        return new PublisherAdapter.PublisherViewHolder(view);
    }

    @Override
    public int getItemCount() { return publishers.size(); }

    public void setData(List<PublisherShop> newList){
        publishers.clear();
        publishers.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull PublisherAdapter.PublisherViewHolder holder, int position){
        PublisherShop publisher = publishers.get(position);
        holder.publisherNumber.setText(String.format("Номер: %s", publisher.getId()));
        holder.publisherName.setText(String.format("Название: %s", publisher.getName()));
    }

    static class PublisherViewHolder extends RecyclerView.ViewHolder{
        TextView publisherNumber;
        TextView publisherName;
        public PublisherViewHolder(@NonNull View itemView){
            super(itemView);
            publisherNumber = itemView.findViewById(R.id.textView_rv_publisher_number);
            publisherName = itemView.findViewById(R.id.textView_rv_publisher_name);
        }
    }
}
