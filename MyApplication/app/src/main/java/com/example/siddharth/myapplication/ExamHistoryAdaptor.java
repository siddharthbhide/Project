package com.example.siddharth.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


public class ExamHistoryAdaptor extends ArrayAdapter<HistoryDetails> {

    ArrayList<HistoryDetails> records = null;
    Context cntx = null;

    public ExamHistoryAdaptor(Context context, ArrayList<HistoryDetails> records)
    {
        super(context, 0, records);
        this.records = records;
        this.cntx = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        HistoryDetails user = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.exam_history_record, parent, false);
        }

        TextView examName = (TextView) convertView.findViewById(R.id.examName);
        TextView examCourse = (TextView) convertView.findViewById(R.id.examCourse);
        TextView examDate = (TextView) convertView.findViewById(R.id.examDate);

        HistoryDetails record = this.records.get(position);
        examName.setText(record.examDetails.getName());
        examCourse.setText(record.examDetails.getCourse()+" : "+record.examDetails.getLevel());
        examDate.setText(record.examDetails.getStartDate());

        ImageButton detailsBtn = (ImageButton)convertView.findViewById(R.id.historyDetails);
        detailsBtn.setTag(""+position);
        detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = Integer.parseInt(view.getTag().toString());
                Intent intent = new Intent(cntx, ReportActivity.class);
                intent.putExtra("historyRecord", records.get(position));
                cntx.startActivity(intent);
            }
        });


        return convertView;
    }

}
