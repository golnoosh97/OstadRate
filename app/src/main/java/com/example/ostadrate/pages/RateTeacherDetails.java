
package com.example.ostadrate.pages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ostadrate.Comment;
import com.example.ostadrate.R;
import com.example.ostadrate.Rate;
import com.example.ostadrate.logger.Pulp;
import com.shawnlin.numberpicker.NumberPicker;
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

        final int id = getIntent().getIntExtra("personalCode", -1);
        // Fetch the personal code to get the full data

        generateFakeInfo(id);

        list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(generateFakeComment());
        list.setAdapter(adapter);

        setChartValues(chart1,
                generateFakeRate().getTeachingQuality(),
                generateFakeRate().getBehaviour(),
                generateFakeRate().getGrading()
        );

        // When add feedback was clicked
        addFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RateSelectorDialogView selector = new RateSelectorDialogView(RateTeacherDetails.this);
                new AlertDialog.Builder(RateTeacherDetails.this)
                        .setTitle("Enter feedback")
                        .setMessage("Complete the things")
                        .setView(selector.container)
                        .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                int grading = selector.grading.getValue();
                                int teaching = selector.teaching.getValue();
                                int behaviour = selector.behaviour.getValue();
                                int teacherId = id;

                                Pulp.debug("TeacherRate", "Teacher rate submitted")
                                        .addMessage("TeacherId", String.valueOf(teacherId))
                                        .addMessage("Teaching", String.valueOf(teaching))
                                        .addMessage("Grading", String.valueOf(grading))
                                        .addMessage("Behaviour", String.valueOf(behaviour))
                                        .log();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create()
                        .show();
            }
        });

        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RateCommentDialogView commentView = new RateCommentDialogView(RateTeacherDetails.this);
                new AlertDialog.Builder(RateTeacherDetails.this)
                        .setTitle("Enter comment")
                        .setView(commentView.container)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Pulp.debug("Add Comment", "Comment generated")
                                        .addMessage("TeacherId", String.valueOf(id))
                                        .addMessage("Name", commentView.getNameValue())
                                        .addMessage("Comment", commentView.getCommentValue())
                                        .log();
                                List<Comment> comments = generateFakeComment();
                                comments.add(new Comment(id, commentView.getNameValue(), commentView.getCommentValue()));
                                adapter.updateComments(comments);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create()
                        .show();

            }
        });
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

    void setChartValues(StackedBarChart chart, double teachingQuality, double behaviour, double grading) {
        chart.addBar(new StackedBarModel("Teaching", Arrays.asList(
                new BarModel((float) teachingQuality, teachingQuality < 5f ? Color.RED : Color.GREEN),
                new BarModel(10f - (float) teachingQuality, Color.WHITE)
        )));

        chart.addBar(new StackedBarModel("Behaviour", Arrays.asList(
                new BarModel((float) behaviour, behaviour < 5f ? Color.RED : Color.GREEN),
                new BarModel(10f - (float) behaviour, Color.WHITE)
        )));

        chart.addBar(new StackedBarModel("Grading", Arrays.asList(
                new BarModel((float) grading, grading < 5f ? Color.RED : Color.GREEN),
                new BarModel(10f - (float) grading, Color.WHITE)
        )));
    }

    // ----- Fake info

    void generateFakeInfo(int id) {
        if (id == 11) {
            name.setText("Mohammad Meshkani");
            Picasso.get().load("https://avatars2.githubusercontent.com/u/20459019?s=460&v=4").into(image);
            university.setText("Azad sabzevar");
            status.setText("Votes: 3");
        } else if (id == 12) {
            name.setText("Mahdi Malvandi");
            Picasso.get().load("https://avatars2.githubusercontent.com/u/21319971?s=460&v=4").into(image);
            university.setText("University of semnan");
            status.setText("Votes: 11");
        }
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

    // ----- List classes

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


    @SuppressLint("InflateParams")
    class RateSelectorDialogView {

        private NumberPicker teaching, behaviour, grading;
        private View container;

        RateSelectorDialogView(Context context) {
            container = LayoutInflater.from(context).inflate(R.layout.rate_selector_layout, null);
            teaching = container.findViewById(R.id.teaching);
            teaching.setDividerColor(Color.WHITE);
            teaching.setMaxValue(5);
            teaching.setDisplayedValues(new String[] {"1", "2", "3", "4", "5"});
            teaching.setOrientation(NumberPicker.ASCENDING);
            behaviour = container.findViewById(R.id.behaviour);
            behaviour.setDividerColor(Color.WHITE);
            behaviour.setMaxValue(5);
            behaviour.setDisplayedValues(new String[] {"1", "2", "3", "4", "5"});
            behaviour.setOrientation(NumberPicker.ASCENDING);
            grading = container.findViewById(R.id.grading);
            grading.setDividerColor(Color.WHITE);
            grading.setMaxValue(5);
            grading.setDisplayedValues(new String[] {"1", "2", "3", "4", "5"});
            grading.setOrientation(NumberPicker.ASCENDING);
        }
    }

    @SuppressLint("InflateParams")
    class RateCommentDialogView {

        private EditText name, comment;
        private View container;

        RateCommentDialogView(Context context) {
            container = LayoutInflater.from(context).inflate(R.layout.rate_comment_layout, null);
            name = container.findViewById(R.id.nameEdit);
            comment = container.findViewById(R.id.commentEdit);
        }

        public String getNameValue() {
            return name.getText().toString();
        }

        public String getCommentValue() {
            return comment.getText().toString();
        }
    }
}
