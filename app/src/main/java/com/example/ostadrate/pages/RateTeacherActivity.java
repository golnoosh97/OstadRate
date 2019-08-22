package com.example.ostadrate.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ostadrate.R;
import com.example.ostadrate.Teacher;
import com.example.ostadrate.logger.Pulp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class RateTeacherActivity extends AppCompatActivity {

    private List<Teacher> teachers = new ArrayList<>();
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_teacher);
        setTitle("Rate teacher");

        testList();

        RecyclerView list = findViewById(R.id.list);
        list.setHasFixedSize(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(teachers, new ListClick() {
            @Override
            public void onClick(int position, Teacher teacher) {
                startActivity(
                        new Intent(RateTeacherActivity.this, RateTeacherDetails.class)
                        .putExtra("personalCode", teacher.getPersonalCode())
                );
            }
        });
        list.setAdapter(adapter);
    }

    public void testList() {
        Teacher mmd = new Teacher(11, "Mohammad meshkani",
                "https://avatars3.githubusercontent.com/u/20459019?s=400&v=4",
                "Azad sabzevar");
        teachers.add(mmd);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Filter")
                .setIcon(android.R.drawable.ic_menu_sort_by_size)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                // Filter list
                Toast.makeText(RateTeacherActivity.this, "Filtering not ready", Toast.LENGTH_SHORT).show();
                return true;
            }
        }).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    class Adapter extends RecyclerView.Adapter<ViewModel> {

        private List<Teacher> teachers;
        private ListClick listener;

        Adapter(List<Teacher> teachers, ListClick listener) {
            this.teachers = teachers;
            this.listener = listener;
        }

        void updateSet(List<Teacher> teachers) {
            this.teachers.clear();
            this.teachers.addAll(teachers);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewModel(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.rate_teacher_list_item, parent, false)
            );
        }

        @Override
        public void onBindViewHolder(@NonNull ViewModel holder, int position) {
            Picasso.get().load(teachers.get(position).getImageUrl()).into(holder.image);
            holder.name.setText(teachers.get(position).getName());
            holder.university.setText(teachers.get(position).getUniversity());
            final int p = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(p, teachers.get(p));
                }
            });
        }

        @Override
        public int getItemCount() {
            return teachers.size();
        }
    }

    class ViewModel extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView name, university;
        ViewModel(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            university = itemView.findViewById(R.id.university);
        }
    }

    interface ListClick {
        void onClick(int position, Teacher teacher);
    }
}
