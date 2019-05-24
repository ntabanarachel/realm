package com.example.myrealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    EditText reg,name;
    Button view,add,delete,update;
    TextView text;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reg=(EditText)findViewById(R.id.etreg);
        name=(EditText)findViewById(R.id.etname);
        view=(Button)findViewById(R.id.btnview);
        add=(Button)findViewById(R.id.btnadd);
        delete=(Button)findViewById(R.id.btndelete);
        update=(Button)findViewById(R.id.btnupdate);
        text=(TextView)findViewById(R.id.txtview);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

    }


    public void clickevent(View view) {
        switch (view.getId()){
            case R.id.btnadd:
                addrecord();
                break;
            case R.id.btnview:
                viewrecord();
                break;
            case R.id.btnupdate:
                update();
                break;
            case R.id.btndelete:
                delete();
                break;

        }

    }

//   method addrecord
    public  void  addrecord(){
        realm.beginTransaction();
        Employee employee=realm.createObject(Employee.class);
        employee.setReg(Integer.parseInt(reg.getText().toString()));
        employee.setName(name.getText().toString());

        realm.commitTransaction();

    }
    public void viewrecord(){
        RealmResults<Employee>results =realm.where( Employee.class).findAll();

        text.setText("");

        for (Employee employee :results){
            text.append(employee.getReg() +"  "+employee.getName()+"\n" );
        }
    }

    public void update(){
        RealmResults<Employee> results =realm.where(Employee.class) .equalTo("reg",Integer.parseInt(reg.getText().toString())).findAll();

        realm.beginTransaction();
        for (Employee employee : results){
            employee.setName(name.getText().toString());
        }
        realm.commitTransaction();
    }
    public void delete(){

        RealmResults<Employee> results =realm.where(Employee.class) .equalTo("reg",Integer.parseInt(reg.getText().toString())).findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();

    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}

