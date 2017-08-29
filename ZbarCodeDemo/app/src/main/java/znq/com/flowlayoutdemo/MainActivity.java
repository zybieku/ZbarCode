package znq.com.flowlayoutdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.znq.zbarcode.CaptureActivity;

public class MainActivity extends AppCompatActivity {

    private int QR_CODE =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button(View view){
        Intent intent1 = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent1, QR_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == QR_CODE) {
            Bundle b=data.getExtras();
            String result = b.getString(CaptureActivity.EXTRA_STRING);
            Toast.makeText(this, result + "", Toast.LENGTH_SHORT).show();

            Intent in =new Intent(this,ResultsActivity.class);
            in.putExtra(CaptureActivity.EXTRA_STRING, result);
            startActivity(in);

        }
    }
}
