package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Project;

import java.util.List;

public interface ProjectService {
    void saveInfo(Project project);

    List<Project> getProjectList();

    Project getProjectById(Integer pid);

    void updateProject(Project project);

    boolean deleteProjectByPid(Integer[] ids);

    List<Project> search(Integer cid, String keyword, Integer orderby);

    List<Project> jsonProList(List<Integer> ids);

    List<Project> jsonInList(List<Integer> ids);

    List<Project> proInList();

    List<Project> proNotInList();

    List<Project> jsonFileList();
}
