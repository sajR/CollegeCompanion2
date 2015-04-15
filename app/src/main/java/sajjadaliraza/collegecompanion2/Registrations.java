package sajjadaliraza.collegecompanion2;

/**
 * Created by sajja_000 on 14/04/2015.
 */
public class Registrations {
    private int  _RegistrationID,_lessonID,_studentNumber;


    public Registrations (int registrationID,int lessonID, int studentNumber) {
        _RegistrationID = registrationID;
        _studentNumber=studentNumber;
        _lessonID = lessonID;
    }

    public int getRegistrationID() { return _RegistrationID; }
    public int getStudentNumber(){return _studentNumber;}
    public int getLessonID() {return _lessonID;}

}
