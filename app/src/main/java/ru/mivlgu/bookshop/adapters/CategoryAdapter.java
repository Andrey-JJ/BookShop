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
import ru.mivlgu.bookshop.models.CategoryShop;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<CategoryShop> categories = new ArrayList<>();

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_category_item, parent, false);
        return new CategoryAdapter.CategoryViewHolder(view);
    }

    @Override
    public int getItemCount() { return categories.size(); }

    public void setData(List<CategoryShop> newList){
        categories.clear();
        categories.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position){
        CategoryShop category = categories.get(position);
        holder.categoryNumber.setText(String.format("Номер: %d", category.getId()));
        holder.categoryName.setText(String.format("Название: %s", category.getName()));
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView categoryNumber;
        TextView categoryName;
        public CategoryViewHolder(@NonNull View itemView){
            super(itemView);
            categoryNumber = itemView.findViewById(R.id.textView_rv_category_number);
            categoryName = itemView.findViewById(R.id.textView_rv_category_name);
        }
    }
}
