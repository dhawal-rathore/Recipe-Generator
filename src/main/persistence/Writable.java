package persistence;

import org.json.JSONObject;

//Inspired by JSONSerializationDemo project
//github: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

//Any class which implements this interface can be written as a JSON Object
public interface Writable {
    //EFFECTS: returns object as JSON object
    JSONObject toJson();
}
