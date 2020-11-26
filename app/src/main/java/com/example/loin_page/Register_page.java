package com.example.loin_page;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.renderscript.Script;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Register_page<FirebaseAuth> extends AppCompatActivity {
    ImageView ed_chose;
    RadioButton rd_gender;
    EditText ed_name,ed_email,ed_pass,ed_co_pass,ed_phone;
    Button bt_gallery,bt_register;
    RadioGroup gender;
    private static final int PICK_IMAGE = 100;
    Uri imageUri =null;
    ProgressDialog progressDialog;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        ed_chose=findViewById(R.id.ed_gallery);
        rd_gender=findViewById(R.id.rd_gender);

        ed_name=findViewById(R.id.ed_name);
        ed_email=findViewById(R.id.ed_email);
        ed_pass=findViewById(R.id.ed_pass);
        ed_co_pass=findViewById(R.id.ed_re_cnf_pass);
        ed_phone=findViewById(R.id.ed_call);
        bt_register=findViewById(R.id.regi);
        bt_gallery=findViewById(R.id.ed_gal_bt);
        progressDialog=new ProgressDialog(this);

        bt_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

    }



    private  void openGallery(){
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(gallery.ACTION_GET_CONTENT);
        startActivityForResult(gallery,01);
 }
 @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==01&&requestCode==RESULT_OK){
            imageUri=data.getData();
        }
    }
    private void checkValidation(){
        String usname=ed_name.getText().toString().trim();
                String email=ed_email.getText().toString().trim();
        String pass=ed_pass.getText().toString().trim();
                String cnpass=ed_co_pass.getText().toString().trim();
        String phone=ed_phone.getText().toString().trim();
        if(TextUtils.isEmpty(usname)){
            ed_name.setError("Please Enter your name");
        }else if (TextUtils.isEmpty(email)){
            ed_email.setError("Please Enter your name");
        }else if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            ed_email.setError("Enter valid email");

        }else if(TextUtils.isEmpty(pass)){
            ed_pass.setError("Please Enter your password");
        }else if(TextUtils.isEmpty(cnpass)) {
            ed_co_pass.setError("Re-Enter your password");
        }
        else if(!(ed_pass.equals(cnpass))){
            Toast.makeText(this, "Password not mathch", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(phone)){
            ed_phone.setError("Please Enter your Phone Number");
        }else if(imageUri==null){
            Toast.makeText(this, "Please chose an image", Toast.LENGTH_SHORT).show();
        }else{
            int selecteID=gender.getCheckedRadioButtonId();
            rd_gender=findViewById(selecteID);
            String user_gender=rd_gender.getText().toString();
            insertInToDataBase(usname,email,pass,cnpass,user_gender,imageUri);
        }

    }

    private void insertInToDataBase(String usname, String email, String pass, String cnpass, String user_gender, Uri imageUri) {
    progressDialog.setMessage("Please wait...");
    progressDialog.setCancelable(false);
    progressDialog.show();
    auth.createUserWithEmailAndPassword();

    }


}