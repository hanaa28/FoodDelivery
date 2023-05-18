package com.example.fooddelivery.activities.avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.SharedPrefs;
import com.example.fooddelivery.databinding.ActivityPhotoBinding;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class photoActivity extends AppCompatActivity {
    ActivityPhotoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo);
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(photoActivity.this,MainresActivity.class);
                startActivity(intent);

                }

        });
        binding.slectphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent,100);

            }
        });
        binding.takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent1,101);


            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==100 && resultCode==RESULT_OK){
            Uri uri=data.getData();
            binding.viewImage.setImageURI(uri);
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                covertphoto(bitmap);
            } catch (Exception e) {

            }
        } else if (requestCode==101 && resultCode==RESULT_OK){
            binding.viewImage.setImageBitmap((Bitmap) data.getExtras().get("data"));
            covertphoto((Bitmap) data.getExtras().get("data"));
        }
//
//        Bitmap photo= (Bitmap) data.getExtras().get("data");
//        imgbtn.setImageBitmap(photo);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] b = baos.toByteArray();
//        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
//        preferenceManager.setString("image_data",encodedImage);

    }
    public void covertphoto(Bitmap photo)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        SharedPrefs sharedPrefs = new SharedPrefs(getApplicationContext());
        sharedPrefs.setProfilePhoto(encodedImage);
    }    }
