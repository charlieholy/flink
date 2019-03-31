package com.charlie.task;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;

public class Test {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);
        //setup the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        //make parameter available in the wen interface
        env.getConfig().setGlobalJobParameters(params);
        DataSet<String> text = env.readTextFile(params.get("input"));
    }
}
