package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
        Integer[] courseMyTotalEx = new Integer[courses.length];
        Integer[] courseMyHours = new Integer[courses.length];
        Integer[] totalSubmissions = new Integer[courses.length];
        Integer[] totalExercises = new Integer[courses.length];
        Double[] totalHours = new Double[courses.length];

        for (int i=0; i<courses.length; i++){
            courseMyTotalEx[i] = 0;
            courseMyHours[i] = 0;
            totalSubmissions[i] = 0;
            totalExercises[i] = 0;
            totalHours[i] = 0.0;

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
                        courseMyHours[i] += submission.getHours();
                        courseMyTotalEx[i] += submission.getExercices().length;
                        
                    }
                }
            
        }
        
        for (int i=0; i<courses.length; i++){
            if (courses[i] != null){
                String newurl = "https://studies.cs.helsinki.fi/courses/" + courses[i].name + "/stats";
                
                String statsResponse = Request.Get(newurl).execute().returnContent().asString();
                
                JsonParser parser = new JsonParser();
                JsonObject parsedData = parser.parse(statsResponse).getAsJsonObject();
                System.out.println(courses[i].fullName + " " + courses[i].term + " " + courses[i].year + "\n");

                
                for (Submission submission : subs) {
                    if (submission.getCourseName().equals(courses[i].name)) {


                        System.out.println(submission);
                    }
                }
                System.out.println(courses[i].name);
                ArrayList<String> avaimet = new ArrayList<>();
                avaimet.addAll(parsedData.keySet());
                avaimet.forEach(s -> System.out.println(s));
                for (int j=0; j<avaimet.size(); j++){
                    
                    JsonObject vastaus = parsedData.getAsJsonObject(avaimet.get(j));
                  
                    totalSubmissions[i] += Integer.parseInt(vastaus.get("students").toString());
                    
                    totalHours[i] += Double.parseDouble(vastaus.get("hour_total").toString());
                    totalExercises[i] += Integer.parseInt(vastaus.get("exercise_total").toString());
                }
            System.out.println("Yhteensä: " + courseMyTotalEx[i] + "/" + courses[i].totalExercises() + " " + courseMyHours[i] + " tuntia\n");
            System.out.println("Kurssilla yhteensä " + totalSubmissions[i] + " palautusta, palautettuja tehtäviä " + totalExercises[i] + " kpl, aikaa käytetty " + totalHours[i] + " tuntia");
            }
        }
        
    }
}