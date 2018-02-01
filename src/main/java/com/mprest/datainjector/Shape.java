package com.mprest.datainjector;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonIgnoreProperties({"id"})
public class Shape {

    private static Integer ID = 1;

    public Shape() {
        id = ID++;
        state = 0;

        lines = new ArrayList<>(2);

        lines.add( (Math.random()+1.0)*100 );
        lines.add( (Math.random()+1.0)*100 );
    }

    public String
    print() {
        return "id:" + id.toString() + ", state:" + state.toString()
                + ", line-1:" + lines.get(0).toString() + ",line-2:" + lines.get(1).toString();
    }

    private Integer id;
    private Integer state;

    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private List<Double> lines;
}
