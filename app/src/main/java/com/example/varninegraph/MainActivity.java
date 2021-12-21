package com.example.varninegraph;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {

//    private TextView showErrors =(TextView)findViewById(R.id.errors);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        TextView showErrors = (TextView) findViewById(R.id.errors);
        showErrors.setVisibility(View.INVISIBLE);

    }

    public void onDrawGraph (View v){
        EditText editNx1 = (EditText) findViewById(R.id.x1Edit);
        EditText editNx2 = (EditText) findViewById(R.id.x2Edit);
        EditText inputT = (EditText) findViewById(R.id.tEdit);
        TextView showErrors = (TextView) findViewById(R.id.errors);
        showErrors.setVisibility(View.INVISIBLE);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.removeAllSeries();
        boolean test=true;
        try {
            int inputX1 = Integer.parseInt(editNx1.getText().toString());
            int inputX2 = Integer.parseInt(editNx2.getText().toString());
            double t = Double.parseDouble(inputT.getText().toString());
        }
        catch (Exception e)
        {
            showErrors.setVisibility(View.VISIBLE);
            String x1,x2,t;
            x1=editNx1.getText().toString();
            x2=editNx2.getText().toString();
            t=inputT.getText().toString();
            if(x1.isEmpty())
                showErrors.setText("Заповніть x1");
            else if(x2.isEmpty())
                showErrors.setText("Заповніть x2");
            else if(t.isEmpty())
                showErrors.setText("Заповніть t");
            else showErrors.setText("Помилка при конвертації");
            System.out.println("x1="+x1+" x2="+x2+" t="+t);
            test=false;
        }
        finally{
            if(test) {
                int x1 = Integer.parseInt(editNx1.getText().toString());
                int x2 = Integer.parseInt(editNx2.getText().toString());
                int dif=x2-x1;
                double t = Double.parseDouble(inputT.getText().toString());

                if (t<=0) {
                    showErrors.setVisibility(View.VISIBLE);
                    showErrors.setText("значення t не задовольняє умови");
                }
                else {

                    LineGraphSeries<DataPoint> series=
                            new LineGraphSeries<>();
                    double y;
                    for(int x=x1;x<x2;x++){
                        y=(4.351*Math.pow(x,3)+2*t*Math.log(t))/(Math.sqrt(Math.cos(2*x)+4.351));
                        series.appendData(new DataPoint(x,y),true,dif);
                    }
                    graph.addSeries(series);

                }
            }
        }

    }

}