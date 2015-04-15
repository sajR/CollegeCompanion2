package sajjadaliraza.collegecompanion2;

import android.net.Uri;

/**
 * Created by sajja_000 on 14/04/2015.
 */
public class Students {

    private String _firstName,_lastName, _phone, _email, _address,_DOB;
    private int _studentNumber;
    private Uri _studentImage;

    public Students (int studentNumber, String firstName, String lastName,String DOB,String email, String phone, String address,Uri studentImage) {
        _studentNumber=studentNumber;
        _firstName = firstName;
        _lastName=lastName;
        _DOB=DOB;
        _email = email;
        _phone=phone;
        _address = address;
        _studentImage=studentImage;

    }

    public int getStudentNumber(){return _studentNumber;}
    public String getFirstName() {return _firstName;}
    public String getLastName() {return _lastName;}
    public String getDOB(){return _DOB;}
    public String getEmail() { return _email;}
    public String getAddress() { return _address;}
    public String getPhone(){return _phone;}
    public Uri getStudentImage(){return _studentImage;}


}
