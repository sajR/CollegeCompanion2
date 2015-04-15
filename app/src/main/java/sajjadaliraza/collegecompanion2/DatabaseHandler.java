package sajjadaliraza.collegecompanion2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajja_000 on 14/04/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper{



    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "CollegeCompanionDatabase",
            TABLE_STUDENTS = "Students",
            KEY_StudentNumber="StudentNumber",
            KEY_FirstName = "firstName",
            KEY_LastName = "lastName",
            KEY_DOB="DOB",
            KEY_Email = "email",
            KEY_phone = "phone",
            KEY_Address = "address",
            KEY_StudentImage = "studentImage",


    TABLE_BOOKS="Books",
            KEY_ISBN = "ISBN",
            KEY_BookName = "BookName",
            KEY_Author = "Author",
            KEY_Status = "status",
            KEY_CoverImage = "coverImage",
            KEY_BookQR = "QR",
            KEY_StudentNumberBooks="studentNumber",

    TABLE_LESSONS="Lessons",
            KEY_LessonID = "LessonID",
            KEY_LessonName= "lessonName",
            KEY_LecturerName = "lecturerName",
            KEY_RoomNumber = "roomNum",

    TABLE_REGISTRATIONS="Registrations",
            KEY_RegistrationID="RegistrationID",
            KEY_studentNumberReg="studentNumber",
            KEY_LessonIDReg="LessonID",
            KEY_RegistrationQR="QR";








    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_STUDENTS + "(" + KEY_StudentNumber + " INTEGER PRIMARY KEY,"+ KEY_FirstName + " TEXT," + KEY_LastName + " TEXT," + KEY_DOB+ " TEXT,"+ KEY_Email + " TEXT," + KEY_Address + " TEXT," + KEY_phone+ " TEXT,"+KEY_CoverImage + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_BOOKS + "(" + KEY_ISBN + " TEXT PRIMARY KEY,"+KEY_BookName+" TEXT," + KEY_Author + " TEXT," + KEY_Status + " TEXT," + KEY_BookQR+" TEXT,"+KEY_CoverImage + " TEXT," + KEY_StudentNumberBooks + " INTEGER)");
        db.execSQL("CREATE TABLE " + TABLE_LESSONS + "(" + KEY_LessonID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_LessonName+" TEXT," + KEY_LecturerName + " TEXT," + KEY_RoomNumber + " INTEGER)");
        db.execSQL("CREATE TABLE " + TABLE_REGISTRATIONS + "(" + KEY_RegistrationID + " INTEGER PRIMARY KEY," + KEY_LessonIDReg + "INTEGER," + KEY_studentNumberReg + " INTEGER," + KEY_RegistrationQR + " INTEGER)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LESSONS);

        onCreate(db);
    }


    //region student
    public void createStudent(Students student) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_StudentNumber,student.getStudentNumber());
        values.put(KEY_FirstName, student.getFirstName());
        values.put (KEY_LastName, student.getLastName());
        values.put (KEY_DOB, student.getDOB());
        values.put(KEY_phone, student.getPhone());
        values.put(KEY_Email, student.getEmail());
        values.put(KEY_Address, student.getAddress());
        values.put(KEY_StudentImage, student.getStudentImage().toString());

        db.insert(TABLE_STUDENTS, null, values);
        db.close();
    }
    public void createBook(Books book)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_ISBN,book.getISBN());
        values.put(KEY_BookName,book.getbookName());
        values.put(KEY_Status,book.getStatus());
        values.put(KEY_CoverImage, book.getbookName().toString());
        values.put(KEY_BookQR, book.getQR());
//student number
        db.insert(TABLE_BOOKS,null,values);
        db.close();
    }
    public void createLesson(Lessons lesson)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_LessonID,lesson.getlessonID());
        values.put(KEY_LessonName, lesson.getlessonName());
        values.put(KEY_LecturerName,lesson.getlecturerName());
        values.put(KEY_RoomNumber,lesson.getroomNum());
        values.put(KEY_BookQR, lesson.getLessonQR());

        db.insert(TABLE_LESSONS,null,values);
        db.close();

    }
    public void createRegistration(Registrations registration)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_RegistrationID, registration.getRegistrationID());
        values.put(KEY_LessonIDReg, registration.getLessonID());
        values.put(KEY_studentNumberReg, registration.getRegistrationID());
        values.put(KEY_RegistrationQR,registration.getRegistrationQR());
        db.insert(TABLE_REGISTRATIONS, null, values);
        db.close();
    }

    public Students getStudent(int id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDENTS, new String[] { KEY_StudentNumber, KEY_FirstName, KEY_LastName, KEY_DOB, KEY_Email, KEY_phone,KEY_Address,KEY_StudentImage }, KEY_StudentNumber + "=?", new String[] { String.valueOf(id) }, null, null, null, null );

        if (cursor != null)
            cursor.moveToFirst();

      Students student = new Students(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5),cursor.getString(6), Uri.parse(cursor.getString(7)));
        db.close();
        cursor.close();
        return student ;
    }

    public void deleteStudent(Students student) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_STUDENTS, KEY_StudentNumber + "=?", new String[]{String.valueOf(student.getStudentNumber())});
        db.close();
    }

    public int getStudentsCount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENTS, null);
        int count = cursor.getCount();
        db.close();
        cursor.close();

        return count;
    }
    public int getBooksCount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BOOKS, null);
        int count = cursor.getCount();
        db.close();
        cursor.close();

        return count;
    }






    public List<Students> getAllStudents() {
        List<Students> ListStudent = new ArrayList<Students>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENTS, null);

        if (cursor.moveToFirst()) {
            do {
                ListStudent.add(new Students(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5),cursor.getString(6), Uri.parse(cursor.getString(7))));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ListStudent;
    }

    public void updateBook(String QR, String StudentNumber) {
        SQLiteDatabase db = getWritableDatabase();
        String _qr=QR;
        String _studentnumber=StudentNumber;

        db.execSQL("UPDATE " + TABLE_BOOKS + " SET " + KEY_Status + "='Taken' , " + KEY_StudentNumberBooks + " = '" + _studentnumber + "' WHERE " + KEY_BookQR + "='" + _qr + "';");
        //OR
        db.execSQL("UPDATE Books SET status='Taken',studentNumber='" + Integer.parseInt(_studentnumber) + " WHERE QR='" + _qr + "';");

    }
    public void updateRegistration(String QR,String StudentNumber)
    {
        SQLiteDatabase db=getWritableDatabase();
        String _qr=QR;
        String _studentNumber=StudentNumber;

        db.execSQL("UPDATE " +TABLE_REGISTRATIONS+ " SET "+KEY_studentNumberReg+ "="+Integer.parseInt(_studentNumber)+" WHERE " + KEY_RegistrationQR + "='" + _qr + "';");
        db.execSQL("UPDATE Registrations SET studentNumber=" + Integer.parseInt(_studentNumber) + " WHERE QR='" + _qr + "';");



    }
    public void updateBooks(String QR,String StudentNumber)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_Status,"Taken");
        values.put(KEY_StudentNumberBooks,StudentNumber);
        db.update(TABLE_BOOKS, values, KEY_BookQR + "=" + QR, null);
        db.close();

    }
    public void uodateRegistratins(String QR,String StudentNumber)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_studentNumberReg,StudentNumber);
        db.update(TABLE_REGISTRATIONS,values,KEY_RegistrationQR+"="+QR,null);

    }

}