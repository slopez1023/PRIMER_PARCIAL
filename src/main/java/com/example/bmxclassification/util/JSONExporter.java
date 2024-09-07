package com.example.bmxclassification.util;

import com.example.bmxclassification.model.Competitor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class JSONExporter {

    public static void exportCompetitors(Writer writer, List<Competitor> competitors) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, competitors);
    }
}
