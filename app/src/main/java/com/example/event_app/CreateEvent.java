package com.example.event_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class CreateEvent extends Fragment {
    private static final int RESULT_OK = -1;
    EditText title;
    EditText org;
    EditText date;
    EditText number;
    EditText deadline;
    EditText desc;
    TextView filename;
    Button upload;
    Button btn_create;
    String t="",o="",d="",num="",del="",des="",file="";
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    FirebaseStorage storage;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseAuth mAuth;
    Uri pdfUri;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= inflater.inflate(R.layout.activity_create_event,container,false);
        title=view.findViewById(R.id.c_title);
        org=view.findViewById(R.id.c_org);
        date=view.findViewById(R.id.c_date);
        number=view.findViewById(R.id.c_phone);
        deadline=view.findViewById(R.id.c_deadline);
        desc=view.findViewById(R.id.c_desc);
        filename=view.findViewById(R.id.c_filename);
        upload=view.findViewById(R.id.c_upload);
        btn_create=view.findViewById(R.id.c_btn_create);

        storage=FirebaseStorage.getInstance();
        rootnode=FirebaseDatabase.getInstance();

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                {
                    selectpdf();
                }
                else
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
            }
        });

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(pdfUri!=null)
//                    uploadFile(pdfUri);
                StorageReference storageReference=storage.getReference();
                reference = rootnode.getReference("Events");

                t=title.getText().toString();
                o=org.getText().toString();
                d=date.getText().toString();
                num=number.getText().toString();
                del=deadline.getText().toString();
                des=desc.getText().toString();
                UserhelperCreate u=new UserhelperCreate(t,o,d,num,del,des);
                reference.child(t).setValue(u);
                Toast.makeText(getActivity(),"Event Created Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), Home_admin.class);
                startActivity(intent);
            }
        });

        return view;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==9&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            selectpdf();
        }
        else
            Toast.makeText(getActivity(),"Please provide Permission",Toast.LENGTH_SHORT).show();
    }

    public void selectpdf() {
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Pdf"),86);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 86 && resultCode == RESULT_OK && data != null) {
            pdfUri = data.getData();
            filename.setText(pdfUri.toString());
        } else {
            Toast.makeText(getActivity(), "File not selected", Toast.LENGTH_SHORT).show();
        }
    }
}