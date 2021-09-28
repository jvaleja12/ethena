package com.example.ethenatest;

import org.apache.maven.internal.commons.io.input.BOMInputStream;
import org.json.JSONObject;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jyotivaleja
 */
public class AmaflixManager {

    private static final String pathCsv = "/Users/jyotivaleja/Documents/CSOD-Source/ethena-be-interview-main/tv_shows.csv";
    public static void main(String[] args) {
        ICsvMapReader mapReader=null;
        Character delimiterChar=',';
        CsvPreference.Builder csvPreference = new CsvPreference.Builder('\"', delimiterChar, "\n");
        List<Map<String, String>> result = new ArrayList<>();
        try {
            mapReader = new CsvMapReader(new InputStreamReader(new BOMInputStream(new FileInputStream(pathCsv)),"UTF-8"),((CsvPreference.Builder) csvPreference).build());
            final String[] header = mapReader.getHeader(true);
            Map<String, String> record;
            JSONObject input= getInput();
            ArrayList<String> inputAlreadySeen = new ArrayList<>();
            ArrayList<String> inputServiceAccess = new ArrayList<>();
            Integer inputYearLower = 0;
            Integer inputYearUpper = 0;
            try {
                 inputYearLower = (Integer) input.get("year_lower_limit");
                 inputYearUpper = (Integer) input.get("year_upper_limit");
            }catch (Exception e) {
                    // donothing
            }

            while ((record = mapReader.read(header)) != null) {

                if(mapReader.getRowNumber() > 2) {
                    String age = record.get("Age Group").substring(0, record.get("Age Group").length() - 1);
                    int csvAge = Integer.parseInt(age);
                    Integer inputAge = (Integer) input.get("age");
                    if (inputAge <= csvAge) { //age appropriate
                        if(! inputAlreadySeen.contains(record.get("id"))) { // already seen

                            for(String services : inputServiceAccess) { // access
                                if(record.get(services).equals(1)) {

                                    int csvYear = Integer.parseInt("Year"); // limits
                                    if(inputYearLower != null && inputYearLower > csvYear || inputYearUpper != null && inputYearUpper < csvYear ) {

                                        String rottenTomatoes = record.get("Rotten Tomatoes").substring(0, record.get("Rotten Tomatoes").length() - 1);
                                        result.add(record);
                                    }
                                }
                            }

                        }
                    }

                }
            }
        }catch (Exception e){
            System.out.println("Error occurred in row :"+mapReader.getRowNumber());
        }
    }

    private static JSONObject getInput() {
        JSONObject jsonObject = new JSONObject("{\n" +
                "  \"age\": 25,\n" +
                "  \"rotten_tomatoes\": 77,\n" +
                "  \"services_owned\": [\"Disney+\", \"Netflix\"],\n" +
                "  \"already_seen\": [0, 12, 93, 23, 25, 3639, 5001],\n" +
                "  \"year_lower_limit\": 1999\n" +
                "}");
        return jsonObject;
    }


}
