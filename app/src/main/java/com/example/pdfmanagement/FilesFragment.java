package com.example.pdfmanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilesFragment extends Fragment {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private FilesAdapter adapter;

    // sample data
    private final List<FileItem> allFiles = Arrays.asList(
            new FileItem("Demo PDF.pdf", "08/29/2025 • 418 KB", "pdf"),
            new FileItem("Demo DOCX.docx", "08/29/2025 • 19 KB", "doc"),
            new FileItem("Demo XLSX.xlsx", "08/29/2025 • 6 KB", "xls"),
            new FileItem("Demo PPT.pptx", "08/29/2025 • 257 KB", "ppt")
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_files, container, false);

        // Initialize views
        toolbar = view.findViewById(R.id.toolbar);
        tabLayout = view.findViewById(R.id.tabLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        fab = view.findViewById(R.id.fab);

        // Toolbar setup
        toolbar.setTitle("All PDF Reader");
        toolbar.inflateMenu(R.menu.menu_files_appbar);
        toolbar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();

            if (id == R.id.action_search) {
                Toast.makeText(getContext(), "Search clicked", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.action_sort) {
                Toast.makeText(getContext(), "Sort clicked", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.action_settings) {
                Toast.makeText(getContext(), "Settings clicked", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.action_select) {
                Toast.makeText(getContext(), "Select clicked", Toast.LENGTH_SHORT).show();
                return true;
            }

            return false;
        });

        // Tabs
        String[] tabs = {"All", "PDF", "Word", "Excel", "PPT"};
        for (String t : tabs) {
            tabLayout.addTab(tabLayout.newTab().setText(t));
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                applyFilter(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                applyFilter(tab.getText().toString());
            }
        });

        // RecyclerView
        adapter = new FilesAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        applyFilter("All");

        // FAB click
        fab.setOnClickListener(v -> Toast.makeText(getContext(), "Add/Import clicked", Toast.LENGTH_SHORT).show());

        return view;
    }

    private void applyFilter(String tab) {
        List<FileItem> filtered = new ArrayList<>();
        for (FileItem f : allFiles) {
            boolean ok = tab.equals("All") ||
                    (tab.equals("PDF") && f.type.equals("pdf")) ||
                    (tab.equals("Word") && f.type.equals("doc")) ||
                    (tab.equals("Excel") && f.type.equals("xls")) ||
                    (tab.equals("PPT") && f.type.equals("ppt"));
            if (ok) filtered.add(f);
        }
        adapter.submit(filtered);
    }
}
