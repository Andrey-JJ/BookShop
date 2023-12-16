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
import ru.mivlgu.bookshop.models.AuthorShop;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder> {
    private List<AuthorShop> authors = new ArrayList<>();

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_author_item, parent, false);
        return new AuthorAdapter.AuthorViewHolder(view);
    }

    @Override
    public int getItemCount() { return authors.size(); }

    public void setData(List<AuthorShop> newList) {
        authors.clear();
        authors.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorAdapter.AuthorViewHolder holder, int position){
        AuthorShop author = authors.get(position);
        holder.authorLastname.setText(String.format("Фамилия: %s", author.getLastname()));
        holder.authorFirstname.setText(String.format("Имя: %s", author.getFirstname()));
        holder.authorMidname.setText(String.format("Отчество: %s", author.getMidname()));
    }

    static class AuthorViewHolder extends RecyclerView.ViewHolder{
        TextView authorLastname;
        TextView authorFirstname;
        TextView authorMidname;
        public AuthorViewHolder(@NonNull View itemView){
            super(itemView);
            authorLastname = itemView.findViewById(R.id.textView_rv_author_lastname);
            authorFirstname = itemView.findViewById(R.id.textView_rv_author_firstname);
            authorMidname = itemView.findViewById(R.id.textView_rv_author_midname);
        }
    }
}
