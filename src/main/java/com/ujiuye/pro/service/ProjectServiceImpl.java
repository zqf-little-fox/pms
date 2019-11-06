package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;
import com.ujiuye.pro.mapper.ProjectMapper;
import com.ujiuye.sys.bean.EmployeeExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Resource
    private ProjectMapper projectMapper;

    @Override
    public void saveInfo(Project project) {
        projectMapper.insert(project);
    }

    @Override
    public List<Project> getProjectList() {
        ProjectExample example = new ProjectExample();
        List<Project> projects = projectMapper.selectByExample(example);
        return projects;
    }

    @Override
    public Project getProjectById(Integer pid) {
        return projectMapper.selectByPrimaryKey(pid);
    }

    @Override
    public void updateProject(Project project) {
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public boolean deleteProjectByPid(Integer[] ids) {
        List<Integer> idList = Arrays.asList(ids);
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andPidIn(idList);
        int row = projectMapper.deleteByExample(example);
        return ids.length == row;
    }

    @Override
    public List<Project> search(Integer cid, String keyword, Integer orderby) {
        List<Project> project = projectMapper.getProjectwithSearch(cid,keyword,orderby);
        return project;
    }

    @Override
    public List<Project> jsonProList(List<Integer> ids) {

        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andPidNotIn(ids);
        List<Project> projects = projectMapper.selectByExample(example);
        return projects;
    }

    @Override
    public List<Project> jsonInList(List<Integer> ids) {
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andPidIn(ids);
        List<Project> projects = projectMapper.selectByExample(example);
        return projects;
    }

    @Override
    public List<Project> proInList() {
        return projectMapper.proInList();
    }

    @Override
    public List<Project> proNotInList() {
        return projectMapper.proNotInList();
    }

    @Override
    public List<Project> jsonFileList() {
        return projectMapper.jsonFileList();
    }
}
