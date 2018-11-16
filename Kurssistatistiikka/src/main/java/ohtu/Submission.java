package ohtu;


public class Submission {
    private String course;
    private Course courseFull;
    private int week;
    private int hours;
    private Integer[] exercises;

    public Submission(String course, int week, int hours, Integer[] exercises) {
        this.course = course;
        this.week = week;
        this.hours = hours;
        this.exercises = exercises;
        this.courseFull = null;
    
    }
    public void setCourse(Course course){
        this.courseFull = course;
    }
    public Course getCourse(){
        return this.courseFull;
    }
    public String getCourseName(){
        return this.course;
    }
    public int getWeek() {
        return week;
    }
    public int getHours(){
        return this.hours;
    }
    public Integer[] getExercices(){
        return this.exercises;
    }

    @Override
    public String toString() {
       
        String r2 = "Viikko " + this.week+ ": \n";
        String r3p1 = "\ttehtyjä tehtäviä " + this.exercises.length + "/" + this.courseFull.exercises[this.week]+ ", aikaa kului " + hours + " tuntia. Tehdyt tehtävät: ";
        String r3p2 = "";
        for (int i=0; i<exercises.length-2; i++){
            r3p2 = r3p2 + exercises[i].toString() + ", ";
        }
        r3p2 = r3p2 + this.exercises[this.exercises.length-1] + "\n"+"\n";
        return r2+r3p1+r3p2;        
    }
}