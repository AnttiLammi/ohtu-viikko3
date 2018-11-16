package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }
        
        String cUrl = "https://studies.cs.helsinki.fi/courses/courseinfo";
        String cBodyText = Request.Get(cUrl).execute().returnContent().asString();
        Gson cMapper = new Gson();
        Course[] courses = cMapper.fromJson(cBodyText, Course[].class);

        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";
        String bodyText = Request.Get(url).execute().returnContent().asString();
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Integer[] courseTotalEx = new Integer[courses.length];
        Integer[] courseTotalHours = new Integer[courses.length];
        for (int i=0; i<courses.length; i++){
            courseTotalEx[i] = 0;
            courseTotalHours[i] = 0;
            Boolean taken = false;
            for (Submission submission : subs){
                if (courses[i].name.equals(submission.getCourseName())){
                    taken = true;
                } 
            }
            if (!taken) {
                courses[i] = null;
            }
        }
        for (Submission submission : subs) {
                for (int i=0; i<courses.length; i++){
                    if (courses[i] != null && submission.getCourseName().equals(courses[i].name)){
                        submission.setCourse(courses[i]);
                        courseTotalHours[i] += submission.getHours();
                        courseTotalEx[i] += submission.getExercices().length;
                    }
                }
            
        }
        
        for (int i=0; i<courses.length; i++){
            if (courses[i] != null){
                System.out.println(courses[i].fullName + " " + courses[i].term + " " + courses[i].year + "\n");

                for (Submission submission : subs) {
                    if (submission.getCourseName().equals(courses[i].name)){
                        System.out.println(submission);
                    }
                }
            System.out.println("Yhteensä: " + courseTotalEx[i] + "/" + courses[i].totalExercises() + " " + courseTotalHours[i] + " tuntia\n\n");
            }
        }
        
    }
}