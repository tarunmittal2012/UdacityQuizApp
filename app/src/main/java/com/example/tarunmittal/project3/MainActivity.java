package com.example.tarunmittal.project3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    CountDownTimer mCountDownTimer;
    String remarks;
    @BindView(R.id.relative1)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.linearLayout1)
    LinearLayout mLinearLayout;
    @BindView(R.id.linearLayout2)
    LinearLayout mLinearLayout2;
    @BindView(R.id.text1)
    TextView heading;
    @BindView(R.id.text2)
    TextView startTime;
    @BindView(R.id.start)
    Button startQuiz;
    @BindView(R.id.name)
    EditText nameOfUser;
    @BindView(R.id.submit)
    Button submitQuiz;
    @BindView(R.id.reset)
    Button resetQuiz;
    @BindView(R.id.rg1)
    RadioGroup firstRadioGroup;
    @BindView(R.id.rg2)
    RadioGroup secondRadioGroup;
    @BindView(R.id.rg3)
    RadioGroup thirdRadioGroup;
    @BindView(R.id.rg4)
    RadioGroup fourthRadioGroup;
    @BindView(R.id.ans1)
    RadioButton firstAnswer;
    @BindView(R.id.ans2)
    RadioButton secondAnswer;
    @BindView(R.id.ans3a)
    CheckBox thirdAnswerA;
    @BindView(R.id.ans3b)
    CheckBox thirdAnswerB;
    @BindView(R.id.ans3c)
    CheckBox thirdAnswerC;
    @BindView(R.id.ans3d)
    CheckBox thirdAnswerD;
    @BindView(R.id.Ans4)
    EditText fourthAnswer;
    @BindView(R.id.ans5)
    RadioButton fifthAnswer;
    int marks1;
    int marks2;
    int marks3;
    int marks4;
    int marks5;
    int marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        remarks = this.getString(R.string.directSubmit);
    }

    // Function that Evaluate User Answer and calculate their marks and remark based on Performance
    public void setAnswer(View view) {
        String forthAnswer = fourthAnswer.getText().toString().trim();
        if (firstAnswer.isChecked())
            marks1 = 20;
        else marks1 = 0;
        if (secondAnswer.isChecked())
            marks2 = 20;
        else marks2 = 0;
        if (thirdAnswerA.isChecked() && thirdAnswerB.isChecked() && !thirdAnswerC.isChecked() && !thirdAnswerD.isChecked()) {
            marks3 = 20;
        } else marks3 = 0;
        if (forthAnswer.equalsIgnoreCase("oreo"))
            marks4 = 20;
        else marks4 = 0;
        if (fifthAnswer.isChecked())
            marks5 = 20;
        else marks5 = 0;

        marks = marks1 + marks2 + marks3 + marks4 + marks5;

        if (marks == 100) {
            remarks = this.getString(R.string.remark1);

        } else if (marks >= 60 && marks < 100) {
            remarks = this.getString(R.string.remark2);


        } else if (marks >= 40 && marks < 60) {
            remarks = this.getString(R.string.remark3);
        } else if (marks < 40) {
            remarks = this.getString(R.string.remark4);
        }
    }

    // Function for Start the Quiz by clicking START QUIZ
    @OnClick(R.id.start)
    public void setStartQuiz(final View view) {
        String name = nameOfUser.getText().toString();

        name = name.replace(" ", "");
        if (name.length() == 0) {
            nameOfUser.setError("Enter the name");
            nameOfUser.requestFocus();
        } else {
            startTime.setVisibility(View.VISIBLE);
            heading.setVisibility(View.VISIBLE);
            mLinearLayout.setVisibility(View.GONE);
            mLinearLayout2.setVisibility(View.VISIBLE);
            mCountDownTimer = new CountDownTimer(300000, 1000) {
                public void onTick(long millisUntilFinished) {
                    long millis = millisUntilFinished;
                    String time = String.format(Locale.getDefault(), "Time Remaining %02d min: %02d sec",
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
                    startTime.setText(time);
                }

                public void onFinish() {
                    setSubmitQuiz(view);
                }
            }.start();

        }
    }

    //Function that Reset Quiz and Reset all the marks and time
    @OnClick(R.id.reset)
    public void setResetQuiz(View view) {
        //Dialog to reset your Quiz
        AlertDialog.Builder resetDialog = new AlertDialog.Builder(MainActivity.this);
        resetDialog.setTitle("Alert !");
        resetDialog.setMessage("Are you sure to reset Quiz");
        //Dialog Button if you want reset
        resetDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                firstRadioGroup.clearCheck();
                secondRadioGroup.clearCheck();
                thirdRadioGroup.clearCheck();
                fourthRadioGroup.clearCheck();
                fourthAnswer.setText("");
                marks = 0;

                if (mCountDownTimer != null) {
                    mCountDownTimer.cancel();
                    mCountDownTimer.start();

                }
            }
        });
        //Dialog button if you want to continue
        resetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        resetDialog.show();
    }

    //Function when You press submit button you will get result
    @OnClick(R.id.submit)
    public void setSubmitQuiz(View view) {

        //Dialog to show your result
        Toast.makeText(this, nameOfUser.getText().toString() + " " + marks + "/100" + remarks, Toast.LENGTH_LONG).show();
        final AlertDialog.Builder submitDialog = new AlertDialog.Builder(MainActivity.this);
        submitDialog.setIcon(R.drawable.ic_submit_dialog);
        submitDialog.setTitle(R.string.dialogTitle);
        submitDialog.setMessage("Hi " + nameOfUser.getText().toString() + " :" +
                " You got" + ": " + marks + "/100" + " " + "\n" + remarks
        );
        submitDialog.setNegativeButton("Finish", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent startAgain = new Intent(MainActivity.this, FrontPage.class);
                startActivity(startAgain);
            }
        }).show();


    }
}

