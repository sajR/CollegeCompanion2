package sajjadaliraza.collegecompanion2;

/**
 * Created by sajja_000 on 14/04/2015.
 */
public class Lessons {

    private String _lessonName, _lecturerName,_lessonQR;
    private int _lessonID,_roomNum;

    public Lessons (int LessonID, String lessonName, String lecturerName, int roomNum,String LessonQR) {
        _lessonID = LessonID;
        _lessonQR=LessonQR;
        _lessonName = lessonName;
        _lecturerName = lecturerName;
        _roomNum = roomNum;

    }

    public int getlessonID() { return _lessonID; }

    public String getlessonName() {
        return _lessonName;
    }

    public String getlecturerName() {
        return _lecturerName;
    }

    public int getroomNum() {
        return _roomNum;
    }

    public String getLessonQR(){return _lessonQR;}
}
