package sajjadaliraza.collegecompanion2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    EditText firstNameTxt,LastNametxt, phoneTxt, emailTxt, addressTxt,StudentNumberTxt,DOBtxt;
    ImageView studentImageView;
    List<Students> ListStudent = new ArrayList<Students>();
    List<Books> ListBooks = new ArrayList<Books>();
    List<Lessons> ListLessons = new ArrayList<Lessons>();
    List<Registrations> ListRegistrations = new ArrayList<Registrations>();
    ListView StudentListView;
    Uri imageUri = Uri.parse("android.resource://org.intracode.contactmanager/drawable/no_user_logo.png");
    DatabaseHandler dbHandler;
    int longClickedItemIndex;
    ArrayAdapter<Students> studentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StudentNumberTxt=(EditText)findViewById(R.id.txtStudentNumber);
        firstNameTxt = (EditText) findViewById(R.id.txtFirstName);
        LastNametxt=(EditText) findViewById(R.id.txtLastName);
        DOBtxt=(EditText)findViewById(R.id.txtDOB);
        phoneTxt = (EditText) findViewById(R.id.txtPhone);
        emailTxt = (EditText) findViewById(R.id.txtEmail);
        addressTxt = (EditText) findViewById(R.id.txtAddress);
        StudentListView = (ListView) findViewById(R.id.listView);

        dbHandler = new DatabaseHandler(getApplicationContext());

        registerForContextMenu(StudentListView);

        StudentListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                longClickedItemIndex = position;
                return false;
            }
        });

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("creator");
        tabSpec.setContent(R.id.tabCreator);
        tabSpec.setIndicator("Creator");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("list");
        tabSpec.setContent(R.id.tabContactList);
        tabSpec.setIndicator("List");
        tabHost.addTab(tabSpec);

        final Button addBtn = (Button) findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Students student = new Students(dbHandler.getStudentsCount(), String.valueOf(firstNameTxt.getText()), String.valueOf(LastNametxt.getText()), String.valueOf(DOBtxt.getText()),String.valueOf(emailTxt.getText()),String.valueOf(phoneTxt.getText()), String.valueOf(addressTxt.getText()), imageUri);
            //    Books book=new Books(dbHandler.getBooksCount(),String.valueOf(ISBNtxt.getText()),String.valueOf(BookNametxt.getText()),String.valueOf(Authortxt.getText()),String.valueOf(Statustxt.getTEXT()),bookImage,)
              Books book=new Books("AB121","ComputerScience","Stephen Smith","Available",null,"b112",0);
                Books book1=new Books("AB121","ComputerScience","sajjR","Taken",null,"b112",Integer.parseInt("21999"));
                Books book2=new Books("AB121","Computers","Shannon Smith","Taken",null,"b112",Integer.parseInt("290776"));
                Registrations registration2=new Registrations(1,3,0,"QR123");
                Registrations registration3=new Registrations(4,5,0,"QR123");
                dbHandler.createRegistration(registration2);
                dbHandler.createRegistration(registration3);


                if (!studentExists(student)) {
                    dbHandler.createStudent(student);
                    ListStudent.add(student);
                    studentAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), String.valueOf(firstNameTxt.getText()) + " has been added to your Contacts!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!bookExits(book)) {
                    dbHandler.createBook(book);
                    ListBooks.add(book);
                  bookAdapater.notifyDataSetChanged();
                    studentAdapter.notifyDataSetChanged();
                }

                Toast.makeText(getApplicationContext(), String.valueOf(firstNameTxt.getText()) + " already exists. Please use a different name.", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), String.valueOf(bookName.getText()) + " already exists. Please use a different name.", Toast.LENGTH_SHORT).show();
            }
        });
        firstNameTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                addBtn.setEnabled(String.valueOf(firstNameTxt.getText()).trim().length() > 0);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        }); studentImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select student Image"), 1);
            }

        });

        if (dbHandler.getStudentsCount() != 0)
            ListStudent.addAll(dbHandler.getAllStudents());

        populateList();
    }
    private boolean studentExists(Students student) {
        String name = student.getFirstName();
        int contactCount = ListStudent.size();

        for (int i = 0; i < contactCount; i++) {
            if (name.compareToIgnoreCase(ListStudent.get(i).getFirstName()) == 0)
                return true;
        }
        return false;
    }
    private void populateList() {
      studentAdapter= new studentListAdapter();
        StudentListView.setAdapter(studentAdapter);
        bookAdapter=new BookListAdapter();
        BookListView=setAdapter(bookAdapter);
    }
    private class studentListAdapter extends ArrayAdapter<Students> {
        public studentListAdapter() {
            super (MainActivity.this, R.layout.listview_item, ListStudent);
        }
        private class BookListAdapter extends ArrayAdapter<Books> {
            public bookListAdapter() {
                super(MainActivity.this, R.layout.listview_item, ListStudent);
            }yeah


        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);

            Students currentStudent = ListStudent.get(position);

            TextView name = (TextView) view.findViewById(R.id.contactName);
            name.setText(currentStudent.getFirstName());
            TextView phone = (TextView) view.findViewById(R.id.phoneNumber);
            phone.setText(currentStudent.getPhone());
            TextView email = (TextView) view.findViewById(R.id.emailAddress);
            email.setText(currentStudent.getEmail());
            TextView address = (TextView) view.findViewById(R.id.cAddress);
            address.setText(currentStudent.getAddress());
            ImageView ivContactImage = (ImageView) view.findViewById(R.id.ivContactImage);
            ivContactImage.setImageURI(currentStudent.getStudentImage());

            return view;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
