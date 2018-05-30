package com.gek.java.f10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PTimeCsv {
    private List<String> lines;

    public PTimeCsv(List<String> lines) {
        this.lines = lines;
    }

    public List<String[]> getFieldList(boolean hasHeader, String ignorePattern) {
        List<String[]> fieldsList = new ArrayList<String[]>();

        String[] ignoreStrings = new String[]{};
        if (ignorePattern != null && ignorePattern.length() > 0) {
            ignoreStrings = ignorePattern.split(":::");
        }
        List<String> ignoreLines = Arrays.asList(ignoreStrings);

        int l = 0;
        for (String line : lines) {
            line = line.trim();

            l++;
            if (l == 1 && hasHeader) {
                continue;
            }

            if (line.length() == 0) {
                continue;
            }

            if (ignoreLine(line, ignoreLines)) {
                continue;
            }

            String[] fields = getFields(line);
            if (fields.length > 0) {
                fieldsList.add(fields);
            }
        }

        return fieldsList;
    }

    private String[] getFields(String line) {
        String[] fields = line.split(";");
        String[] fieldsContent = new String[fields.length];

        for (int i = 0; i < fields.length; i++) {
            String[] content = fields[i].split("\"");
            fieldsContent[i] = content[1];
        }

        return fieldsContent;
    }

    private boolean ignoreLine(String line, List<String> ignoreLines) {
        for (String ignorePattern : ignoreLines) {
            if (line.startsWith(ignorePattern)) {
                return true;
            }
        }

        return false;
    }

}
