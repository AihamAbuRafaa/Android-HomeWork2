package com.example.majdh.homework2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class quizActivity extends AppCompatActivity{

    private TextView questionNum;
    private TextView questionContent;
    private ListView answersListView;
    private ArrayList<Question> questions;
    private int currQuestion;
    private ArrayList<Integer> collectedAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionNum = (TextView)findViewById(R.id.questionNum);
        questionContent = (TextView)findViewById(R.id.questionContent);
        answersListView = (ListView)findViewById(R.id.answers);
        questions = new ArrayList<Question>();
        currQuestion = 0;
        collectedAnswers = new ArrayList<Integer>();

        //questions
        addQuestions();

        //start with question 1
        showQuestion(questions.get(currQuestion));

        //answer onclick
        answersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                collectedAnswers.add(position);
                if(currQuestion < questions.size()-1)
                {
                    currQuestion++;
                }
                else {
                    Intent resultPage = new Intent(quizActivity.this, ResultActivity.class);
                    resultPage.putExtra("questions", questions);
                    resultPage.putExtra("collectedAnswers", collectedAnswers);
                    startActivity(resultPage);
                }
                showQuestion(questions.get(currQuestion));
            }
        });

    }

    private void addQuestions() {
        Question q1 = new Question(1, "In which year did Maradona score a goal with his hand?");
        q1.add_answer("1984");
        q1.add_answer("1985");
        q1.add_answer("1986");
        q1.add_answer("1987");
        q1.setIndexOfCurrentAnswer(2);
        questions.add(q1);

        Question q2 = new Question(2, "What is the name of the Barcelona football stadium?");
        q2.add_answer("Santiago Bernabeu");
        q2.add_answer("Camp Nou");
        q2.add_answer("San Siro");
        q2.add_answer("Maracanã");
        q2.setIndexOfCurrentAnswer(1);
        questions.add(q2);

        Question q3 = new Question(3, "What was the Olympic city of 1992?");
        q3.add_answer("Barcelona");
        q3.add_answer("Madrid");
        q3.add_answer("Bayern Munich");
        q3.add_answer("Juventus");
        q3.setIndexOfCurrentAnswer(0);
        questions.add(q3);

        Question q4 = new Question(4, "How many teams played in the first World Cup?");
        q4.add_answer("9");
        q4.add_answer("13");
        q4.add_answer("24");
        q4.add_answer("32");
        q4.setIndexOfCurrentAnswer(1);
        questions.add(q4);

        Question q5 = new Question(5, "Who scored two goals in the 1998 World Cup final?");
        q5.add_answer("Ronaldo");
        q5.add_answer("Michael Owen");
        q5.add_answer("Davor Šuker");
        q5.add_answer("Zinedine Zidane");
        q5.setIndexOfCurrentAnswer(3);
        questions.add(q5);

        Question q6 = new Question(6, "Which was the first country to host the World Cup twice?");
        q6.add_answer("Brazil");
        q6.add_answer("Canada");
        q6.add_answer("Mexico");
        q6.add_answer("United States");
        q6.setIndexOfCurrentAnswer(2);
        questions.add(q6);

        Question q7 = new Question(7, "Who scored the quickest goal ever by an England player in a World Cup match?");
        q7.add_answer("Bryan Robson");
        q7.add_answer("Peter Shilton");
        q7.add_answer("Michael Owen");
        q7.add_answer("John Terry");
        q7.setIndexOfCurrentAnswer(0);
        questions.add(q7);

        Question q8 = new Question(8, "How many games were played at the World Cup 2006?");
        q8.add_answer("72");
        q8.add_answer("68");
        q8.add_answer("67");
        q8.add_answer("64");
        q8.setIndexOfCurrentAnswer(3);
        questions.add(q8);

        Question q9 = new Question(9, "How many games will the winning team have to play?");
        q9.add_answer("6");
        q9.add_answer("7");
        q9.add_answer("8");
        q9.add_answer("9");
        q9.setIndexOfCurrentAnswer(1);
        questions.add(q9);

        Question q10 = new Question(10, "How many different countries have won the World Cup prior to 2010?");
        q10.add_answer("5");
        q10.add_answer("6");
        q10.add_answer("7");
        q10.add_answer("8");
        q10.setIndexOfCurrentAnswer(2);
        questions.add(q10);
    }

    private void showQuestion(Question q)
    {
        ArrayList<String> answers = q.getAnswers();
        questionNum.setText("Question number " + q.getQuestionNum());
        questionContent.setText(q.getQuestionContent());
        ItemsAdapter answersAdapter = new ItemsAdapter(this, answers);
        answersListView.setAdapter(answersAdapter);
    }

}
