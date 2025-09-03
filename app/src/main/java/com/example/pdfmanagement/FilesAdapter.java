package com.example.pdfmanagement;

import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.VH> {
    private final List<FileItem> data = new ArrayList<>();
    public void submit(List<FileItem> items){ data.clear(); data.addAll(items); notifyDataSetChanged(); }

    @NonNull @Override public VH onCreateViewHolder(@NonNull ViewGroup p, int v) {
        View view = LayoutInflater.from(p.getContext()).inflate(R.layout.item_file, p, false);
        return new VH(view);
    }
    @Override public void onBindViewHolder(@NonNull VH h, int pos) {
        FileItem f = data.get(pos);
        h.title.setText(f.name);
        h.meta.setText(f.meta);
        int icon = R.drawable.baseline_pdf_file_24;
        switch (f.type){
            case "doc": icon = R.drawable.baseline_doc_file_24; break;
            case "xls": icon = R.drawable.baseline_xml_file_24; break;
            case "ppt": icon = R.drawable.baseline_ppt_file_24; break;
        }
        h.icon.setImageResource(icon);
    }
    @Override public int getItemCount(){ return data.size(); }
    static class VH extends RecyclerView.ViewHolder{
        ImageView icon, more; TextView title, meta;
        VH(View v){ super(v);
            icon = v.findViewById(R.id.icon);
            more = v.findViewById(R.id.more);
            title = v.findViewById(R.id.title);
            meta = v.findViewById(R.id.meta);
        }
    }
}
