package com.example.implicitintent;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.example.implicitintent.databinding.ActivityMainBinding;

import java.io.File;


public class MainActivity extends AppCompatActivity {
Button email,sms,google,call,picature,link;
ImageView newImage;
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //send picture to any chat app
        binding.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ee = new Intent(Intent.ACTION_SEND);
                ee.setData(Uri.parse("mailto:"));
               ee.putExtra(Intent.EXTRA_EMAIL,new String[]{"abdomohrm1@gmail.com"});
                ee.putExtra(Intent.EXTRA_SUBJECT,"This is subject");
                Uri uri = Uri.parse("android.resource://"+getPackageName()+"/drawable/"+R.drawable.azkeralmoslam);
              ee.putExtra(Intent.EXTRA_STREAM,uri);
               ee.setType("image/*");
              Intent chosse = Intent.createChooser(ee,"tt");//topic chosser
               startActivity(chosse);

            }
        });
        //send sms to any mumber
        binding.sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smms = new Intent(Intent.ACTION_SENDTO);
                smms.setData(Uri.parse("smsto:"));
                smms.putExtra("sms_body","Helloworld");
                startActivity(smms);
            }
        });
        //open map
        binding.googlemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goog= new Intent(Intent.ACTION_VIEW);
                goog.setData(Uri.parse("geo:18.09,77,776"));
                startActivity(goog);
            }
        });
        //do call to any mumber
        binding.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calll= new Intent(Intent.ACTION_DIAL);
                calll.setData(Uri.parse("tel:"));
                startActivity(calll);
            }
        });
        //open this link
        binding.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent link =new Intent(Intent.ACTION_VIEW);
                link.setData(Uri.parse("http://youtu.be/6evRBXdlFQI"));
                startActivity(link);
            }
        });
        //get picture from gallery
        ActivityResultLauncher<String> arl = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        binding.imageView.setImageURI(result);
                    }
                }
        );
        binding.picature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent pic = new Intent(Intent.ACTION_PICK);
               // pic.setType("image/*");
               // startActivityForResult(Intent.createChooser(pic,"تجيب منين"),4);
                arl.launch("image/*");
            }
        });

    }


   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       // Bitmap bit = (Bitmap) data.getExtras().get("data");
       // camera.setImageBitmap(bit);
        binding.imageView.setImageURI(data.getData());
    }*/
}