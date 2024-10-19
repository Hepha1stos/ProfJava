package com.moritz.process;

import java.time.LocalTime;

public class MyProcess {
    private String name;
    private Integer pid;
    private String command_line;
    private LocalTime start_time;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getCommand_line() {
		return this.command_line;
	}

	public void setCommand_line(String command_line) {
		this.command_line = command_line;
	}

	public LocalTime getStart_time() {
		return this.start_time;
	}

	public void setStart_time(LocalTime start_time) {
		this.start_time = start_time;
	}

    public MyProcess(String name, Integer pid, String command_line, LocalTime start_time){
        this.name = name;
        this.pid = pid;
        this.command_line = command_line;
        this.start_time = start_time;
    }
}


