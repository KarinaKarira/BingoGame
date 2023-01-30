package com.example.qr_2;

//import androidx.appcompat.app.AppCompatActivity;


import static android.os.Build.VERSION_CODES.R;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketguide.R;
import com.google.zxing.WriterException;
//import androidmads.library.qrgenearatorandroid.QRGContents;
//import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity<QRGEncoder> extends AppCompatActivity {

    private ImageView qrCode;
    private EditText inputData;
    private Button generateBtn;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialsing the variables
        qrCode=findViewById(R.id.imageHolder);
        inputData=findViewById(R.id.inputText);
        generateBtn=findViewById(R.id.idBtnGenerateQR);

        //onclicklistener for button
        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(inputData.getText().toString())){
                    //if inputData is empty show this message
                    Toast.makeText(MainActivity.this,"Enter some text to generate QR code",Toast.LENGTH_SHORT).show();
                }
                else{
                    //for getting windowmanager service
                    WindowManager manager=(WindowManager) getSystemService(WINDOW_SERVICE);

                    //initialising a variable for default display
                    Display display=manager.getDefaultDisplay();

                    //variable for point to be displayed in QR
                    Point point=new Point();
                    display.getSize(point);

                    //fetching width and height of the point
                    int width=point.x;
                    int height=point.y;

                    //genearting dimensions from width and height
                    int dimen=width<height?width:height;
                    dimen=dimen*3/4;

                    //passing this dimension to QR code encoder in order to generate a QR Code
                    Object QRGContents = null;
                    assert QRGContents != null;
                    qrgEncoder=new QRGEncoder(inputData.getText().toString(),null,QRGContents.Type.TEXT,dimen);
                    try{
                        bitmap=qrgEncoder.encodeAsBitmap();
                        qrCode.setImageBitmap(bitmap);
                    }
                    catch(WriterException e) {
                        Log.e("Tag",e.toString());
                    }
                    }



                }


        });
    }
}
