package vpjardim.com.contactsfix;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Permissions.Callback,
        ContactsLoader.Callback {

    Permissions permissions;
    ViewFlipper vf;
    Toolbar toolbar;
    ListView listView;
    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        permissions = new Permissions();

        vf = (ViewFlipper) findViewById(R.id.viewFlipper);
        FloatingActionButton startButton = (FloatingActionButton) findViewById(R.id.start);

        contacts = new ArrayList<>();

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ContactsArrayAdapter(this, contacts));

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(permissions.checkPermission(
                        MainActivity.this, Permissions.READ_CONTACTS, MainActivity.this)) {
                    processContacts();
                }
            }
        });
    }

    public void processContacts() {

        Toast.makeText(this, "Processing...", Toast.LENGTH_SHORT).show();

        ContactsLoader contactsLoader = new ContactsLoader(this, this, contacts);
        Bundle bundle = new Bundle();
        getLoaderManager().restartLoader(0, bundle, contactsLoader);
    }

    @Override
    public void onPermissionGranted() { processContacts(); }

    @Override
    public void onPermissionDenied() {
        Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String strPermissions[], int[] grantResults) {
        permissions.onPermissionsResult(requestCode, grantResults);
    }

    @Override
    public void onLoadFinished() {
        ((ContactsArrayAdapter)listView.getAdapter()).notifyDataSetChanged();
        vf.showNext();
    }
}
