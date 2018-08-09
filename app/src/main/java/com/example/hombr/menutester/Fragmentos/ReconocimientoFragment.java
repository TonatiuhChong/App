package com.example.hombr.menutester.Fragmentos;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hombr.menutester.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ReconocimientoFragment extends Fragment  {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private GoogleApiClient googleApiClient;

    //
    private DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("User");
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> usuarios= new ArrayList<>();
    private ListView list;

    //
    int Images[]= {R.drawable.common_full_open_on_phone,R.drawable.common_google_signin_btn_icon_dark,R.drawable.common_google_signin_btn_icon_light,R.drawable.common_google_signin_btn_icon_light_normal_background,R.drawable.ic_launcher_foreground};
    String nombres []={"chu","castelan","chng","prro","pineda"};
    String email []={"chu@gmail.com","castelan@gmail.com","chng@gmail.com","prro@gmail.com","pineda@gmail.com"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View Rec= inflater.inflate(R.layout.reconocimiento_fragment,container,false);
        //*******
        FloatingActionButton fab = Rec.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Boton vergas, luego hará algo", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        list= Rec.findViewById(R.id.contactos);
        //prueba de array
        list.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1 , usuarios));

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                //while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                //}

                usuarios.clear();
                usuarios.addAll(set);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       //
        return Rec;
    }


/*class  CustomAdapter extends BaseAdapter{

    @Override
    public int getCount() {
        return Images.length;

    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vista= getLayoutInflater().inflate(R.layout.listview_fragmento1,null);
        ImageView foto=vista.findViewById(R.id.FIhabitantes);
        TextView usuario= vista.findViewById(R.id.FThabitantes);
        TextView email= vista.findViewById(R.id.FTExtra);
        foto.setImageResource(Images[i]);
        usuario.setText(nombres[i]);

return null;
    }
}

*/
}
