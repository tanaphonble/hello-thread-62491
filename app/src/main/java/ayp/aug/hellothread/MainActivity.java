package ayp.aug.hellothread;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    RadioButton mRadioButtonRed;
    RadioButton mRadioButtonBlue;
    RadioButton mRadioButtonGreen;
    TextView mTextView;

    Button mButton;

    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadioButtonRed = (RadioButton) findViewById(R.id.rdo1);
        mRadioButtonRed.setTextColor(Color.RED);
        mRadioButtonBlue = (RadioButton) findViewById(R.id.rdo2);
        mRadioButtonBlue.setTextColor(Color.BLUE);
        mRadioButtonGreen = (RadioButton) findViewById(R.id.rdo3);
        mRadioButtonGreen.setTextColor(Color.GREEN);

        mTextView = (TextView) findViewById(R.id.txtResult);

        mButton = (Button) findViewById(R.id.button1);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRadioButtonRed.isChecked())
                    startProgress("RED");
                else if(mRadioButtonBlue.isChecked())
                    startProgress("BLUE");
                else if (mRadioButtonGreen.isChecked())
                    startProgress("GREEN");
            }
        });
    }

    public void startProgress(final String color) {
        Runnable runner = new Runnable() {
            final String threadColor = color;

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    final int value = i;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ie){
                        ie.printStackTrace();
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String currentThread = "<br><font color='"
                                    + threadColor.toString() + "'>Thread <b>"
                                    + threadColor.toString() + "</b>"
                                    + ": Process i=" + value + "</font>";
                            result += currentThread;
                            mTextView.setText(Html.fromHtml(result));
                        }
                    });
                }
            }
        };
        new Thread(runner).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
