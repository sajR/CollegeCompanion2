package sajjadaliraza.collegecompanion2;

import android.net.Uri;

/**
 * Created by sajja_000 on 14/04/2015.
 */
public class Books {
    private String _ISBN, _bookName, _Author, _Status, _QR;
    private Uri _bookCover;
    private int _id, _studentNum;

    public Books (String ISBN, String bookName, String Author, String Status, Uri bookCover,String QR, int studentNum) {
        _ISBN = ISBN;
        _bookName = bookName;
        _Author = Author;
        _Status = Status;
        _bookCover = bookCover;
        _studentNum = studentNum;
        _QR = QR;

    }

    public int getId() { return _id; }

    public String getISBN() {
        return _ISBN;
    }

    public String getbookName() {
        return _bookName;
    }

    public String getAuthor() {
        return _Author;
    }

    public String getStatus() {
        return _Status;
    }

    public  String getQR()
    {
        return _QR;
    }

    public int getStudentNum(){

        return _studentNum;
    }

    public Uri getBookImage() { return _bookCover; }

}
