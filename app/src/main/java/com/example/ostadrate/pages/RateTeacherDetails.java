
package com.example.ostadrate.pages;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ostadrate.Comment;
import com.example.ostadrate.R;
import com.example.ostadrate.Rate;
import com.squareup.picasso.Picasso;

import org.eazegraph.lib.charts.StackedBarChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.StackedBarModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RateTeacherDetails extends AppCompatActivity {

    CircleImageView image;
    TextView name, university, status;
    StackedBarChart chart1;
    AppCompatButton addComment, addFeedback;

    RecyclerView list;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_teacher_details);
        setTitle("Rate this Ostad");
        attachViews();

        int id = getIntent().getIntExtra("personalCode", -1);
        // Fetch the personal code to get the full data

        generateFakeInfo();

        list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(generateFakeComment());
        list.setAdapter(adapter);

        setChartValues(chart1,
                generateFakeRate().getTeachingQuality(),
                generateFakeRate().getBehaviour(),
                generateFakeRate().getMark()
        );
    }

    private void attachViews() {
        image = findViewById(R.id.teacherImage);
        name = findViewById(R.id.name);
        status = findViewById(R.id.status);
        chart1 = findViewById(R.id.lineChart1);
        university = findViewById(R.id.university);
        addComment = findViewById(R.id.addComment);
        addFeedback = findViewById(R.id.addFeedback);
        list = findViewById(R.id.comments);
    }

    void setChartValues(StackedBarChart chart, double teachingQuality, double behaviour, double point) {
        chart.addBar(new StackedBarModel("Teaching", Arrays.asList(
                new BarModel((float) teachingQuality, teachingQuality < 5f ? Color.RED : Color.GREEN),
                new BarModel(10f - (float) teachingQuality, Color.WHITE)
        )));

        chart.addBar(new StackedBarModel("Behaviour", Arrays.asList(
                new BarModel((float) behaviour, behaviour < 5f ? Color.RED : Color.GREEN),
                new BarModel(10f - (float) behaviour, Color.WHITE)
        )));

        chart.addBar(new StackedBarModel("Point", Arrays.asList(
                new BarModel((float) point, point < 5f ? Color.RED : Color.GREEN),
                new BarModel(10f - (float) point, Color.WHITE)
        )));
    }

    void generateFakeInfo() {
        name.setText("Mohammad Meshkani");
        Picasso.get().load("https://avatars2.githubusercontent.com/u/20459019?s=460&v=4").into(image);
        university.setText("Azad sabzevar");
        status.setText("Status: Playing with Flutter");
    }

    List<Comment> generateFakeComment() {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment(11, "Mahdi", "I hate teacher he walks around and talks like a fool."));
        comments.add(new Comment(11, "Mahdi", "This man is absolutely idiot."));
        comments.add(new Comment(11, "Mahdi", "He almost dropped me. I got 9.4 and then 10 :(."));
        comments.add(new Comment(11, "Mohammad Meshkani", "I love him. He's perfect"));
        return comments;
    }

    Rate generateFakeRate() {
        return new Rate(11, 4.1, 1.3, 6.2);
    }


    /**
     * List adapter for comments
     */
    class Adapter extends RecyclerView.Adapter<ViewModel> {

        private List<Comment> dataSet;

        Adapter(List<Comment> dataSet) {
            this.dataSet = dataSet;
        }

        public void updateComments(List<Comment> list) {
            dataSet = list;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewModel(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.rate_teacher_comment_list_item, parent, false)
            );
        }

        @Override
        public void onBindViewHolder(@NonNull ViewModel holder, int position) {
            holder.name.setText(dataSet.get(position).getName());
            holder.comment.setText(dataSet.get(position).getComment());
        }

        @Override
        public int getItemCount() {
            return dataSet.size();
        }
    }

    class ViewModel extends RecyclerView.ViewHolder {

        TextView name, comment;

        ViewModel(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            comment = itemView.findViewById(R.id.comment);
        }
    }
}
